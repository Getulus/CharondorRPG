package com.getulus.charondor.gamebody.service;


import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.Skill;
import org.springframework.stereotype.Service;


@Service
public class MonsterActions implements CharacterActions {
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


    private boolean isAnyLoot(){
        return false;
    }


    public Item getRandomLoot(){
        return null;
    }


}
