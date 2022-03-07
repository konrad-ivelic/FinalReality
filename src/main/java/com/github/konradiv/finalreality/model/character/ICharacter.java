package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.controller.handlers.IEventHandler;

import java.beans.PropertyChangeSupport;
import java.util.Hashtable;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's health
   */
  int getHealth();

  /**
   * Sets the character's health to the new value
   * @param health
   *    new value of the character's health
   */
  void setHealth(int health);

  /**
   * Returns this character's defense
   */
  int getDefense();

  /**
   * Tries to attack another character
   */
  void tryAttack(ICharacter character);

  /**
   * Attacks another character
   */
  void attack(ICharacter character);

  /**
   * Tries to get attacked by another character
   */
  void tryAttacked(int damage);

  /**
   * Receives the attack from another character
   */
  void attacked(int damage);

  /**
   * Declares that the character has died
   */
  void die();

  /**
   * Returns all the data of the character organized in a hashtable
   */
  Hashtable<String, Object> getData();

  /**
   * Try to takes the turn, notifying the controller that he is the one taking it
   */
  void tryTakeTurn();

  /**
   * Adds a turn listener to the character
   * @param handler
   *     handler of the event
   */
  void addTurnListener(IEventHandler handler);

  /**
   * Adds a death listener to the character
   * @param handler
   *     handler of the event
   */
  void addDeathListener(IEventHandler handler);

  /**
   * Adds a queue listener to the character
   * @param handler
   *     handler of the event
   */
  void addQueueListener(IEventHandler handler);

  /**
   * Adds the character directly to the queue
   */
  void addToQueue();
}
