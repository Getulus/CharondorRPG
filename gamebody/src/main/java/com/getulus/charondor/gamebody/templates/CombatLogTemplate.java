package com.getulus.charondor.gamebody.templates;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombatLogTemplate {

    double numberOfRound;
    String name;
    double damageDealt;
    double enemyRemainingHealth;


}
