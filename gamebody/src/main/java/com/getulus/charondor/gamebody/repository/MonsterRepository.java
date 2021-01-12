package com.getulus.charondor.gamebody.repository;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    Optional<Monster> getPlayerByName(String name);
}
