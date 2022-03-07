package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

/**
 * Holds the general behaviour of a state of the player
 */
public abstract class PlayerState implements IPlayerState{
    protected IPlayerCharacter character;

    public PlayerState() { this.character = null; }

    /**
     * Sets the character that is going to be used
     * @param character
     *     character to be used
     */
    @Override
    public void setCharacter(ICharacter character){ this.character = (IPlayerCharacter) character; }
}
