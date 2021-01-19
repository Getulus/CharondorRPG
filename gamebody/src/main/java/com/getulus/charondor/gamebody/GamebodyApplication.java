package com.getulus.charondor.gamebody;

import com.getulus.charondor.gamebody.model.Item;
import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.model.advantures.Adventure;
import com.getulus.charondor.gamebody.repository.AdventureRepository;
import com.getulus.charondor.gamebody.repository.ItemRepository;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
import com.getulus.charondor.gamebody.service.Adventures.AdventureList;
import com.getulus.charondor.gamebody.service.HTTPConnection;
import com.getulus.charondor.gamebody.service.MonsterList;
import com.getulus.charondor.gamebody.service.PlayerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@EnableEurekaClient
@SpringBootApplication
public class GamebodyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamebodyApplication.class);

    @Autowired
    AdventureRepository adventureRepository;

    @Autowired
    HTTPConnection httpConnection;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    MonsterList monsterList;

    @Autowired
    PlayerList playerList;

    @Autowired
    AdventureList adventureList;

    @Autowired
    ItemRepository itemRepository;


    public static void main(String[] args) {
        SpringApplication.run(GamebodyApplication.class, args);
    }


    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {

            Player getulus = Player.builder()
                    .agility(10)
                    .armor(200)
                    .attackValue(50)
                    .criticalChance(12)
                    .defenseValue(20)
                    .maxHealth(500)
                    .currentHealth(500)
                    .level(1)
                    .magicResistance(10)
                    .stamina(30)
                    .strength(20)
                    .soulEnergy(100)
                    .type("Warrior")
                    .wisdom(5)
                    .name("Getulus")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .build();


            playerRepository.saveAndFlush(getulus);
            playerList.setCurrentPlayer(getulus);
            //playerList.addPlayer(playerRepository.getPlayerByName("Getulus").get());
            System.out.println(playerList.toString());

            Monster werewolf = Monster.builder()
                    .agility(8)
                    .armor(150)
                    .attackValue(40)
                    .criticalChance(20)
                    .defenseValue(12)
                    .maxHealth(200)
                    .currentHealth(200)
                    .level(1)
                    .magicResistance(5)
                    .stamina(25)
                    .strength(30)
                    .soulEnergy(70)
                    .type("Warrior")
                    .wisdom(3)
                    .name("Werewolf")
                    .lootedGold(40)
                    .build();


            monsterRepository.saveAndFlush(werewolf);
            System.out.println(monsterList.toString());

            Monster beastMan = Monster.builder()
                    .agility(30)
                    .armor(230)
                    .attackValue(50)
                    .criticalChance(24)
                    .defenseValue(15)
                    .maxHealth(250)
                    .currentHealth(250)
                    .level(2)
                    .magicResistance(10)
                    .stamina(30)
                    .strength(41)
                    .soulEnergy(80)
                    .type("Warrior")
                    .wisdom(6)
                    .name("Beast Man")
                    .lootedGold(40)
                    .build();


            monsterRepository.saveAndFlush(beastMan);

            Adventure mistyForest = Adventure.builder()
                    .name("Misty Forest")
                    .startLevel(1)
                    .endLevel(3)
                    .build();

            adventureList.setCurrentAdventure(mistyForest);
            adventureRepository.saveAndFlush(mistyForest);
            System.out.println(monsterList.toString());


            Item woodenHammer = Item.builder()
                    .agility(1)
                    .armor(0)
                    .attackValue(10)
                    .criticalChance(0)
                    .defenseValue(0)
                    .dropChance(100)
                    .level(1)
                    .magicResistance(0)
                    .name("Wooden Hammer")
                    .rarity("Common")
                    .slot("Hands")
                    .stamina(0)
                    .strength(1)
                    .wisdom(0)
                    .build();

            itemRepository.saveAndFlush(woodenHammer);

            Item ironSword = Item.builder()
                    .agility(3)
                    .armor(0)
                    .attackValue(15)
                    .criticalChance(0)
                    .defenseValue(0)
                    .dropChance(100)
                    .level(2)
                    .magicResistance(0)
                    .name("Iron Sword")
                    .rarity("Common")
                    .slot("Hands")
                    .stamina(0)
                    .strength(2)
                    .wisdom(0)
                    .build();

            itemRepository.saveAndFlush(ironSword);

        };

    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }

}