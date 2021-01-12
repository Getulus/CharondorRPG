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
    public Player(long ID, String type, int level, int health, int soulEnergy,
                  int attackValue, int defenseValue, int criticalChance,
                  int armor, int magicResistance,
                  int stamina, int wisdom, int strength, int agility, String name, int experiencePoints,
                  int experienceNeededForNextLevel, int gold, Set<Skill> skills, List<Buff> buffs, List<DeBuff> deBuffs)
    {
        super(
                ID, type, level, health, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, buffs, deBuffs, stamina, wisdom, strength, agility
        );

        this.name = name;
        this.experiencePoints = experiencePoints;
        this.experienceNeededForNextLevel = experienceNeededForNextLevel;
        this.gold = gold;
        //this.items = items;
    }



    private String name;
    private int experiencePoints;
    private int experienceNeededForNextLevel;
    private int gold;

    /*
    @Singular
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Item> items;
     */
}
