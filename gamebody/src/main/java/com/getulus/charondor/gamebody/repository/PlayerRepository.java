package com.getulus.charondor.gamebody.repository;


import com.getulus.charondor.gamebody.model.character.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {


    Optional<Player> getPlayerByName(String name);

    Optional<Player> getPlayerByType(String type);


}
