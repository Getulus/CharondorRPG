package com.getulus.charondor.gamebody.model.items;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getulus.charondor.gamebody.model.character.Player;
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
    public Item(String name, String slot, double level, String rarity, double dropChance, String image, boolean equipped,
                double stamina, double wisdom, double strength, double agility, double attackValue,
                double defenseValue, double criticalChance, double armor, double magicResistance, double gold,
                Player player) {
        this.name = name;
        this.slot = slot;
        this.level = level;
        this.rarity = rarity;
        this.dropChance = dropChance;
        this.image = image;
        this.equipped = equipped;
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
        this.gold = gold;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemID;

    private String name;
    private String slot;
    private double level;
    private String rarity;
    private double dropChance;
    private String image;

    private boolean equipped;


    private double stamina;
    private double wisdom;
    private double strength;
    private double agility;

    private double attackValue;
    private double defenseValue;
    private double criticalChance;
    private double armor;
    private double magicResistance;

    private double gold;


    @ManyToOne
    @JsonIgnore
    private Player player;
}
