package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import com.getulus.charondor.gamebody.service.Items.ItemList;
import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CombatActions {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerList playerList;

    @Autowired
    MonsterList monsterList;

    @Autowired
    CombatLogList combatLogList;

    @Autowired
    PlayerActions playerActions;

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ItemList itemList;


    public void resolveCombat() {
        fight();
        if (playerActions.isDead()) {
            //If the player dead get nothing but health.
            //playerActions.regenerate();
        } else {
            playerActions.earnGold();
            playerActions.earnExperience();
            itemList.setLootedItems(monsterList.getCurrentMonster().getLevel());
            playerActions.loot(itemList.getAvailableItems());
        }

        playerRepository.save(playerList.getCurrentPlayer());

    }


    public void fight() {

        double monsterHealth = monsterList.getRandomMonster().getMaxHealth();
        double playerHealth = playerList.getCurrentPlayer().getCurrentHealth();

        double rounds = 0;

        combatLogList.setCombatLog(new ArrayList<>());

        while (monsterHealth > 0 && playerHealth > 0) {
            if (rounds % 2 == 0) {
                double playerDamage = restTemplate.getForEntity("http://192.168.0.18:8762/action/fight/player-attack", double.class).getBody();;
                monsterHealth -= playerDamage;
                combatLogList.getCombatLog().add(
                        new CombatLogTemplate(rounds,"Player", playerDamage, monsterHealth)
                );
                rounds++;
            } else {
                double monsterDamage = restTemplate.getForEntity("http://192.168.0.18:8762/action/fight/monster-attack", double.class).getBody();
                playerHealth -= monsterDamage;

                playerList.getCurrentPlayer().setCurrentHealth(playerHealth);

                combatLogList.getCombatLog().add(
                        new CombatLogTemplate(rounds,"Monster", monsterDamage, playerHealth)
                );
                rounds++;
            }
        }

    }

}
