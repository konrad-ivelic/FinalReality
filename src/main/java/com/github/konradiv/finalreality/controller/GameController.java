package com.github.konradiv.finalreality.controller;

import com.github.konradiv.finalreality.controller.handlers.*;
import com.github.konradiv.finalreality.controller.turnPhases.ITurnPhase;
import com.github.konradiv.finalreality.controller.turnPhases.InitialPhase;
import com.github.konradiv.finalreality.controller.turnPhases.InvalidTransactionException;
import com.github.konradiv.finalreality.controller.turnPhases.WaitPhase;
import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.*;
import com.github.konradiv.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Holds the behaviour of the controller of the game, which operates with the user to
 * handle the model of the game.
 */
public class GameController {
    private final BlockingQueue<ICharacter> turns;
    private final List<IPlayerCharacter> PlayerCharacters;
    private final List<ICharacter> EnemyCharacters;
    private final List<IWeapon> PlayerInventory;
    private final IEventHandler enemyTurnHandler = new EnemyTurnHandler(this);
    private final IEventHandler playerTurnHandler = new PlayerTurnHandler(this);
    private final IEventHandler playerDeathHandler = new PlayerDeathHandler(this);
    private final IEventHandler enemyDeathHandler = new EnemyDeathHandler(this);
    private final IEventHandler queueEnterHandler = new AddedToQueueHandler(this);
    private ICharacter turnHolder;
    private int aliveEnemies = 0;
    private int alivePlayerCharacters = 0;

    private ITurnPhase turnPhase;

    /**
     * Creates a new controller for the game
     */
    public GameController() {
        turns = new LinkedBlockingQueue<>();
        PlayerCharacters = new ArrayList<>();
        EnemyCharacters = new ArrayList<>();
        PlayerInventory = new ArrayList<>();
        setPhase(new InitialPhase());
    }

    /**
     * Creates a Knight for the player characters
     *
     * @param name    name of the character
     * @param health  health of the character
     * @param defense defense of the character
     */
    public void createKnight(String name, int health, int defense) {
        Knight knight = new Knight(name, turns, health, defense);
        PlayerCharacters.add(knight);
        alivePlayerCharacters += 1;
        knight.addQueueListener(queueEnterHandler);
        knight.addDeathListener(playerDeathHandler);
        knight.addTurnListener(playerTurnHandler);
    }

    /**
     * Creates a Engineer for the player characters
     *
     * @param name    name of the character
     * @param health  health of the character
     * @param defense defense of the character
     */
    public void createEngineer(String name, int health, int defense) {
        Engineer engineer = new Engineer(name, turns, health, defense);
        PlayerCharacters.add(engineer);
        alivePlayerCharacters += 1;
        engineer.addQueueListener(queueEnterHandler);
        engineer.addDeathListener(playerDeathHandler);
        engineer.addTurnListener(playerTurnHandler);
    }

    /**
     * Creates a Thief for the player characters
     *
     * @param name    name of the character
     * @param health  health of the character
     * @param defense defense of the character
     */
    public void createThief(String name, int health, int defense) {
        Thief thief = new Thief(name, turns, health, defense);
        PlayerCharacters.add(thief);
        alivePlayerCharacters += 1;
        thief.addQueueListener(queueEnterHandler);
        thief.addDeathListener(playerDeathHandler);
        thief.addTurnListener(playerTurnHandler);
    }

    /**
     * Creates a White Mage for the player characters
     *
     * @param name    name of the character
     * @param health  health of the character
     * @param defense defense of the character
     * @param mana    mana of the character
     */
    public void createWhiteMage(String name, int health, int defense, int mana) {
        WhiteMage whiteMage = new WhiteMage(name, turns, health, defense, mana);
        PlayerCharacters.add(whiteMage);
        alivePlayerCharacters += 1;
        whiteMage.addQueueListener(queueEnterHandler);
        whiteMage.addDeathListener(playerDeathHandler);
        whiteMage.addTurnListener(playerTurnHandler);
    }

    /**
     * Creates a Black Mage for the player characters
     *
     * @param name    name of the character
     * @param health  health of the character
     * @param defense defense of the character
     * @param mana    mana of the character
     */
    public void createBlackMage(String name, int health, int defense, int mana) {
        BlackMage blackMage = new BlackMage(name, turns, health, defense, mana);
        PlayerCharacters.add(blackMage);
        alivePlayerCharacters += 1;
        blackMage.addQueueListener(queueEnterHandler);
        blackMage.addDeathListener(playerDeathHandler);
        blackMage.addTurnListener(playerTurnHandler);
    }

    /**
     * Creates a Enemy for the enemy characters
     *
     * @param name    name of the enemy
     * @param weight  weight of the enemy
     * @param health  health of the enemy
     * @param defense defense of the enemy
     * @param attack  attack of the enemy
     */
    public void createEnemy(String name, int health, int weight, int defense, int attack) {
        Enemy enemy = new Enemy(name, weight, health, defense, attack, turns);
        EnemyCharacters.add(enemy);
        aliveEnemies += 1;
        enemy.addQueueListener(queueEnterHandler);
        enemy.addDeathListener(enemyDeathHandler);
        enemy.addTurnListener(enemyTurnHandler);
    }

    /**
     * Creates an Axe for the player inventory
     *
     * @param name   name of the weapon
     * @param damage damage of the weapon
     * @param weight weight of the weapon
     */
    public void createAxe(String name, int damage, int weight) {
        PlayerInventory.add(new Axe(name, damage, weight));
    }

    /**
     * Creates an Bow for the player inventory
     *
     * @param name   name of the weapon
     * @param damage damage of the weapon
     * @param weight weight of the weapon
     */
    public void createBow(String name, int damage, int weight) {
        PlayerInventory.add(new Bow(name, damage, weight));
    }

    /**
     * Creates an Knife for the player inventory
     *
     * @param name   name of the weapon
     * @param damage damage of the weapon
     * @param weight weight of the weapon
     */
    public void createKnife(String name, int damage, int weight) {
        PlayerInventory.add(new Knife(name, damage, weight));
    }

    /**
     * Creates an Sword for the player inventory
     *
     * @param name   name of the weapon
     * @param damage damage of the weapon
     * @param weight weight of the weapon
     */
    public void createSword(String name, int damage, int weight) {
        PlayerInventory.add(new Sword(name, damage, weight));
    }

    /**
     * Creates an Staff for the player inventory
     *
     * @param name        name of the weapon
     * @param damage      damage of the weapon
     * @param weight      weight of the weapon
     * @param magicDamage magic damage of the staff
     */
    public void createStaff(String name, int damage, int weight, int magicDamage) {
        PlayerInventory.add(new Staff(name, damage, weight, magicDamage));
    }

    /**
     * Returns the list of enemy characters
     */
    public List<ICharacter> getEnemyCharacters() {
        return EnemyCharacters;
    }

    /**
     * Returns the list of player characters
     */
    public List<IPlayerCharacter> getPlayerCharacters() {
        return PlayerCharacters;
    }

    /**
     * Returns the player inventory
     */
    public List<IWeapon> getPlayerInventory() {
        return PlayerInventory;
    }

    /**
     * Returns the player character at the index specified
     *
     * @param index index of the position of the character in the list
     * @return the character that is in the index position
     */
    public IPlayerCharacter getPlayerCharacter(int index) {
        return PlayerCharacters.get(index);
    }

    /**
     * Returns the enemy at the index specified
     *
     * @param index index of the position of the enemy in the list
     * @return the enemy that is in the index position
     */
    public ICharacter getEnemyCharacter(int index) {
        return EnemyCharacters.get(index);
    }

    /**
     * Returns the weapon at the index specified
     *
     * @param index index of the position of the weapon in the inventory
     * @return the weapon that is in the index position
     */
    public IWeapon getWeapon(int index) {
        return PlayerInventory.get(index);
    }

    /**
     * Returns the data of the character at the given time
     *
     * @param character character to get the data from
     * @return hashtable with the organized data
     */
    public Hashtable<String, Object> characterData(ICharacter character) {
        return character.getData();
    }

    /**
     * Returns the name of the character
     * @param character
     *     character to return the name of
     */
    public String characterName(ICharacter character){
        return character.getName();
    }

    /**
     * Returns the health of the character
     * @param character
     *     character to return the health of
     */
    public int characterHealth(ICharacter character) {return character.getHealth();}

    /**
     * Returns the weapon held by a character
     * @param character
     *     character to return the weapon of
     */
    public IWeapon characterWeapon(IPlayerCharacter character) {return character.getEquippedWeapon();}

    /**
     * Returns the name of a weapon
     * @param weapon
     *     weapon to return the name of
     */
    public String weaponName(IWeapon weapon){
        return weapon.getName();
    }

    /**
     * Equips a weapon to the given character
     *
     * @param character character to equip the weapon to
     * @param weapon    weapon to be equipped
     */
    /*
    HEY CAREFUL WITH EQUIPPING AND NOT TAKING OUT OF THE INVENTORY
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        character.tryEquip(weapon);
    }

    /**
     * Attacks an enemy with the character specified
     *
     * @param attackingCharacter character that is attacking
     * @param attackedCharacter  character that is attacked
     */
    public void attackCharacter(ICharacter attackingCharacter, ICharacter attackedCharacter) {
        attackingCharacter.attack(attackedCharacter);
        attackingCharacter.waitTurn();
        resetTurn();
    }

    /**
     * Adds all the characters to the queue
     */
    public void addAllToQueue(){
        for (IPlayerCharacter playerCharacter : PlayerCharacters) {
            playerCharacter.waitTurn();
        }
        for (ICharacter enemyCharacter : EnemyCharacters) {
            enemyCharacter.waitTurn();
        }
    }

    /**
     * Returns the first character in the queue
     */
    public ICharacter pollCharacter(){
        return turns.poll();
    }

    /**
     * Returns the size of the queue
     */
    public int turnsSize(){
        return turns.size();
    }


    /**
     * Sets the turn holder to null
     */
    public void resetTurn(){
        turnHolder = null;
    }

    /**
     * Returns if the queue is empty
     */
    public boolean emptyQueue(){
        return turns.isEmpty();
    }

    /**
     * Returns the turns queue
     */
    public BlockingQueue<ICharacter> getTurnsQueue() {
        return turns;
    }

    /**
     * Sets the actual turn phase for the game
     * @param turnPhase
     *    turn phase to be set at
     */
    public void setPhase(ITurnPhase turnPhase){
        this.turnPhase = turnPhase;
        turnPhase.setController(this);
    }

    /**
     * Returns the actual phase of the game
     */
    public ITurnPhase getPhase(){
        return turnPhase;
    }

    /**
     * Does the activity of the phase that the game is actually on
     */
    public void phaseActivity(){
        turnPhase.phaseActivity();
    }

    /**
     * Receives the signal that a character has entered the queue and goes to
     * initial phase if in the wait phase
     */
    public void characterEntered() throws InvalidTransactionException {
        if (turnPhase.equals(new WaitPhase())){
            turnPhase.toInitialPhase();
        }
    }

    /**
     * Does the turn for an enemy
     *
     * @param enemy enemy that has the turn
     */
    public void enemyTurn(Enemy enemy) throws InvalidTransactionException {
        turnPhase.toEnemyTurnPhase(enemy);
        turnHolder = enemy;
    }

    /**
     * Does the turn for a player character
     *
     * @param character character that has the turn
     */
    public void playerTurn(IPlayerCharacter character) throws InvalidTransactionException {
        turnPhase.toPlayerTurnPhase(character);
        turnHolder = character;
    }

    /**
     * Subtracts one unit from the number of enemies alive
     *
     * @param enemy enemy that died
     */
    public void enemyDeath(Enemy enemy) {
        aliveEnemies -= 1;
        EnemyCharacters.remove(enemy);
        turns.remove(enemy);
        if (aliveEnemies == 0) {
            this.playerWins();
        }
    }

    /**
     * Returns the amount of enemies still alive
     */
    public int getEnemiesAlive() {
        return aliveEnemies;
    }

    /**
     * Subtracts one unit from the number of player characters alive
     *
     * @param character character that died
     */
    public void playerDeath(IPlayerCharacter character) {
        alivePlayerCharacters -= 1;
        turns.remove(character);
        PlayerCharacters.remove(character);
    }

    /**
     * Returns the amount of player characters still alive
     */
    public int getPlayersAlive(){
        return alivePlayerCharacters;
    }

    /**
     * The player has lost
     */
    public boolean playerLoses(){
        return alivePlayerCharacters == 0;
    }

    /**
     * The player has won
     */
    public boolean playerWins(){
        return aliveEnemies == 0;
    }

    /**
     * Returns the actual character holding the turn
     */
    public ICharacter getTurnHolder() {
        return turnHolder;
    }

    /**
     * Starts the game, only for the gui
     */
    public void startGame() {
        turns.addAll(PlayerCharacters);
        turns.addAll(EnemyCharacters);
    }
}
