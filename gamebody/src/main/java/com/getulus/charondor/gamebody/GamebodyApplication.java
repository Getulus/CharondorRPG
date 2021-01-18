package com.getulus.charondor.gamebody;

import com.getulus.charondor.gamebody.model.Monster;
import com.getulus.charondor.gamebody.model.Player;
import com.getulus.charondor.gamebody.repository.MonsterRepository;
import com.getulus.charondor.gamebody.repository.PlayerRepository;
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
    HTTPConnection httpConnection;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    MonsterList monsterList;

    @Autowired
    PlayerList playerList;


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

        };

    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }

}