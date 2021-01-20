package com.getulus.charondor.gamebody.service;

import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.model.Skill;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import com.getulus.charondor.gamebody.templates.CombatLogTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerActions implements CharacterActions{

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerList playerList;

    @Autowired
    MonsterList monsterList;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public void useSkill(Skill skill) {

    }

    @Override
    public boolean isSkillAvailable(Skill skill) {
        return false;
    }

    @Override
    public boolean isDead() {
        return playerList.getCurrentPlayer().getCurrentHealth() <= 0;
    }

    public void regenerate() {
        playerList.getCurrentPlayer().setCurrentHealth(playerList.getCurrentPlayer().getMaxHealth());
    }


    public void earnExperience(){
        double monsterExp = 50;
        double playerExp = playerList.getCurrentPlayer().getExperiencePoints();
        double needForNexLevel = playerList.getCurrentPlayer().getExperienceNeededForNextLevel();
        double allExp = monsterExp + playerExp;

        if (allExp > needForNexLevel) {
            leveling();
            playerList.getCurrentPlayer().setExperiencePoints(allExp - needForNexLevel);
        } else {
            playerList.getCurrentPlayer().setExperiencePoints(allExp);
        }
    }


    public void earnGold(){
        double playerGold = playerList.getCurrentPlayer().getGold();
        double monsterGold = monsterList.getCurrentMonster().getLootedGold();
        double sumGold = playerGold + monsterGold;

        playerList.getCurrentPlayer().setGold(sumGold);

    }

    public void loot(List<Item> loot){
        for (int i = 0; i < loot.size(); i++) {
            Item currentItem = loot.get(i);
            Item lootedItem = Item.builder()
                    .agility(currentItem.getAgility())
                    .armor(currentItem.getArmor())
                    .attackValue(currentItem.getAttackValue())
                    .criticalChance(currentItem.getCriticalChance())
                    .defenseValue(currentItem.getDefenseValue())
                    .dropChance(currentItem.getDropChance())
                    .level(currentItem.getLevel())
                    .magicResistance(currentItem.getMagicResistance())
                    .name(currentItem.getName())
                    .player(playerList.getCurrentPlayer())
                    .rarity(currentItem.getRarity())
                    .slot(currentItem.getSlot())
                    .stamina(currentItem.getStamina())
                    .strength(currentItem.getStrength())
                    .wisdom(currentItem.getWisdom())
                    .image(currentItem.getImage())
                    .equipped(false)
                    .build();

            itemRepository.save(lootedItem);
            playerList.getCurrentPlayer().addItemToInventory(lootedItem);
        }

    }

    public void retreat(){

    }

    public void equipItem(){

    }

    private void leveling() {
        playerList.getCurrentPlayer().increaseAttributesByLeveling();
    }


}
