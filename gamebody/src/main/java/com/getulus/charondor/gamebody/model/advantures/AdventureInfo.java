package com.getulus.charondor.gamebody.model.advantures;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum AdventureInfo {

    MISTY_FOREST(1,3),
    SPIDER_CAVE(2,4);



    private double startLevel;
    private double endLevel;

    public double getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(double startLevel) {
        this.startLevel = startLevel;
    }

    public double getEndLevel() {
        return endLevel;
    }

    public void setEndLevel(double endLevel) {
        this.endLevel = endLevel;
    }
}
