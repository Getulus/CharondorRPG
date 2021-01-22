package com.getulus.charondor.gamebody.controller;


import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.service.PlayerActions;
import com.getulus.charondor.gamebody.service.PlayerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PlayerController {

    @Autowired
    PlayerList playerList;

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    PlayerActions playerActions;


    @CrossOrigin(origins = "*")
    @GetMapping("/character/player")
    public Player getCurrentPlayer(HttpServletResponse response){
        try {
            response.setStatus(200);
            return playerList.getCurrentPlayer();
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
    @GetMapping("/character/regenerate")
    public void regenerate(HttpServletResponse response){
        try {
            response.setStatus(200);
            playerActions.regenerate();

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
