package com.getulus.charondor.gamebody.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
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
    protected int level;


    protected int health;
    protected int soulEnergy;

    @Singular
    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    protected Set<Skill> skills;


    protected int attackValue;
    protected int defenseValue;
    protected int criticalChance;
    protected int armor;
    protected int magicResistance;

    @Singular
    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Buff> buffs;


    @Singular
    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<DeBuff> deBuffs;


    //attributes
    protected int stamina;
    protected int wisdom;
    protected int strength;
    protected int agility;


}
