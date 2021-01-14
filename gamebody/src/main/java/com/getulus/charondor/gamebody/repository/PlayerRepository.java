package com.getulus.charondor.gamebody.repository;


import com.getulus.charondor.gamebody.model.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {


    Optional<Player> getPlayerByName(String name);

}
