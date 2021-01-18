package com.getulus.charondor.fighting.controller;


import com.getulus.charondor.fighting.ExceptionLog;
import com.getulus.charondor.fighting.service.FightSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class FightController {

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    FightSimulator fightSimulator;

    @CrossOrigin(origins = "*")
    @GetMapping("/fight/player-attack")
    public double playerAttack(HttpServletResponse response){
        try {
            response.setStatus(200);
            return fightSimulator.resolveFight("player");
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
    @GetMapping("/fight/monster-attack")
    public double monsterAttack(HttpServletResponse response){
        try {
            response.setStatus(200);
            return fightSimulator.resolveFight("monster");
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
