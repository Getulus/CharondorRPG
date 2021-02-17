package com.getulus.charondor.gamebody.model.character;


import com.getulus.charondor.gamebody.model.character.Skills.Skill;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Data
public class Monster extends Character {
    private double lootedGold;
    private String name;
    private double experience;

    @Builder(toBuilder = true)
    public Monster(long ID, String type, double level, double currentHealth, double maxHealth, double soulEnergy,
                   double attackValue, double defenseValue, double criticalChance,
                   double armor, double magicResistance,
                   double stamina, double wisdom, double strength, double agility, String image, String name, double experience,
                   double lootedGold)
    {
        super(
                ID, type, level, currentHealth, maxHealth, soulEnergy,  attackValue, defenseValue,
                criticalChance, armor, magicResistance, stamina, wisdom, strength, agility, image
        );

        this.name = name;
        this.lootedGold = lootedGold;
        this.experience = experience;
    }




}
