package com.getulus.charondor.gamebody.controller;

import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.advantures.AdventureNameCredential;
import com.getulus.charondor.gamebody.model.character.Monster;
import com.getulus.charondor.gamebody.repository.AdventureRepository;
import com.getulus.charondor.gamebody.service.Adventures.AdventureList;
import com.getulus.charondor.gamebody.service.character.MonsterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AdventuresController {

    @Autowired
    AdventureRepository adventureRepository;

    @Autowired
    AdventureList adventureList;

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    MonsterList monsterList;

    @CrossOrigin(origins = "*")
    @PostMapping("/adventure/set-adventure")
    public Monster getAdventure(@RequestBody AdventureNameCredential adventureName, HttpServletResponse response){
        try {
            response.setStatus(200);
            adventureList.setCurrentAdventure(adventureRepository.getAdventureByName(adventureName.getName()).get());

            Monster monster = monsterList.getRandomMonster();
            monsterList.setCurrentMonster(monster);

            return monsterList.getCurrentMonster();
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/adventure/clear-adventure")
    public String clearAdventures(HttpServletResponse response){
        try {
            response.setStatus(200);
            adventureList.setCurrentAdventure(null);
            return "OK";
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in players list");
        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

}
