package com.getulus.charondor.actions.controller;


import com.getulus.charondor.actions.ExceptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class FightController {

    @Autowired
    ExceptionLog exceptionLog;

    @CrossOrigin(origins = "*")
    @GetMapping("/fight/all-damage")
    public int getAllDamage(HttpServletResponse response){
        try {
            response.setStatus(200);
            return 100;
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
