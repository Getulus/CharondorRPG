package com.getulus.charondor.gamebody.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Builder(toBuilder = true)
    public Item(String name, String slot, double level, String rarity, double dropChance,
                double stamina, double wisdom, double strength, double agility, double attackValue,
                double defenseValue, double criticalChance, double armor, double magicResistance,
                Player player) {
        this.name = name;
        this.slot = slot;
        this.level = level;
        this.rarity = rarity;
        this.dropChance = dropChance;
        this.stamina = stamina;
        this.wisdom = wisdom;
        this.strength = strength;
        this.agility = agility;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.criticalChance = criticalChance;
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.player = player;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemID;

    private String name;
    private String slot;
    private double level;
    private String rarity;
    private double dropChance;


    private double stamina;
    private double wisdom;
    private double strength;
    private double agility;

    private double attackValue;
    private double defenseValue;
    private double criticalChance;
    private double armor;
    private double magicResistance;


    @ManyToOne
    @JsonIgnore
    private Player player;
}
