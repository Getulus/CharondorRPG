package com.getulus.charondor.gamebody.repository;


import com.getulus.charondor.gamebody.model.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> getItemByName(String name);

    List<Item> getItemByLevelAndPlayer_IDIsNull(Double level);

    List<Item> getItemByPlayer_IDAndEquipped(Long ID, Boolean equipped);

    Optional<Item> getItemByItemID(Long ID);

    Optional<Item> getItemBySlotAndEquipped(String slot, Boolean equipped);

}
