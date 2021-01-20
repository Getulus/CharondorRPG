package com.getulus.charondor.gamebody.service.Items;


import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.items.ItemCredentials;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemList {

    @Autowired
    Util util;

    @Autowired
    ItemRepository itemRepository;


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
        if (itemRepository.getItemBySlotAndEquipped(itemCredentials.getSlot(), true).isEmpty()) {
            Item currentItem = itemRepository.getItemByItemID(itemCredentials.getID()).get();
            currentItem.setEquipped(true);
            itemRepository.save(currentItem);
        }
    }
}
