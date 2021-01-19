package com.getulus.charondor.gamebody.model.advantures;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Adventure {

    @Builder(toBuilder = true)
    public Adventure(String type, String name, int startLevel, int endLevel) {
        this.type = type;
        this.name = name;
        this.startLevel = startLevel;
        this.endLevel = endLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String type;
    private String name;
    private int startLevel;
    private int endLevel;

}
