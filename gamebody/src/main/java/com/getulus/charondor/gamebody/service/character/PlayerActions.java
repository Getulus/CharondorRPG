package com.getulus.charondor.gamebody.service.character;

import com.getulus.charondor.gamebody.model.items.Item;
import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.character.Skills.Skill;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Player currentPlayer = playerList.getCurrentPlayer();
        currentPlayer.setCurrentHealth(currentPlayer.getMaxHealth());
        playerRepository.save(currentPlayer);
    }


    public void earnExperience(Double expGot){
        double playerExp = playerList.getCurrentPlayer().getExperiencePoints();
        double needForNexLevel = playerList.getCurrentPlayer().getExperienceNeededForNextLevel();
        double allExp = expGot + playerExp;

        if (allExp > needForNexLevel) {
            leveling();
            playerList.getCurrentPlayer().setExperiencePoints(allExp - needForNexLevel);
        } else {
            playerList.getCurrentPlayer().setExperiencePoints(allExp);
        }
    }

    private void checkIfLevelUp() {

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
                    .gold(currentItem.getGold())
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

    public void completeQuest(Quest completedQuest){
        System.out.println(completedQuest.toString());
        earnExperience(completedQuest.getExperience());
        playerList.getCurrentPlayer().setGold(playerList.getCurrentPlayer().getGold() + completedQuest.getGold());

    }

    public void removeItemFromInventory(Item currentItem) {
        Player currentPlayer = playerList.getCurrentPlayer();

        for (Item item : currentPlayer.getItems()) {
            if (item.getItemID() == currentItem.getItemID()) {
                currentPlayer.setGold(currentPlayer.getGold() + item.getGold());
                currentPlayer.getItems().remove(item);
                break;
            }
        }
        playerRepository.save(currentPlayer);
    }


}
