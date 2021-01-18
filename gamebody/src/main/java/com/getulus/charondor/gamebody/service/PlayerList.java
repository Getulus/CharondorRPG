package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerList {

    @Autowired
    PlayerRepository playerRepository;


    private Player currentPlayer;
    private Monster currentEnemy;
    private List<Player> players;


    public void addPlayer(Player player){
        players.add(player);
    }

}
