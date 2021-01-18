package com.getulus.charondor.fighting.util;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Util {


    public Random random = new Random();

    public boolean chanceForEvent(double chance) {
        return random.nextInt(100) <= chance;
    }

}
