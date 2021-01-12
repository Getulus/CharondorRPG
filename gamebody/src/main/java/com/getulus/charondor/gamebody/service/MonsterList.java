package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterList {
    public Monster currentMonster;
    public List<Monster> monsters;

    @Autowired
    public MonsterRepository monsterRepository;

    public Monster getCurrentMonster() {
        if (monsterRepository.getPlayerByName("Werewolf").isPresent()) {
            return monsterRepository.getPlayerByName("Werewolf").get();
        }
        return null;
    }

    public void addPlayer(Monster monster){
        monsters.add(monster);
    }

}
