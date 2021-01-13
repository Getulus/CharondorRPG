package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Skill;
import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerActions implements CharacterActions{

    @Autowired
    PlayerList playerList;

    @Autowired
    MonsterList monsterList;

    RestTemplate restTemplate = new RestTemplate();

    public List<CombatLogTemplate> fight() {

        double monsterHealth = monsterList.getRandomMonster().getHealth();
        double playerHealth = playerList.getCurrentPlayer().getHealth();

        double rounds = 0;

        List<CombatLogTemplate> battleLog = new ArrayList<>();

        while (monsterHealth > 0 && playerHealth > 0) {
            if (rounds % 2 == 0) {
                double playerDamage = restTemplate.getForEntity("http://192.168.0.18:8762/action/fight/player-attack", double.class).getBody();
                monsterHealth -= playerDamage;
                battleLog.add(
                        new CombatLogTemplate(rounds,"Player", playerDamage, monsterHealth)
                );
                rounds++;
            } else {
                double monsterDamage = restTemplate.getForEntity("http://192.168.0.18:8762/action/fight/monster-attack", double.class).getBody();
                playerHealth -= monsterDamage;
                battleLog.add(
                        new CombatLogTemplate(rounds,"Monster", monsterDamage, playerHealth)
                );
                rounds++;
            }
        }

        return battleLog;

    }

    @Override
    public void useSkill(Skill skill) {

    }

    @Override
    public boolean isSkillAvailable(Skill skill) {
        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }


    public void earnExperience(){

    }

    private int countExperience(){
        return 0;
    }

    public void earnGold(){

    }

    public void loot(){

    }

    public void retreat(){

    }

    public void equipItem(){

    }

}
