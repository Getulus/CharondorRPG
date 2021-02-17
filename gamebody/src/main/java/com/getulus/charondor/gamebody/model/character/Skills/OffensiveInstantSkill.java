package com.getulus.charondor.gamebody.model.character.Skills;


import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.character.Skills.Skill;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DynamicUpdate
@Entity
@Data

public class OffensiveInstantSkill extends Skill {

    @Builder(toBuilder = true)
    public OffensiveInstantSkill(long skillID, String name, Player player, double energyCost, String type, String image, String text, double damageMultiplier) {
        super(skillID, name, player, energyCost, type, image, text);
        this.damageMultiplier = damageMultiplier;
    }

    private double damageMultiplier;


    @Override
    public double useEffectWithValue() {
        return (double) Math.round(this.player.getAttackValue() * damageMultiplier);

    }

    @Override
    public double useEffectWithValue(Double attribute) {

        return super.useEffectWithValue(attribute);
    }

    @Override
    public void useEffectWithoutValue() {
        super.useEffectWithoutValue();
    }
}
