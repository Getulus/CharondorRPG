package com.getulus.charondor.gamebody.service;


import com.getulus.charondor.gamebody.model.Skill;

public interface CharacterActions {

    void useSkill(Skill skill);
    boolean isSkillAvailable(Skill skill);
    boolean isDead();


}
