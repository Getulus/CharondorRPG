package com.getulus.charondor.gamebody.model.character;

import com.getulus.charondor.gamebody.model.character.Character;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Skill {

    @Id
    private long skillID;

    @ManyToOne
    private Character character;
}
