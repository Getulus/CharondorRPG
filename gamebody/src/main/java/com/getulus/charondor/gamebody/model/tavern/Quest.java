package com.getulus.charondor.gamebody.model.tavern;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getulus.charondor.gamebody.model.character.Player;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Quest {

    @Builder(toBuilder = true)
    public Quest(Player player, String questName, double experience, double gold, double task, double progress, boolean completed, String monsterName, String itemName) {
        this.player = player;
        this.questName = questName;
        this.experience = experience;
        this.gold = gold;
        this.task = task;
        this.progress = progress;
        this.completed = completed;
        this.monsterName = monsterName;
        this.itemName = itemName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questId;

    @ManyToOne
    @JsonIgnore
    private Player player;

    private String questName;
    private double experience;
    private double gold;
    private double task;
    private double progress;
    private boolean completed;
    private String monsterName;
    private String itemName;


    public boolean isCompleted() {
        if (task == progress) {
            completed = true;
        }
        return completed;
    }
}
