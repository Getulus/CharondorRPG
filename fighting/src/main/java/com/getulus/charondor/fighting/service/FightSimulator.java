package com.getulus.charondor.fighting.service;

import com.getulus.charondor.fighting.modell.CharacterTemplate;
import com.getulus.charondor.fighting.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FightSimulator {

    @Autowired
    private Util util;

    private CharacterTemplate attacker;
    private CharacterTemplate defender;

    private RestTemplate restTemplate = new RestTemplate();

    public double resolveFight(String role) {
        setRoles(role);
        double atk = calculateAttackValue();
        double def = calculateDefenseValue();

        return Math.round(atk * def);

    }


    private void setRoles(String role) {
        CharacterTemplate player = restTemplate.getForEntity("http://192.168.0.18:8762/charondor/character/player", CharacterTemplate.class).getBody();
        CharacterTemplate monster = restTemplate.getForEntity("http://192.168.0.18:8762/charondor/character/monster", CharacterTemplate.class).getBody();



        if (role.equals("player")) {
            attacker = player;
            defender = monster;
        } else {
            attacker = monster;
            defender = player;
        }


    }

    private double calculateDefenseValue() {
        return 1- ((defender.getDefenseValue() + defender.getArmor() * 0.05) / 100);
    }

    private double calculateAttackValue() {
        double critical = 1;
        double chance = attacker.getCriticalChance() + Math.round(attacker.getAgility() / 5);

        if (util.chanceForEvent(chance)) {
            critical = 2;
        }

        return Math.round(attacker.getAttackValue() + attacker.getStrength() * 0.3) * critical;

    }



}
