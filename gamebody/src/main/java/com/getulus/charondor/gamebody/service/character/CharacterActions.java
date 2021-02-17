package com.getulus.charondor.gamebody.service.character;


import com.getulus.charondor.gamebody.model.character.Skills.Skill;

public interface CharacterActions {

    void useSkill(Skill skill);
    boolean isSkillAvailable(Skill skill);
    boolean isDead();


}
