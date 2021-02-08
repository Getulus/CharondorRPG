package com.getulus.charondor.gamebody.model.character;

import com.getulus.charondor.gamebody.model.items.Item;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
                  double experienceNeededForNextLevel, double gold, String classSymbol, Set<Skill> skills)
    {
        super(
                ID, type, level, currentHealth, maxHealth, soulEnergy, skills,   attackValue, defenseValue,
                criticalChance, armor, magicResistance, stamina, wisdom, strength, agility, image
        );

        this.attributePoints = AttributePoint.builder().player(this).staminaPointPrice(20).agilityPointPrice(20).strengthPointPrice(20).wisdomPointPrice(20).build();
        this.name = name;
        this.experiencePoints = experiencePoints;
        this.experienceNeededForNextLevel = experienceNeededForNextLevel;
        this.gold = gold;
        this.items = new ArrayList<Item>();
        this.classSymbol = classSymbol;
        this.quests = new ArrayList<>();

    }



    private String name;
    private double experiencePoints;
    private double experienceNeededForNextLevel;
    private double gold;
    private String classSymbol;

    @Singular
    @OneToMany(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Item> items;

    @Singular
    @OneToOne(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    //@LazyCollection(LazyCollectionOption.FALSE)
    protected AttributePoint attributePoints;


    @Singular
    @OneToMany(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Quest> quests;


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