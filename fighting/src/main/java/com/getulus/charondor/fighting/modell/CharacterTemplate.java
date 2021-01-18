package com.getulus.charondor.fighting.modell;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterTemplate {

    private double attackValue;
    private double defenseValue;

    private double armor;
    private double magicResistance;

    private double strength;
    private double agility;

    private double criticalChance;
}
