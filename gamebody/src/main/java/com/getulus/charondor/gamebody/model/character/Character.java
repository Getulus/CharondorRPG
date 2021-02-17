package com.getulus.charondor.gamebody.model.character;

import com.getulus.charondor.gamebody.model.character.Skills.Skill;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Character {


    public Character(String type, double level, double currentHealth, double maxHealth, double soulEnergy,
                     double attackValue, double defenseValue, double criticalChance,
                     double armor, double magicResistance, double stamina, double wisdom, double strength,
                     double agility, String image) {
        this.type = type;
        this.level = level;
        this.stamina = stamina;
        this.currentHealth = currentHealth;
        this.maxHealth = this.maxHealth + (stamina * 10);
        this.soulEnergy = soulEnergy;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.criticalChance = criticalChance;
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.wisdom = wisdom;
        this.strength = strength;
        this.agility = agility;
        this.image = image;
    }

    //character info
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long ID;


    protected String type;
    protected double level;


    protected double currentHealth;
    protected double maxHealth;
    protected double soulEnergy;




    protected double attackValue;
    protected double defenseValue;
    protected double criticalChance;
    protected double armor;
    protected double magicResistance;







    //attributes
    protected double stamina;
    protected double wisdom;
    protected double strength;
    protected double agility;

    protected String image;



}
