package com.getulus.charondor.gamebody;

import com.getulus.charondor.gamebody.model.items.Item;
import com.getulus.charondor.gamebody.model.character.Monster;
import com.getulus.charondor.gamebody.model.character.Player;
import com.getulus.charondor.gamebody.model.advantures.Adventure;
import com.getulus.charondor.gamebody.model.tavern.Quest;
import com.getulus.charondor.gamebody.repository.*;
import com.getulus.charondor.gamebody.service.Adventures.AdventureList;
import com.getulus.charondor.gamebody.service.HTTPConnection;
import com.getulus.charondor.gamebody.service.character.MonsterList;
import com.getulus.charondor.gamebody.service.character.PlayerList;
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

    @Autowired
    QuestRepository questRepository;


    public static void main(String[] args) {
        SpringApplication.run(GamebodyApplication.class, args);
    }


    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {

            //********************
            //PLAYERS
            //********************

            Player warrior = Player.builder()
                    .agility(10)
                    .armor(200)
                    .attackValue(50)
                    .criticalChance(12)
                    .defenseValue(20)
                    .stamina(30)
                    .maxHealth(450)
                    .currentHealth(450)
                    .level(1)
                    .magicResistance(10)
                    .strength(20)
                    .soulEnergy(125)
                    .type("Warrior")
                    .wisdom(5)
                    .name("Getulus")
                    .experienceNeededForNextLevel(80)
                    .gold(200)

                    .experiencePoints(0)
                    .image("/images/warrior.jpeg")
                    .classSymbol("/images/warrioricon.jpg")
                    .build();

            playerRepository.saveAndFlush(warrior);
            playerList.setCurrentPlayer(warrior);

            Player shadowStriker = Player.builder()
                    .agility(20)
                    .armor(150)
                    .attackValue(60)
                    .criticalChance(20)
                    .defenseValue(15)
                    .maxHealth(400)
                    .currentHealth(400)
                    .level(1)
                    .magicResistance(10)
                    .stamina(20)
                    .strength(30)
                    .soulEnergy(110)
                    .type("Shadow Striker")
                    .wisdom(2)
                    .name("Lartimor")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .image("/images/shadowstriker.jpeg")
                    .classSymbol("/images/striker-sign.jpg")
                    .build();

            playerRepository.saveAndFlush(shadowStriker);

            Player druid = Player.builder()
                    .agility(12)
                    .armor(180)
                    .attackValue(40)
                    .criticalChance(10)
                    .defenseValue(30)
                    .maxHealth(410)
                    .currentHealth(410)
                    .level(1)
                    .magicResistance(30)
                    .stamina(22)
                    .strength(30)
                    .soulEnergy(200)
                    .type("Druid")
                    .wisdom(20)
                    .name("Liran")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .image("/images/druid.png")
                    .classSymbol("images/druid-sign.jpg")
                    .build();

            playerRepository.saveAndFlush(druid);


            Player wizard = Player.builder()
                    .agility(10)
                    .armor(120)
                    .attackValue(70)
                    .criticalChance(17)
                    .defenseValue(15)
                    .maxHealth(350)
                    .currentHealth(350)
                    .level(1)
                    .magicResistance(30)
                    .stamina(10)
                    .strength(25)
                    .soulEnergy(250)
                    .type("Wizard")
                    .wisdom(30)
                    .name("Markuth")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .image("/images/wizard.png")
                    .classSymbol("/images/wizard-sign.jpg")
                    .build();

            playerRepository.saveAndFlush(wizard);

            Player trapMaster = Player.builder()
                    .agility(30)
                    .armor(200)
                    .attackValue(40)
                    .criticalChance(17)
                    .defenseValue(25)
                    .maxHealth(400)
                    .currentHealth(400)
                    .level(1)
                    .magicResistance(12)
                    .stamina(20)
                    .strength(26)
                    .soulEnergy(135)
                    .type("Trap Master")
                    .wisdom(7)
                    .name("Septar")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .image("/images/trap-master.jpeg")
                    .classSymbol("/images/trap-sign.jpg")
                    .build();

            playerRepository.saveAndFlush(trapMaster);


            Player necromancer = Player.builder()
                    .agility(10)
                    .armor(200)
                    .attackValue(50)
                    .criticalChance(12)
                    .defenseValue(25)
                    .maxHealth(400)
                    .currentHealth(400)
                    .level(1)
                    .magicResistance(40)
                    .stamina(20)
                    .strength(45)
                    .soulEnergy(200)
                    .type("Necromancer")
                    .wisdom(20)
                    .name("Berlevac")
                    .experienceNeededForNextLevel(80)
                    .gold(0)
                    .experiencePoints(0)
                    .image("/images/necromancer.jpeg")
                    .classSymbol("/images/necromancer-sign.jpg")
                    .build();

            playerRepository.saveAndFlush(necromancer);

            //********************
            //MONSTERS
            //********************

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
                    .image("/images/werewolf.jpeg")
                    .experience(30)
                    .build();


            monsterRepository.saveAndFlush(werewolf);

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
                    .image("/images/beastman.jpeg")
                    .experience(50)
                    .build();


            monsterRepository.saveAndFlush(beastMan);

            Monster serpent = Monster.builder()
                    .agility(60)
                    .armor(100)
                    .attackValue(60)
                    .criticalChance(30)
                    .defenseValue(15)
                    .maxHealth(180)
                    .currentHealth(180)
                    .level(2)
                    .magicResistance(40)
                    .stamina(35)
                    .strength(45)
                    .soulEnergy(30)
                    .type("Beast")
                    .wisdom(2)
                    .name("Serpent")
                    .lootedGold(50)
                    .image("/images/serpent.jpeg")
                    .experience(60)
                    .build();


            monsterRepository.saveAndFlush(serpent);


            Monster caveSpider = Monster.builder()
                    .agility(60)
                    .armor(200)
                    .attackValue(100)
                    .criticalChance(30)
                    .defenseValue(35)
                    .maxHealth(350)
                    .currentHealth(350)
                    .level(3)
                    .magicResistance(30)
                    .stamina(30)
                    .strength(55)
                    .soulEnergy(50)
                    .type("Spider")
                    .wisdom(2)
                    .name("Cave Spider")
                    .lootedGold(80)
                    .image("/images/caveSpider.jpeg")
                    .experience(140)
                    .build();


            monsterRepository.saveAndFlush(caveSpider);


            Monster goblinPyromancer = Monster.builder()
                    .agility(30)
                    .armor(200)
                    .attackValue(150)
                    .criticalChance(20)
                    .defenseValue(20)
                    .maxHealth(200)
                    .currentHealth(200)
                    .level(4)
                    .magicResistance(60)
                    .stamina(30)
                    .strength(60)
                    .soulEnergy(150)
                    .type("Goblin")
                    .wisdom(40)
                    .name("Goblin Pyromancer")
                    .lootedGold(100)
                    .image("/images/goblinPyromancer.png")
                    .experience(200)
                    .build();


            monsterRepository.saveAndFlush(goblinPyromancer);

            Monster giantCentipede = Monster.builder()
                    .agility(70)
                    .armor(300)
                    .attackValue(120)
                    .criticalChance(30)
                    .defenseValue(50)
                    .maxHealth(400)
                    .currentHealth(400)
                    .level(4)
                    .magicResistance(30)
                    .stamina(40)
                    .strength(60)
                    .soulEnergy(50)
                    .type("Spider")
                    .wisdom(5)
                    .name("Giant Centipede")
                    .lootedGold(150)
                    .image("/images/centipede.png")
                    .experience(250)
                    .build();


            monsterRepository.saveAndFlush(giantCentipede);


            Monster cyclops = Monster.builder()
                    .agility(30)
                    .armor(500)
                    .attackValue(70)
                    .criticalChance(20)
                    .defenseValue(40)
                    .maxHealth(500)
                    .currentHealth(500)
                    .level(4)
                    .magicResistance(40)
                    .stamina(50)
                    .strength(40)
                    .soulEnergy(50)
                    .type("Ogre")
                    .wisdom(2)
                    .name("Cyclops")
                    .lootedGold(100)
                    .image("/images/cyclops.jpeg")
                    .experience(230)
                    .build();


            monsterRepository.saveAndFlush(cyclops);


            Monster alphaOgre = Monster.builder()
                    .agility(70)
                    .armor(400)
                    .attackValue(140)
                    .criticalChance(30)
                    .defenseValue(50)
                    .maxHealth(500)
                    .currentHealth(500)
                    .level(6)
                    .magicResistance(50)
                    .stamina(40)
                    .strength(60)
                    .soulEnergy(100)
                    .type("Ogre")
                    .wisdom(15)
                    .name("Alpha Ogre")
                    .lootedGold(200)
                    .image("/images/alphaOgre.jpeg")
                    .experience(400)
                    .build();


            monsterRepository.saveAndFlush(alphaOgre);

            Monster magmaLord = Monster.builder()
                    .agility(150)
                    .armor(1000)
                    .attackValue(300)
                    .criticalChance(20)
                    .defenseValue(200)
                    .maxHealth(1500)
                    .currentHealth(1500)
                    .level(10)
                    .magicResistance(300)
                    .stamina(100)
                    .strength(130)
                    .soulEnergy(300)
                    .type("Ogre")
                    .wisdom(50)
                    .name("Magma Lord")
                    .lootedGold(2000)
                    .image("/images/magmaLord.jpeg")
                    .experience(3000)
                    .build();


            monsterRepository.saveAndFlush(magmaLord);


            //********************
            //ADVENTURES
            //********************

            Adventure mistyForest = Adventure.builder()
                    .name("Misty Forest")
                    .startLevel(1)
                    .endLevel(2)
                    .build();

            adventureRepository.saveAndFlush(mistyForest);


            Adventure spiderCave = Adventure.builder()
                    .name("Spider Cave")
                    .startLevel(3)
                    .endLevel(4)
                    .build();

            adventureRepository.saveAndFlush(spiderCave);

            Adventure landOfOgres = Adventure.builder()
                    .name("Land of Ogres")
                    .startLevel(4)
                    .endLevel(6)
                    .build();

            adventureRepository.saveAndFlush(landOfOgres);

            Adventure lionsHighland = Adventure.builder()
                    .name("Lion's Highland")
                    .startLevel(5)
                    .endLevel(8)
                    .build();

            adventureRepository.saveAndFlush(lionsHighland);


            Adventure towerOfSuffering = Adventure.builder()
                    .name("Tower of Suffering")
                    .startLevel(7)
                    .endLevel(9)
                    .build();

            adventureRepository.saveAndFlush(towerOfSuffering);

            Adventure devilsPit = Adventure.builder()
                    .name("Devil's Pit")
                    .startLevel(10)
                    .endLevel(11)
                    .build();

            adventureRepository.saveAndFlush(devilsPit);


            //********************
            //ITEMS
            //********************

            Item woodenHammer = Item.builder()
                    .agility(1)
                    .armor(0)
                    .attackValue(10)
                    .criticalChance(0)
                    .defenseValue(0)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(0)
                    .name("Wooden Hammer")
                    .rarity("Common")
                    .slot("leftHand")
                    .stamina(0)
                    .strength(1)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/wooden-hammer.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(woodenHammer);


            Item necklaceOfStrength = Item.builder()
                    .agility(0)
                    .armor(0)
                    .attackValue(0)
                    .criticalChance(0)
                    .defenseValue(0)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(0)
                    .name("Necklace of Strength")
                    .rarity("Common")
                    .slot("neck")
                    .stamina(0)
                    .strength(5)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/necklaceOfStrength.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(necklaceOfStrength);

            Item ironShield = Item.builder()
                    .agility(0)
                    .armor(50)
                    .attackValue(0)
                    .criticalChance(0)
                    .defenseValue(15)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(0)
                    .name("Iron Shield")
                    .rarity("Common")
                    .slot("rightHand")
                    .stamina(3)
                    .strength(0)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-shield.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(ironShield);

            Item lightHelmet = Item.builder()
                    .agility(0)
                    .armor(20)
                    .attackValue(0)
                    .criticalChance(0)
                    .defenseValue(5)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(0)
                    .name("Light Helmet")
                    .rarity("Common")
                    .slot("head")
                    .stamina(0)
                    .strength(5)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-helm.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(lightHelmet);


            Item ironGloves = Item.builder()
                    .agility(5)
                    .armor(10)
                    .attackValue(5)
                    .criticalChance(0)
                    .defenseValue(5)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(0)
                    .name("Leather Gloves")
                    .rarity("Common")
                    .slot("gloves")
                    .stamina(0)
                    .strength(0)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-gloves.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(ironGloves);

            Item ironChestplate = Item.builder()
                    .agility(0)
                    .armor(50)
                    .attackValue(0)
                    .criticalChance(0)
                    .defenseValue(5)
                    .dropChance(25)
                    .level(1)
                    .magicResistance(10)
                    .name("Iron Chestplate")
                    .rarity("Common")
                    .slot("chest")
                    .stamina(4)
                    .strength(1)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-chestplate.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(ironChestplate);

            Item ironSword = Item.builder()
                    .agility(3)
                    .armor(0)
                    .attackValue(15)
                    .criticalChance(0)
                    .defenseValue(0)
                    .dropChance(25)
                    .level(2)
                    .magicResistance(0)
                    .name("Iron Sword")
                    .rarity("Common")
                    .slot("leftHand")
                    .stamina(0)
                    .strength(2)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-sword.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(ironSword);

            Item ironBoots = Item.builder()
                    .agility(0)
                    .armor(20)
                    .attackValue(0)
                    .criticalChance(0)
                    .defenseValue(2)
                    .dropChance(25)
                    .level(2)
                    .magicResistance(15)
                    .name("Iron Boots")
                    .rarity("Common")
                    .slot("foot")
                    .stamina(6)
                    .strength(2)
                    .wisdom(0)
                    .equipped(false)
                    .image("/images/iron-boots.png")
                    .gold(50)
                    .build();

            itemRepository.saveAndFlush(ironBoots);



            //********************
            //QUESTS
            //********************


            Quest killSomeWolfs = Quest.builder()
                    .progress(0)
                    .completed(false)
                    .experience(100)
                    .gold(340)
                    .monsterName("Werewolf")
                    .itemName("")
                    .questName("Kill Some Wolfs")
                    .task(3)
                    .build();

            questRepository.saveAndFlush(killSomeWolfs);

            Quest killSomeSerpents = Quest.builder()
                    .progress(0)
                    .completed(false)
                    .experience(200)
                    .gold(200)
                    .monsterName("Serpent")
                    .itemName("")
                    .questName("Kill Some Serpents")
                    .task(3)
                    .build();

            questRepository.saveAndFlush(killSomeSerpents);

            Quest slayTheBeastmen = Quest.builder()
                    .progress(0)
                    .completed(false)
                    .experience(150)
                    .gold(200)
                    .monsterName("Beast Man")
                    .itemName("")
                    .questName("Slay Beastmen")
                    .task(2)
                    .build();

            questRepository.saveAndFlush(slayTheBeastmen);


            Quest killTheAlphaOgre = Quest.builder()
                    .progress(0)
                    .completed(false)
                    .experience(300)
                    .gold(300)
                    .monsterName("Alpha Ogre")
                    .itemName("")
                    .questName("Kill The Alpha")
                    .task(1)
                    .build();

            questRepository.saveAndFlush(killTheAlphaOgre);


            Quest intoTheCave = Quest.builder()
                    .progress(0)
                    .completed(false)
                    .experience(300)
                    .gold(500)
                    .monsterName("Cave Spider")
                    .itemName("")
                    .questName("Into the Cave")
                    .task(4)
                    .build();

            questRepository.saveAndFlush(intoTheCave);

        };

    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }

}