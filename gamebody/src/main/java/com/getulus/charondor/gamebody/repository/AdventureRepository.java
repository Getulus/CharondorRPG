package com.getulus.charondor.gamebody.repository;

import com.getulus.charondor.gamebody.model.advantures.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    Optional<Adventure> getAdventureByName(String name);

}
