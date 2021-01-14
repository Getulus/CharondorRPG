package com.getulus.charondor.gamebody.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Data
public class Monster extends Character{
    private double lootedGold;
    private String name;

    @Builder(toBuilder = true)
    public Monster(long ID, String type, double level, double currentHealth, double maxHealth, double soulEnergy,
                  double attackValue, double defenseValue, double criticalChance,
                  double armor, double magicResistance,
                  double stamina, double wisdom, double strength, double agility, String name,
                  Set<Skill> skills, List<Buff> buffs, List<DeBuff> deBuffs, double lootedGold)
    {
        super(
                ID, type, level, currentHealth, maxHealth, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, buffs, deBuffs, stamina, wisdom, strength, agility
        );

        this.name = name;
        this.lootedGold = lootedGold;
    }




}
