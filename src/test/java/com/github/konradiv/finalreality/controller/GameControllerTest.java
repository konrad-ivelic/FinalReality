package com.github.konradiv.finalreality.controller;

import com.github.konradiv.finalreality.controller.turnPhases.*;
import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.*;
import com.github.konradiv.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private GameController testController;
    private final int HEALTH = 100;
    private final String NAME = "Name";
    private final int DEFENSE = 10;
    private final int MANA = 100;
    private final int DAMAGE = 30;
    private final int WEIGHT = 20;
    private final int MAGIC_DAMAGE = 30;
    private final int ATTACK = 40;

    @BeforeEach
    void setUp(){
        testController = new GameController();
    }

    @Test
    void creationTest(){
        assert testController.emptyQueue();
        assertEquals(testController.getEnemiesAlive(),0);
        assertEquals(testController.getPlayersAlive(),0);

        /* We check that the player characters are created correctly */
        testController.createKnight("Knight",HEALTH, DEFENSE);
        testController.createThief("Thief",HEALTH, DEFENSE);
        testController.createEngineer("Engineer",HEALTH, DEFENSE);
        testController.createWhiteMage("White Mage",HEALTH, DEFENSE,MANA);
        testController.createBlackMage("Black Mage",HEALTH, DEFENSE,MANA);

        assertEquals(testController.characterName(testController.getPlayerCharacter(0)),"Knight");
        assertEquals(testController.characterName(testController.getPlayerCharacter(1)),"Thief");
        assertEquals(testController.characterName(testController.getPlayerCharacter(2)),"Engineer");
        assertEquals(testController.characterName(testController.getPlayerCharacter(3)),"White Mage");
        assertEquals(testController.characterName(testController.getPlayerCharacter(4)),"Black Mage");

        assertEquals(testController.characterHealth(testController.getPlayerCharacter(0)),HEALTH);
        assertEquals(testController.characterHealth(testController.getPlayerCharacter(1)),HEALTH);
        assertEquals(testController.characterHealth(testController.getPlayerCharacter(2)),HEALTH);
        assertEquals(testController.characterHealth(testController.getPlayerCharacter(3)),HEALTH);
        assertEquals(testController.characterHealth(testController.getPlayerCharacter(4)),HEALTH);

        assertEquals(testController.getPlayerCharacter(0), new Knight("Knight",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacter(1), new Thief("Thief",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacter(2), new Engineer("Engineer",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacter(3), new WhiteMage("White Mage",testController.getTurnsQueue(),HEALTH, DEFENSE,MANA));
        assertEquals(testController.getPlayerCharacter(4), new BlackMage("Black Mage",testController.getTurnsQueue(),HEALTH, DEFENSE,MANA));
        assertEquals(testController.getPlayersAlive(),5);

        assertEquals(testController.getPlayerCharacters().size(),5);
        assertEquals(testController.getPlayerCharacters().get(0), new Knight("Knight",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacters().get(1), new Thief("Thief",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacters().get(2), new Engineer("Engineer",testController.getTurnsQueue(),HEALTH, DEFENSE));
        assertEquals(testController.getPlayerCharacters().get(3), new WhiteMage("White Mage",testController.getTurnsQueue(),HEALTH, DEFENSE,MANA));
        assertEquals(testController.getPlayerCharacters().get(4), new BlackMage("Black Mage",testController.getTurnsQueue(),HEALTH, DEFENSE,MANA));

        /* We check the same for the weapons */
        testController.createAxe("Axe", DAMAGE, WEIGHT);
        testController.createBow("Bow", DAMAGE, WEIGHT);
        testController.createKnife("Knife", DAMAGE, WEIGHT);
        testController.createSword("Sword", DAMAGE, WEIGHT);
        testController.createStaff("Staff", DAMAGE, WEIGHT, MAGIC_DAMAGE);

        assertEquals(testController.getWeapon(0),new Axe("Axe", DAMAGE, WEIGHT));
        assertEquals(testController.getWeapon(1),new Bow("Bow", DAMAGE, WEIGHT));
        assertEquals(testController.getWeapon(2),new Knife("Knife", DAMAGE, WEIGHT));
        assertEquals(testController.getWeapon(3),new Sword("Sword", DAMAGE, WEIGHT));
        assertEquals(testController.getWeapon(4),new Staff("Staff", DAMAGE, WEIGHT, MAGIC_DAMAGE));

        assertEquals(testController.weaponName(testController.getWeapon(0)),"Axe");
        assertEquals(testController.weaponName(testController.getWeapon(1)),"Bow");
        assertEquals(testController.weaponName(testController.getWeapon(2)),"Knife");
        assertEquals(testController.weaponName(testController.getWeapon(3)),"Sword");
        assertEquals(testController.weaponName(testController.getWeapon(4)),"Staff");

        assertEquals(testController.getPlayerInventory().size(),5);
        assertEquals(testController.getPlayerInventory().get(0),new Axe("Axe", DAMAGE, WEIGHT));
        assertEquals(testController.getPlayerInventory().get(1),new Bow("Bow", DAMAGE, WEIGHT));
        assertEquals(testController.getPlayerInventory().get(2),new Knife("Knife", DAMAGE, WEIGHT));
        assertEquals(testController.getPlayerInventory().get(3),new Sword("Sword", DAMAGE, WEIGHT));
        assertEquals(testController.getPlayerInventory().get(4),new Staff("Staff", DAMAGE, WEIGHT, MAGIC_DAMAGE));

        testController.equipWeapon(testController.getPlayerCharacter(0),testController.getWeapon(0));
        testController.equipWeapon(testController.getPlayerCharacter(1),testController.getWeapon(2));
        testController.equipWeapon(testController.getPlayerCharacter(2),testController.getWeapon(1));
        testController.equipWeapon(testController.getPlayerCharacter(3),testController.getWeapon(4));
        testController.equipWeapon(testController.getPlayerCharacter(4),testController.getWeapon(4));

        assertEquals(testController.characterWeapon(testController.getPlayerCharacter(0)),testController.getWeapon(0));
        assertEquals(testController.characterWeapon(testController.getPlayerCharacter(1)),testController.getWeapon(2));
        assertEquals(testController.characterWeapon(testController.getPlayerCharacter(2)),testController.getWeapon(1));
        assertEquals(testController.characterWeapon(testController.getPlayerCharacter(3)),testController.getWeapon(4));
        assertEquals(testController.characterWeapon(testController.getPlayerCharacter(4)),testController.getWeapon(4));

        /* We check the same for the enemy characters */
        testController.createEnemy("Goblin",HEALTH, WEIGHT, DEFENSE, ATTACK);
        testController.createEnemy("Giant",HEALTH*2, WEIGHT *4, DEFENSE *3, ATTACK);

        assertEquals(testController.characterName(testController.getEnemyCharacter(0)),"Goblin");
        assertEquals(testController.characterName(testController.getEnemyCharacter(1)),"Giant");

        assertEquals(testController.characterHealth(testController.getEnemyCharacter(0)),HEALTH);
        assertEquals(testController.characterHealth(testController.getEnemyCharacter(0)),HEALTH);

        assertEquals(testController.getEnemyCharacter(0),new Enemy("Goblin",HEALTH, WEIGHT, DEFENSE, ATTACK,testController.getTurnsQueue()));
        assertEquals(testController.getEnemyCharacter(1),new Enemy("Giant",HEALTH*2, WEIGHT *4, DEFENSE *3, ATTACK,testController.getTurnsQueue()));
        assertEquals(testController.getEnemiesAlive(),2);

        assertEquals(testController.getEnemyCharacters().size(),2);
        assertEquals(testController.getEnemyCharacters().get(0),new Enemy("Goblin",HEALTH, WEIGHT, DEFENSE, ATTACK,testController.getTurnsQueue()));
        assertEquals(testController.getEnemyCharacters().get(1),new Enemy("Giant",HEALTH*2, WEIGHT *4, DEFENSE *3, ATTACK,testController.getTurnsQueue()));
    }

    @Test
    void basicOperationsTest(){
        testController.createKnight(NAME,HEALTH, DEFENSE);
        testController.createWhiteMage(NAME,HEALTH, DEFENSE,MANA);

        testController.createSword(NAME, DAMAGE, WEIGHT);
        testController.createStaff(NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);

        testController.createEnemy("Goblin",HEALTH, WEIGHT, DEFENSE, ATTACK);
        testController.createEnemy("Giant",HEALTH, WEIGHT, DEFENSE, ATTACK);


        IPlayerCharacter testKnight = testController.getPlayerCharacter(0);
        IPlayerCharacter testWhiteMage = testController.getPlayerCharacter(1);
        ICharacter testGoblin = testController.getEnemyCharacter(0);
        ICharacter testGiant = testController.getEnemyCharacter(1);
        IWeapon testSword = testController.getWeapon(0);
        IWeapon testStaff = testController.getWeapon(1);

        Hashtable<String,Object> KnightData = testController.characterData(testKnight);
        assertEquals(KnightData.get("Name:"),NAME);
        assertEquals(KnightData.get("Health:"),HEALTH);
        assertEquals(KnightData.get("Defense:"),DEFENSE);

        Hashtable<String,Object> WhiteMageData = testController.characterData(testWhiteMage);
        assertEquals(WhiteMageData.get("Name:"),NAME);
        assertEquals(WhiteMageData.get("Health:"),HEALTH);
        assertEquals(WhiteMageData.get("Defense:"),DEFENSE);
        assertEquals(WhiteMageData.get("Mana:"),MANA);

        Hashtable<String,Object> GoblinData = testController.characterData(testGoblin);
        assertEquals(GoblinData.get("Name:"),"Goblin");
        assertEquals(GoblinData.get("Health:"),HEALTH);
        assertEquals(GoblinData.get("Weight:"),WEIGHT);
        assertEquals(GoblinData.get("Defense:"),DEFENSE);
        assertEquals(GoblinData.get("Attack:"),ATTACK);

        Hashtable<String,Object> GiantData = testController.characterData(testGiant);
        assertEquals(GiantData.get("Name:"),"Giant");
        assertEquals(GiantData.get("Health:"),HEALTH);
        assertEquals(GiantData.get("Weight:"),WEIGHT);
        assertEquals(GiantData.get("Defense:"),DEFENSE);
        assertEquals(GiantData.get("Attack:"),ATTACK);

        testController.equipWeapon(testKnight,testSword);
        assertEquals(testKnight.getEquippedWeapon(),testSword);

        testController.equipWeapon(testWhiteMage,testStaff);
        assertEquals(testWhiteMage.getEquippedWeapon(),testStaff);

        testController.attackCharacter(testGoblin,testKnight);
        assertEquals(testKnight.getHealth(),70);

        testController.attackCharacter(testGoblin,testWhiteMage);
        assertEquals(testWhiteMage.getHealth(),70);

        assertEquals(testController.getPlayersAlive(),2);
        assert !testController.playerLoses();

        testKnight.die();
        assertEquals(testController.getPlayersAlive(),1);
        assert !testController.playerLoses();
        testWhiteMage.die();
        assertEquals(testController.getPlayersAlive(),0);
        assert testController.playerLoses();
        /* Try to take the turn while being dead */
        testWhiteMage.tryTakeTurn();
        testKnight.tryTakeTurn();

        assertEquals(testController.getEnemiesAlive(),2);
        assert !testController.playerWins();
        testGoblin.die();
        assertEquals(testController.getEnemiesAlive(),1);
        assert !testController.playerWins();
        testGiant.die();
        assertEquals(testController.getEnemiesAlive(),0);
        assert testController.playerWins();
        /* Try to take the turn while being dead */
        testGoblin.tryTakeTurn();
    }

    @Test
    void turnTest(){
        testController.createEngineer(NAME,HEALTH,DEFENSE);
        testController.createBlackMage(NAME,HEALTH,DEFENSE,MANA);

        testController.createKnife(NAME,DAMAGE,WEIGHT-10);
        testController.createBow(NAME,DAMAGE,WEIGHT);
        testController.createAxe(NAME,DAMAGE*2,WEIGHT);

        IPlayerCharacter testEngineer = testController.getPlayerCharacter(0);
        IPlayerCharacter testBlackMage = testController.getPlayerCharacter(1);

        IWeapon testKnife = testController.getWeapon(0);
        IWeapon testBow = testController.getWeapon(1);
        IWeapon testAxe = testController.getWeapon(2);

        testController.equipWeapon(testEngineer,testBow);
        testController.equipWeapon(testBlackMage,testKnife);

        testController.createEnemy("Goblin",HEALTH,WEIGHT+10,DEFENSE,ATTACK);
        testController.createEnemy("Giant",HEALTH,WEIGHT*2,DEFENSE,ATTACK);

        ICharacter testGoblin = testController.getEnemyCharacter(0);
        ICharacter testGiant = testController.getEnemyCharacter(1);

        /* We add al characters to the queue to start*/
        testController.addAllToQueue();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're checking that all characters are waiting their due time
            // to enter the queue
            Thread.sleep(900);
            assertEquals(testController.turnsSize(), 0);
            Thread.sleep(200);
            assertEquals(testController.turnsSize(), 1);
            Thread.sleep(700);
            assertEquals(testController.turnsSize(), 1);
            Thread.sleep(300);
            assertEquals(testController.turnsSize(), 2);
            Thread.sleep(700);
            assertEquals(testController.turnsSize(), 2);
            Thread.sleep(300);
            assertEquals(testController.turnsSize(), 3);
            Thread.sleep(600);
            assertEquals(testController.turnsSize(), 3);
            Thread.sleep(400);
            assertEquals(testController.turnsSize(), 4);
            /* Black Mage: First
             * Engineer: Second
             * Goblin: Third
             * Giant: Fourth
             */

            /* List that holds the health of the player characters
            * this is used to check the random attacks of enemies */
            List<Integer> playerHealthList = new ArrayList<>();
            playerHealthList.add(testEngineer.getHealth());
            playerHealthList.add(testBlackMage.getHealth());

            /* Check that we start in initial phase */
            assertEquals(testController.getPhase(), new InitialPhase());
            assertNotEquals(testController.getPhase(),new WaitPhase());

            /* We start the turn, the black mage starts */
            testController.phaseActivity();
            assertEquals(testController.getPhase(), new PlayerTurnPhase(testBlackMage));
            /* We will check that is not equal that the one holding the turn is the black mage
            * or the engineer or the giant*/
            assertNotEquals(testController.getPhase(),new PlayerTurnPhase(testEngineer));
            assertNotEquals(testController.getPhase(),new EnemyTurnPhase(testGiant));

            /* We attack the giant */
            testController.attackCharacter(testController.getTurnHolder(),testGiant);
            testController.phaseActivity();

            assertEquals(80, testGiant.getHealth());
            assertEquals(testController.getPhase(), new WaitPhase());

            /* Since there are characters in the queue, it should go to initial phase*/
            testController.phaseActivity();
            assertEquals(testController.getPhase(), new InitialPhase());

            /* Its the turn of the Engineer */
            testController.phaseActivity();
            assertEquals(testController.getPhase(), new PlayerTurnPhase(testEngineer));
            assertNotEquals(testController.getPhase(),new PlayerTurnPhase(testBlackMage));

            /* We equip an axe and attack the goblin */
            testController.equipWeapon((IPlayerCharacter) testController.getTurnHolder(),testAxe);
            assertEquals(testEngineer.getEquippedWeapon(), testAxe);

            testController.attackCharacter(testController.getTurnHolder(),testGoblin);
            testController.phaseActivity();

            /*
             * If the axe was equipped, the damage is 50 and not 20
             */
            assertEquals(50, testGoblin.getHealth());
            assertEquals(testController.getPhase(), new WaitPhase());

            /* Since there are characters in the queue, it should go to initial phase*/
            testController.phaseActivity();
            assertEquals(testController.getPhase(), new InitialPhase());

            /* Its the goblins turn */
            testController.phaseActivity();
            assertEquals(testController.getPhase(), new EnemyTurnPhase(testGoblin));
            /* We will check that is not equal that the one holding the turn is the goblin
             * or the giant or the engineer*/
            assertNotEquals(testController.getPhase(),new EnemyTurnPhase(testGiant));
            assertNotEquals(testController.getPhase(),new PlayerTurnPhase(testEngineer));

            testController.phaseActivity();
            /* We check that the Goblin attacked one of the player characters at random */
            assert (playerHealthList.get(0) > testEngineer.getHealth() || playerHealthList.get(1) > testBlackMage.getHealth());
            playerHealthList.set(0,testEngineer.getHealth());
            playerHealthList.set(1,testBlackMage.getHealth());
            assertEquals(testController.getPhase(),new WaitPhase());

            /* Since there are characters in the queue, it should go to initial phase*/
            testController.phaseActivity();
            assertEquals(testController.getPhase(),new InitialPhase());

            /* Its the giants turn */
            testController.phaseActivity();
            assertEquals(testController.getPhase(),new EnemyTurnPhase(testGiant));
            assertNotEquals(testController.getPhase(),new EnemyTurnPhase(testGoblin));

            testController.phaseActivity();
            /* We check that the Giant attacked one of the player characters at random */
            assert (playerHealthList.get(0) > testEngineer.getHealth() || playerHealthList.get(1) > testBlackMage.getHealth());
            playerHealthList.set(0,testEngineer.getHealth());
            playerHealthList.set(1,testBlackMage.getHealth());
            assertEquals(testController.getPhase(),new WaitPhase());

            /* Since nobody is in the queue it should stay in wait phase */
            testController.phaseActivity();
            assertEquals(testController.getPhase(),new WaitPhase());

            /* We wait for the character to enter the queue */
            Thread.sleep(1200);

            assertEquals(1,testController.turnsSize());
            /* When the first character enters the queue, it should change to initial phase */
            assertEquals(testController.getPhase(),new InitialPhase());

            /* We check that a dead character is not added to the queue */
            testBlackMage.die();

            testController.startGame();
            assertEquals(3,testController.turnsSize());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void InvalidTransactionExceptionTest(){
        InvalidTransactionException e;
        Knight testKnight = new Knight(NAME,testController.getTurnsQueue(),HEALTH,DEFENSE);
        Enemy testEnemy = new Enemy(NAME,WEIGHT,HEALTH,DEFENSE,ATTACK,testController.getTurnsQueue());

        /* We will test the exceptions thrown by the initial phase */
        InitialPhase initialPhase = new InitialPhase();

        e = assertThrows(InvalidTransactionException.class,initialPhase::toInitialPhase);
        assertEquals(e.getMessage(),"Can't change to initial phase");

        e = assertThrows(InvalidTransactionException.class,initialPhase::toWaitPhase);
        assertEquals(e.getMessage(),"Can't change to wait phase");

        /* We will test the exceptions thrown by the player turn phase */
        PlayerTurnPhase playerTurnPhase = new PlayerTurnPhase(testKnight);

        e = assertThrows(InvalidTransactionException.class,playerTurnPhase::toInitialPhase);
        assertEquals(e.getMessage(),"Can't change to initial phase");

        e = assertThrows(InvalidTransactionException.class, () -> playerTurnPhase.toPlayerTurnPhase(testKnight));
        assertEquals(e.getMessage(),"Can't change to player turn phase");

        e = assertThrows(InvalidTransactionException.class, () -> playerTurnPhase.toEnemyTurnPhase(testEnemy));
        assertEquals(e.getMessage(),"Can't change to enemy turn phase");

        /* We will test the exceptions thrown by the enemy turn phase */
        EnemyTurnPhase enemyTurnPhase = new EnemyTurnPhase(testEnemy);

        e = assertThrows(InvalidTransactionException.class,enemyTurnPhase::toInitialPhase);
        assertEquals(e.getMessage(),"Can't change to initial phase");

        e = assertThrows(InvalidTransactionException.class, () -> enemyTurnPhase.toPlayerTurnPhase(testKnight));
        assertEquals(e.getMessage(),"Can't change to player turn phase");

        e = assertThrows(InvalidTransactionException.class, () -> enemyTurnPhase.toEnemyTurnPhase(testEnemy));
        assertEquals(e.getMessage(),"Can't change to enemy turn phase");

        /* We will test the exceptions thrown by the wai phase */
        WaitPhase waitPhase = new WaitPhase();

        e = assertThrows(InvalidTransactionException.class,waitPhase::toWaitPhase);
        assertEquals(e.getMessage(),"Can't change to wait phase");

        e = assertThrows(InvalidTransactionException.class, () -> waitPhase.toPlayerTurnPhase(testKnight));
        assertEquals(e.getMessage(),"Can't change to player turn phase");

        e = assertThrows(InvalidTransactionException.class, () -> waitPhase.toEnemyTurnPhase(testEnemy));
        assertEquals(e.getMessage(),"Can't change to enemy turn phase");
    }
}
