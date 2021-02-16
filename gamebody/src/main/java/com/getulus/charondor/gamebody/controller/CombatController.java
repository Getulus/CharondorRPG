package com.getulus.charondor.gamebody.controller;

import com.getulus.charondor.gamebody.logger.ExceptionLog;
import com.getulus.charondor.gamebody.service.combat.CombatActions;
import com.getulus.charondor.gamebody.service.combat.CombatLogList;
import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CombatController {

    @Autowired
    ExceptionLog exceptionLog;

    @Autowired
    CombatActions combatActions;

    @Autowired
    CombatLogList combatLogList;


    @CrossOrigin(origins = "*")
    @GetMapping("/action/combat")
    public String combat(HttpServletResponse response){
        try {
            response.setStatus(200);
            combatActions.resolveCombat();
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


    @CrossOrigin(origins = "*")
    @GetMapping("/action/combat-log")
    public List<CombatLogTemplate> getCombatLog(HttpServletResponse response){
        try {
            response.setStatus(200);

            if (combatLogList.getCombatLog() == null) {
                return new ArrayList<>();
                }
            return combatLogList.getCombatLog();
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
    @PostMapping("/action/clear-combat-log")
    public String clearCombatLog(HttpServletResponse response){
        try {
            response.setStatus(200);
            combatLogList.setCombatLog(null);
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
