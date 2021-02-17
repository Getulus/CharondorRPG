package com.getulus.charondor.gamebody.model.character.Skills;

import com.getulus.charondor.gamebody.model.character.Player;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DynamicUpdate
@Entity
@Data
public class OffensiveInstantSpell extends Skill {

    @Builder(toBuilder = true)
    public OffensiveInstantSpell(long skillID, String name, Player player, double energyCost, String type, String image, String text, double damageMultiplier) {
        super(skillID, name, player, energyCost, type, image, text);
        this.damageMultiplier = damageMultiplier;
    }

    double damageMultiplier;

    @Override
    public double useEffectWithValue() {
        return Math.round(this.player.getAttackValue()*damageMultiplier + this.player.getWisdom() / 2);
    }
}
