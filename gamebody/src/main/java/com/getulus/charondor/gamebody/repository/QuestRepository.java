package com.getulus.charondor.gamebody.repository;

import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    Quest getQuestByQuestNameAndPlayerIsNull(String questName);

    List<Quest> getQuestsByPlayerAndCompleted(Player player, Boolean completed);

    List<Quest> getQuestsByPlayerIsNull();

}
