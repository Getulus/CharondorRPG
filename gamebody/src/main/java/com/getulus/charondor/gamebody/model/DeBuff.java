package com.getulus.charondor.gamebody.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class DeBuff {


    @Id
    private long deBuffID;

    @ManyToOne
    private Character character;

}
