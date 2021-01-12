package com.getulus.charondor.gamebody.model;

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
