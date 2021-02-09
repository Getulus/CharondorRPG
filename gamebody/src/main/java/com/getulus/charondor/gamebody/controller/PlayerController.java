package com.getulus.charondor.gamebody.controller;


import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.character.AttributeCredentials;
import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.service.character.PlayerActions;
import com.getulus.charondor.gamebody.service.character.PlayerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            if (playerList.getCurrentPlayer() == null) {
                return Player.builder().build();
            }
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

    @CrossOrigin(origins = "*")
    @PostMapping("/character/choose-character")
    public void chooseCharacter(@RequestBody Player player, HttpServletResponse response){
        try {
            response.setStatus(200);

            playerList.choosePlayer(player);

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
    @PostMapping("/character/change-name")
    public void changeName(@RequestBody Player player, HttpServletResponse response){
        try {
            response.setStatus(200);

            playerList.getCurrentPlayer().setName(player.getName());

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
    @PostMapping("/character/player/add-attribute")
    public void addAttributePoint(@RequestBody AttributeCredentials attributeCredentials, HttpServletResponse response){
        try {
            response.setStatus(200);
            playerList.addAttributePoint(attributeCredentials.getAttributeName());
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
    @GetMapping("/character/player/test")
    public void test(HttpServletResponse response){
        try {
            response.setStatus(200);
            playerList.addAttributePoint("strength");
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
