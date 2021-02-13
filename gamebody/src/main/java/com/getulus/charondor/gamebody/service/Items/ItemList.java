package com.getulus.charondor.gamebody.service.Items;


import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.items.Item;
import com.getulus.charondor.gamebody.model.items.ItemCredentials;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.service.character.PlayerActions;
import com.getulus.charondor.gamebody.service.character.PlayerList;
import com.getulus.charondor.gamebody.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.matcher.InstanceTypeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    @Autowired
    PlayerActions playerActions;


    List<Item> availableItems;
    List<Item> shopItems = new ArrayList<>();



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


    private void getRandomItems(){
        List<Item> tempItems = itemRepository.getItemsByPlayerIsNull();
        Random random = new Random();
        int numberOfItems = tempItems.size();

        if (numberOfItems > 0) {
            for (int i = 0; i < 5; i++) {
                int randomNumber = random.nextInt(numberOfItems);

                shopItems.add(tempItems.get(randomNumber));
            }}
    }

    public List<Item> getShopItems() {
        if (shopItems.size() == 0) {
            getRandomItems();
        }

        return shopItems;
    }

    public void sellItem(Item item) {
        Item currentItem = itemRepository.getItemByItemID(item.getItemID()).get();

        playerActions.removeItemFromInventory(currentItem);
        itemRepository.delete(currentItem);
    }

    private void removeItemFromShop(Item item) {
        for (Item cItem : shopItems ) {
            if (cItem.getItemID() == item.getItemID()) {
                shopItems.remove(cItem);
                break;
            }
        }
    }


    public void buyItem(Item item) {
        Item currentItem = itemRepository.getItemByItemID(item.getItemID()).get();
        Player currentPlayer = playerList.getCurrentPlayer();
        if (currentPlayer.getGold() > currentItem.getGold()) {
            List<Item> currentListOfItem = new ArrayList<>();

            currentListOfItem.add(currentItem);
            playerActions.loot(currentListOfItem);
            currentPlayer.setGold(currentPlayer.getGold() - currentItem.getGold());
            removeItemFromShop(item);
            playerList.savePlayer(currentPlayer);
        }
    }


}
