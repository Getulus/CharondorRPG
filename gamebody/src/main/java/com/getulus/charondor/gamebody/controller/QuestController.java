package com.getulus.charondor.gamebody.controller;


import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import com.getulus.charondor.gamebody.repository.QuestRepository;
import com.getulus.charondor.gamebody.service.character.PlayerList;
import com.getulus.charondor.gamebody.service.tavern.QuestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuestController {

    @Autowired
    PlayerList playerList;

    @Autowired
    QuestList questList;

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    QuestRepository questRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/quest/accept")
    public void acceptQuest(@RequestBody Quest quest, HttpServletResponse response){
        try {
            response.setStatus(200);
            questList.acceptQuest(quest.getQuestName());

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
    @GetMapping("/quest/tavern-quests")
    public List<Quest> getTavernQuests(HttpServletResponse response){
        try {
            response.setStatus(200);
            questList.sortQuests();
            return questList.getAvailableQuests();

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
    @GetMapping("/quest/player-quests")
    public List<Quest> getPlayerQuests(HttpServletResponse response){
        try {
            response.setStatus(200);
            List<Quest> playerQuests = playerList.getCurrentPlayer().getQuests();

            return playerQuests.stream().filter(x-> !x.getTurnedIn()).collect(Collectors.toList());

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
    @PostMapping("/quest/complete")
    public void completeQuest(@RequestBody Quest quest, HttpServletResponse response){
        try {
            response.setStatus(200);
            questList.turnInQuest(quest.getQuestName());

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
    @GetMapping("/quest/test")
    public void testQuest(HttpServletResponse response){
        try {
            response.setStatus(200);

            questList.acceptQuest("Kill Some Wolfs");



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