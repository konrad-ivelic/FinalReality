package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeSupport;

/**
 * Holds the behaviour of a dead Enemy
 */
public class EnemyDead extends EnemyState{

    public EnemyDead() {
        super();
    }

    /**
     * A dead enemy cannot be attacked
     */
    @Override
    public void attacked(int damage) { }

    /**
     * A dead enemy cannot attack
     */
    @Override
    public void attack(ICharacter attackedCharacter) { }

    /**
     * A dead enemy cannot take his turn
     */
    @Override
    public void takeTurn(PropertyChangeSupport property, String name) { }
}
