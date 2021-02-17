package com.getulus.charondor.gamebody.model.character.Skills;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getulus.charondor.gamebody.model.character.Character;
import com.getulus.charondor.gamebody.model.character.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Skill {

    public Skill(String name, Player player, double energyCost, String type, String image, String text) {
        this.name = name;
        this.player = player;
        this.energyCost = energyCost;
        this.type = type;
        this.image = image;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long skillID;

    protected String name;

    @ManyToOne
    @JsonIgnore
    protected Player player;

    protected double energyCost;
    protected String type;

    protected String image;
    protected String text;



    public void useEffectWithoutValue(){}

    public double useEffectWithValue(){return 0;}
    public double useEffectWithValue(Double attribute){return 0;}
}
