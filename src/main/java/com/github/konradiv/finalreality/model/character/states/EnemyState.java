package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;

/**
 * Holds the behaviour of the state of an enemy
 */
public abstract class EnemyState implements IState {
    protected Enemy enemy;

    public EnemyState() {
        this.enemy = null;
    }

    /**
     * Sets the enemy that will be used
     * @param character
     *     enemy to be used
     */
    @Override
    public void setCharacter(ICharacter character) {
        this.enemy = (Enemy) character;
    }
}
