package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Skill;
import org.springframework.stereotype.Service;


@Service
public class PlayerActions implements CharacterActions{
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
