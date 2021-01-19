package com.getulus.charondor.gamebody.repository;


import com.getulus.charondor.gamebody.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> getItemByName(String name);

    List<Item> getItemByLevelAndPlayer_IDIsNull(Double level);

    List<Item> getItemByPlayer_ID(Long ID);

}
