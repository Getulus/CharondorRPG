package com.getulus.charondor.gamebody.model.character;

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

    //character info
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long ID;


    protected String type;
    protected double level;


    protected double currentHealth;
    protected double maxHealth;
    protected double soulEnergy;

    @Singular
    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    protected Set<Skill> skills;


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
