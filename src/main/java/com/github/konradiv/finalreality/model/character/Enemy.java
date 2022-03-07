package com.github.konradiv.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.konradiv.finalreality.controller.handlers.IEventHandler;
import com.github.konradiv.finalreality.model.character.states.EnemyAlive;
import com.github.konradiv.finalreality.model.character.states.EnemyDead;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Konrad Ivelic
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attack;

  private final PropertyChangeSupport enemyTurnEvent = new PropertyChangeSupport(this);
  private final PropertyChangeSupport enemyDeathEvent = new PropertyChangeSupport(this);

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int weight, int health, int defense, int attack,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(turnsQueue, name, health, defense);
    this.weight = weight;
    this.attack = attack;
    setState(new EnemyAlive());
  }

  /**
   * Returns enemy's weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns enemy's attack
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Method that makes an enemy wait for their turn depending on their
   * weight
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    var enemy = (Enemy) this;
    scheduledExecutor.schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Attacks the character specified
   *
   * @param character character to be attacked
   */
  @Override
  public void attack(ICharacter character) {
    character.tryAttacked(attack);
  }

  /**
   * The enemy has died, notified the observers and change the state
   */
  public void die(){
    setState(new EnemyDead());
    enemyDeathEvent.firePropertyChange(name+"'s has died",null,this);};

  /**
   * Returns the data of an enemy
   */
  @Override
  public Hashtable<String, Object> getData() {
    Hashtable<String,Object> data = new Hashtable<>();
    data.put("Name:",name);
    data.put("Health:",health);
    data.put("Weight:",weight);
    data.put("Defense:",defense);
    data.put("Attack:",attack);
    return data;
  }

  /**
   * Tries to fire the event that the turn holder is an enemy
   */
  @Override
  public void tryTakeTurn() { state.takeTurn(enemyTurnEvent,name); }

  /**
   * Adds a listener to the event of an enemy death
   * @param handler
   *      handles the death of enemies
   */
  @Override
  public void addDeathListener(IEventHandler handler) {
    enemyDeathEvent.addPropertyChangeListener(handler);
  }

  /**
   * Adds a listener to the event of a taking a turn of an enemy
   * @param handler
   *      handles the turn of enemies
   */
  @Override
  public void addTurnListener(IEventHandler handler) {
    enemyTurnEvent.addPropertyChangeListener(handler);
  }

  /**
   *
   * @param obj
   *        object to check equality with
   * @return if two objects are equal, same class and same name
   */
  @Override
  public boolean equals(final Object obj) {return obj instanceof Enemy && ((Enemy) obj).name == this.name;}

  /**
   * Returns the hash code of the Enemy class
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(Enemy.class);
  }
}
