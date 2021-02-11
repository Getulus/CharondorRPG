package com.getulus.charondor.gamebody.service.character;

import com.getulus.charondor.gamebody.model.character.Monster;
import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.tavern.Quest;
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


        newPLayer.setName(player.getName());
        currentPlayer = newPLayer;
        playerRepository.save(newPLayer);
    }

    public Player getCurrentPlayerWithEquipment() {

        return currentPlayer;
    }

    public void savePlayer() {
        playerRepository.save(currentPlayer);
    }

    public void addAttributePoint(String attributeName) {

        switch (attributeName) {
            case "strength":
                addStrength();
                break;
            case "stamina":
                addStamina();
                break;
            case "agility":
                addAgility();
                break;
            case "wisdom":
                addWisdom();
                break;
        }
        savePlayer();
    }


    private void addStrength(){
        if (currentPlayer.getAttributePoints().getStrengthPointPrice() <= currentPlayer.getGold()){
            currentPlayer.setStrength(currentPlayer.getStrength() + 3);
            currentPlayer.setGold(currentPlayer.getGold() - currentPlayer.getAttributePoints().getStrengthPointPrice());
            currentPlayer.getAttributePoints().addStrengthPointPrice();
        }
    }

    private void addStamina(){
        if (currentPlayer.getAttributePoints().getStaminaPointPrice() <= currentPlayer.getGold()){
            currentPlayer.setStamina(currentPlayer.getStamina() + 3);
            currentPlayer.setGold(currentPlayer.getGold() - currentPlayer.getAttributePoints().getStaminaPointPrice());
            currentPlayer.getAttributePoints().addStaminaPointPrice();
        }
    }

    private void addWisdom(){
        if (currentPlayer.getAttributePoints().getWisdomPointPrice() <= currentPlayer.getGold()){
            currentPlayer.setWisdom(currentPlayer.getWisdom() + 3);
            currentPlayer.setGold(currentPlayer.getGold() - currentPlayer.getAttributePoints().getWisdomPointPrice());
            currentPlayer.getAttributePoints().addWisdomPointPrice();
        }
    }

    private void addAgility(){
        if (currentPlayer.getAttributePoints().getAgilityPointPrice() <= currentPlayer.getGold()){
            currentPlayer.setAgility(currentPlayer.getAgility() + 3);
            currentPlayer.setGold(currentPlayer.getGold() - currentPlayer.getAttributePoints().getAgilityPointPrice());
            currentPlayer.getAttributePoints().addAgilityPointPrice();
        }
    }





}
