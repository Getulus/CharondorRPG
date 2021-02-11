package com.getulus.charondor.gamebody.service.Items;


import com.getulus.charondor.gamebody.model.items.Item;
import com.getulus.charondor.gamebody.model.items.ItemCredentials;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.service.character.PlayerList;
import com.getulus.charondor.gamebody.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemList {

    @Autowired
    Util util;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PlayerList playerList;


    List<Item> availableItems;


    public void setLootedItems(Double level) {
        availableItems = itemRepository.getItemByLevelAndPlayer_IDIsNull(level);
        sortLootedItems();

    }

    private void sortLootedItems(){
        List<Item> lootedItems = new ArrayList<>();

        for (int i = 0; i < availableItems.size(); i++) {

            Item currentItem = availableItems.get(i);
            double dropChance = currentItem.getDropChance();

            if (util.chanceForEvent(dropChance)) {
                lootedItems.add(currentItem);
            }
        }

        availableItems = lootedItems;
    }

    public void addItem() {

    }




    public void equipItem(ItemCredentials itemCredentials){
        Optional<Item> optSlotItem = itemRepository.getItemBySlotAndEquipped(itemCredentials.getSlot(), true);

        if (optSlotItem.isPresent()) {
            Item slotItem = optSlotItem.get();
            slotItem.setEquipped(false);
            itemRepository.save(slotItem);
            playerList.getCurrentPlayer().unEquipItem(slotItem);

            for (Item item : playerList.getCurrentPlayer().getItems()) {
                if (item.getItemID() == slotItem.getItemID()) {
                    item.setEquipped(false);
                }
            }
        }

        for (Item item : playerList.getCurrentPlayer().getItems()) {
            if (item.getItemID() == itemCredentials.getID()) {
                item.setEquipped(true);
            }
        }

        Item currentItem = itemRepository.getItemByItemID(itemCredentials.getID()).get();
        currentItem.setEquipped(true);
        itemRepository.save(currentItem);
        playerList.getCurrentPlayer().equipItem(currentItem);
        playerList.savePlayer();
    }


}
