package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class MonsterList {
    public Monster currentMonster;
    public List<Monster> monsters;

    @Autowired
    public MonsterRepository monsterRepository;

    public Monster getMonsterByName() {
        if (monsterRepository.getPlayerByName("Werewolf").isPresent()) {
            return monsterRepository.getPlayerByName("Werewolf").get();
        }
        return null;
    }

    private void getAllMonster() {
        monsters = monsterRepository.findAllByOrderByName();
        System.out.println(monsters.toString());
    }

    public Monster getRandomMonster(){
        if (monsters == null) {
            getAllMonster();
        }
        currentMonster =  monsters.get(0);
        return currentMonster;
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

}
