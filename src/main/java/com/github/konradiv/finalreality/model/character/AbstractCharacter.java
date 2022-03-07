package com.github.konradiv.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.konradiv.finalreality.controller.handlers.IEventHandler;
import com.github.konradiv.finalreality.model.character.player.AbstractCommonCharacter;
import com.github.konradiv.finalreality.model.character.states.IState;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  public ScheduledExecutorService scheduledExecutor;
  protected int health;
  protected int defense;
  protected IState state = null;
  protected final PropertyChangeSupport enterQueue = new PropertyChangeSupport(this);

  /**
   *  Creates a new Abstract Character with the parameters listed below
   * @param name
   *    name of the new common character
   * @param turnsQueue
   *    the queue with the characters waiting for their turn
   * @param health
   *    health of the new character
   * @param defense
   *    defense of the new character
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int health, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.health = health;
    this.defense = defense;
  }

  /**
   * Adds this character to the turns queue.
   */
  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    enterQueue.firePropertyChange(name+"'s has entered the queue",false,true);
    scheduledExecutor.shutdown();
  }

  /**
   * Adds a listener for when the character has entered the queue
   * @param handler
   *     handles the event of the character entering the queue
   */
  public void addQueueListener(IEventHandler handler){
    enterQueue.addPropertyChangeListener(handler);
  }

  /**
   * Returns the character's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the character's health
   */
  @Override
  public int getHealth() {
    return health;
  }

  /**
   * Sets the character's health to the new value
   *
   * @param health new value of the character's health
   */
  @Override
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Returns the character's defense
   */
  @Override
  public int getDefense() { return defense; }

  /**
   * Sets the state of the character to a new one
   * @param newState
   *     new state of the character
   */
  protected void setState(IState newState){
    state = newState;
    state.setCharacter(this);
  }

  /**
   * Tries to be attacked by another character
   * @param damage
   *     damage to be attack by
   */
  @Override
  public void tryAttacked(int damage){
    state.attacked(damage);
  }

  /**
   * Tries to attack another character
   * @param character
   *     character that tries to attack
   */
  @Override
  public void tryAttack(ICharacter character){
    state.attack(character);
  }

  /**
   * The characters is attacked by the specified value
   * @param damage
   *    Damage of the attack
   */
  @Override
  public void attacked(int damage) {
    health = Math.max(health - Math.max(damage - defense,0), 0 );
    if (health == 0){
      this.die();
    }
  }
}
