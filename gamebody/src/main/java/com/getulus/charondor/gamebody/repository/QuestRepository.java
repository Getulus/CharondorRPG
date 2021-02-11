package com.getulus.charondor.gamebody.repository;

import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    Quest getQuestByQuestNameAndPlayerIsNull(String questName);

    List<Quest> getQuestsByPlayerAndCompleted(Player player, Boolean completed);

    Quest getQuestByQuestNameAndCompletedIsTrue(String questName);

    List<Quest> getQuestsByPlayerIsNull();

    Optional<List<Quest>> getQuestsByPlayer(Player player);

    void deleteByQuestNameAndCompletedIsTrue(String questName);

}
