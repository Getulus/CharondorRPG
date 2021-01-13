package com.getulus.charondor.gamebody.controller;

import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.service.PlayerActions;
import com.getulus.charondor.gamebody.service.PlayerList;
import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ActionController {

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    PlayerActions playerActions;


    @CrossOrigin(origins = "*")
    @GetMapping("/action/fight")
    public List<CombatLogTemplate> fight(HttpServletResponse response){
        try {
            response.setStatus(200);
            return playerActions.fight();
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
