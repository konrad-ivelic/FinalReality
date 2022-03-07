package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeSupport;

/**
 * Holds the behaviour of an alive enemy
 */
public class EnemyAlive extends EnemyState{

    public EnemyAlive() {
        super();
    }

    /**
     * Receives the attack because he is alive
     * @param damage
     *     damage to be received
     */
    @Override
    public void attacked(int damage) {enemy.attacked(damage);}

    /**
     * Attacks the given character because he is alive
     * @param attackedCharacter
     *     character to be attacked
     */
    @Override
    public void attack(ICharacter attackedCharacter) {enemy.attack(attackedCharacter);}

    /**
     * Fire the event that the turn holder is an enemy
     */
    @Override
    public void takeTurn(PropertyChangeSupport property, String name) { property.firePropertyChange(name+"'s turn",null,enemy); }
}
