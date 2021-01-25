package com.getulus.charondor.gamebody.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
                  double stamina, double wisdom, double strength, double agility, String image, String name, double experiencePoints,
                  double experienceNeededForNextLevel, double gold, Set<Skill> skills, List<Buff> buffs, List<DeBuff> deBuffs)
    {
        super(
                ID, type, level, currentHealth, maxHealth, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, buffs, deBuffs, stamina, wisdom, strength, agility, image
        );

        this.name = name;
        this.experiencePoints = experiencePoints;
        this.experienceNeededForNextLevel = experienceNeededForNextLevel;
        this.gold = gold;
        this.items = new ArrayList<Item>();
    }



    private String name;
    private double experiencePoints;
    private double experienceNeededForNextLevel;
    private double gold;

    @Singular
    @OneToMany(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Item> items;


    public void increaseAttributesByLeveling() {
        this.level++;

        this.agility += 2;
        this.strength += 3;
        this.wisdom += 1;
        this.stamina += 3;

        this.armor += 30;
        this.attackValue += 5;
        this.defenseValue += 2;

        this.soulEnergy += 20;
        this.maxHealth += 100;

        this.experienceNeededForNextLevel *= 2;

    }

    public void addItemToInventory(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void unEquipItem(Item item) {
        agility -= item.getAgility();
        stamina -= item.getStamina();
        strength -= item.getStrength();
        armor -= item.getArmor();
        attackValue -= item.getAttackValue();
        criticalChance -= item.getCriticalChance();
        defenseValue -= item.getDefenseValue();
        wisdom -= item.getWisdom();
    }

    public void equipItem(Item item) {
        agility += item.getAgility();
        stamina += item.getStamina();
        strength += item.getStrength();
        armor += item.getArmor();
        attackValue += item.getAttackValue();
        criticalChance += item.getCriticalChance();
        defenseValue += item.getDefenseValue();
        wisdom += item.getWisdom();
    }

}
