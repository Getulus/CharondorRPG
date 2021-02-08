package com.getulus.charondor.gamebody.model.character;

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
@Builder
public class AttributePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attributeID;

    @OneToOne
    private Player player;

    private double strengthPointPrice;
    private double agilityPointPrice;
    private double staminaPointPrice;
    private double wisdomPointPrice;

    public void addStrengthPoint() {
        strengthPointPrice = Math.round(strengthPointPrice * 1.2);
    }

    public void addAgilityPointPrice() {
        agilityPointPrice = Math.round(agilityPointPrice * 1.2);
    }

    public void addStaminaPointPrice() {
        staminaPointPrice = Math.round(staminaPointPrice * 1.2);
    }

    public void addWisdomPointPrice() {
        wisdomPointPrice = Math.round(wisdomPointPrice * 1.2);
    }

}
