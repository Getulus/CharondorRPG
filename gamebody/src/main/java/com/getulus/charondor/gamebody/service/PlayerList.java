package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerList {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PlayerRepository playerRepository;


    private Player currentPlayer;
    private Monster currentEnemy;
    private List<Player> players;



    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    public void choosePlayer(Player player){
        Player newPLayer = playerRepository.getPlayerByType(player.getType()).get();


        //newPLayer.setName(player.getName());
        currentPlayer = newPLayer;
        playerRepository.save(newPLayer);
    }

    public Player getCurrentPlayerWithEquipment() {
        /*
        List<Item> equipment = itemRepository.getItemByPlayer_IDAndEquipped(currentPlayer.getID(), true);
        for (Item item : equipment) {
            currentPlayer.setAgility(currentPlayer.getAgility() + item.getAgility());
            currentPlayer.setStamina(currentPlayer.getStamina() + item.getStamina());
            currentPlayer.setStrength(currentPlayer.getStrength() + item.getStrength());
            currentPlayer.setArmor(currentPlayer.getArmor() + item.getArmor());
            currentPlayer.setAttackValue(currentPlayer.getAttackValue() + item.getAttackValue());
            currentPlayer.setCurrentHealth(currentPlayer.getCriticalChance() + item.getCriticalChance());
            currentPlayer.setDefenseValue(currentPlayer.getDefenseValue() + item.getDefenseValue());
            currentPlayer.setWisdom(currentPlayer.getWisdom() + item.getWisdom());
        }
        */
        return currentPlayer;
    }

    public void savePlayer() {
        playerRepository.save(currentPlayer);
    }


}
