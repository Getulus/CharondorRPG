package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterList {
    public Monster currentMonster;
    public List<Monster> monsters;
    public MonsterRepository monsterRepository;

}
