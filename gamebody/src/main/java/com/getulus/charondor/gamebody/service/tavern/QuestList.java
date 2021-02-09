package com.getulus.charondor.gamebody.service.tavern;


import com.getulus.charondor.gamebody.model.tavern.Quest;
import com.getulus.charondor.gamebody.repository.QuestRepository;
import com.getulus.charondor.gamebody.service.character.MonsterList;
import com.getulus.charondor.gamebody.service.character.PlayerActions;
import com.getulus.charondor.gamebody.service.character.PlayerList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestList {

    @Autowired
    MonsterList monsterList;

    @Autowired
    PlayerList playerList;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    PlayerActions playerActions;

    private List<Quest> availableQuests;


    public void acceptQuest(String questName){
        Quest currentQuest = questRepository.getQuestByQuestNameAndPlayerIsNull(questName);

        Quest acceptedQuest = Quest.builder()
                .completed(false)
                .experience(currentQuest.getExperience())
                .gold(currentQuest.getGold())
                .itemName(currentQuest.getItemName())
                .monsterName(currentQuest.getMonsterName())
                .progress(0)
                .task(currentQuest.getTask())
                .questName(currentQuest.getQuestName())
                .build();

        availableQuests.remove(currentQuest);

        playerList.getCurrentPlayer().getQuests().add(acceptedQuest);
        playerList.savePlayer();

    }


    public void sortQuests(){
        if (availableQuests == null ) {
            availableQuests = questRepository.getQuestsByPlayerIsNull();
        }
    }

    public void progressQuest() {
        String monsterName = monsterList.getCurrentMonster().getName();

        for (Quest quest : playerList.getCurrentPlayer().getQuests()) {
            if (monsterName.equals(quest.getMonsterName())) {
                quest.setProgress(quest.getProgress() + 1);
                if (quest.isCompleted()) {
                    playerActions.completeQuest(quest);
                }
            }
        }

    }



}
