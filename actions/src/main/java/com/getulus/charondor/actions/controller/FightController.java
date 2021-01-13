package com.getulus.charondor.actions.controller;


import com.getulus.charondor.actions.ExceptionLog;
import com.getulus.charondor.actions.service.FightSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class FightController {

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    FightSimulator fightSimulator;

    @CrossOrigin(origins = "*")
    @GetMapping("/fight/all-damage")
    public double getAllDamage(HttpServletResponse response){
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

}
