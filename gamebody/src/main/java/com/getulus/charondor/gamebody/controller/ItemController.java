package com.getulus.charondor.gamebody.controller;

import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.items.ItemCredentials;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.service.Items.ItemList;
import com.getulus.charondor.gamebody.service.PlayerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    PlayerList playerList;

    @Autowired
    ItemList itemList;

    @CrossOrigin(origins = "*")
    @GetMapping("/items/inventory-items")
    public List<Item> getInventoryItems(HttpServletResponse response){
        try {
            response.setStatus(200);
            List<Item> items = itemRepository.getItemByPlayer_IDAndEquipped(playerList.getCurrentPlayer().getID(), false);

            while (items.size() != 25) {
                items.add(Item.builder().name("empty slot").image("/images/inventory-slot.png").build());
            }

            return items;
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/get-loot")
    public List<Item> getLoot(HttpServletResponse response){
        try {
            response.setStatus(200);
            return itemList.getAvailableItems();
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/items/equip")
    public void equip(@RequestBody ItemCredentials itemCredentials, HttpServletResponse response){
        try {
            response.setStatus(200);
            itemList.equipItem(itemCredentials);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/equipped-items")
    public List<Item> getEquippedItems(HttpServletResponse response){
        try {
            response.setStatus(200);
            List<Item> items = itemRepository.getItemByPlayer_IDAndEquipped(playerList.getCurrentPlayer().getID(), true);
            /*
            while (items.size() != 7) {
                items.add(Item.builder().name("empty slot").image("/images/inventory-slot.png").build());
            }
            */
            return items;
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }


}