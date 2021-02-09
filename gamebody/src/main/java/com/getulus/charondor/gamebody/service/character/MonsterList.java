package com.getulus.charondor.gamebody.service.character;

import com.getulus.charondor.gamebody.model.character.Monster;
import com.getulus.charondor.gamebody.model.advantures.Adventure;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import com.getulus.charondor.gamebody.service.Adventures.AdventureList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@Data
public class MonsterList {
    public Monster currentMonster;
    public List<Monster> monsters;

    @Autowired
    AdventureList adventureList;

    @Autowired
    public MonsterRepository monsterRepository;

    public Monster getMonsterByName() {
        if (monsterRepository.getMonsterByName("Werewolf").isPresent()) {
            return monsterRepository.getMonsterByName("Werewolf").get();
        }
        return null;
    }

    private void getAllMonsterByAdventure() {
        Adventure currentAdventure = adventureList.getCurrentAdventure();
        int startLevel = currentAdventure.getStartLevel();
        int endLevel = currentAdventure.getEndLevel();

        //monsters = monsterRepository.findAllByOrderByName();
        monsters = monsterRepository.getAllByLevelBetween(startLevel,endLevel);
    }

    public Monster getRandomMonster(){
        Random random = new Random();
        getAllMonsterByAdventure();
        currentMonster =  monsters.get(random.nextInt(monsters.size()));
        return currentMonster;
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

}
