package com.getulus.charondor.gamebody.controller;


import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.service.Adventures.AdventureList;
import com.getulus.charondor.gamebody.service.MonsterList;
import com.getulus.charondor.gamebody.service.PlayerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MonsterController {
    @Autowired
    MonsterList monsterList;

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    AdventureList adventureList;


    @CrossOrigin(origins = "*")
    @GetMapping("/character/monster")
    public Monster getCurrentMonster(HttpServletResponse response){
        try {
            response.setStatus(200);
            if (adventureList.getCurrentAdventure() == null) {
                return Monster.builder().image("/images/blankmonster.png").name("Monster").build();
            }

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

}
