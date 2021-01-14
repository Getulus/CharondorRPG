package com.getulus.charondor.gamebody.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@DynamicUpdate
@Entity
@Data
public class Player extends Character {

    @Builder(toBuilder = true)
    public Player(long ID, String type, double level, double currentHealth, double maxHealth, double soulEnergy,
                  double attackValue, double defenseValue, double criticalChance,
                  double armor, double magicResistance,
                  double stamina, double wisdom, double strength, double agility, String name, double experiencePoints,
                  double experienceNeededForNextLevel, double gold, Set<Skill> skills, List<Buff> buffs, List<DeBuff> deBuffs)
    {
        super(
                ID, type, level, currentHealth, maxHealth, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, buffs, deBuffs, stamina, wisdom, strength, agility
        );

        this.name = name;
        this.experiencePoints = experiencePoints;
        this.experienceNeededForNextLevel = experienceNeededForNextLevel;
        this.gold = gold;
        //this.items = items;
    }



    private String name;
    private double experiencePoints;
    private double experienceNeededForNextLevel;
    private double gold;

    /*
    @Singular
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Item> items;
     */
}
