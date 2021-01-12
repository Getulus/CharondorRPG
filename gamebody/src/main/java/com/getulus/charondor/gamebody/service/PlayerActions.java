package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Skill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class PlayerActions implements CharacterActions{


    RestTemplate restTemplate = new RestTemplate();

    public int fight() {
        return restTemplate.getForEntity("http://192.168.0.18:8762/action/fight/all-damage", int.class).getBody();
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
