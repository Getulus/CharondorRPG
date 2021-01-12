package com.getulus.charondor.gamebody.model;


import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@DynamicUpdate
@Entity
public class Monster extends Character{

    @Builder(toBuilder = true)
    public Monster(long ID, String type, int level, int health, int soulEnergy,
                  int attackValue, int defenseValue, int criticalChance,
                  int armor, int magicResistance,
                  int stamina, int wisdom, int strength, int agility, String name,
                  Set<Skill> skills, List<Buff> buffs, List<DeBuff> deBuffs, int lootedGold)
    {
        super(
                ID, type, level, health, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, buffs, deBuffs, stamina, wisdom, strength, agility
        );

        this.name = name;
        this.lootedGold = lootedGold;
    }


    private int lootedGold;
    private String name;

}
