package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.controller.GameController;

/**
 * It holds the behaviour of a turn phase for the controller
 */
public abstract class AbstractTurnPhase implements ITurnPhase {
    protected GameController controller;

    public AbstractTurnPhase() {
        this.controller = null;
    }

    /**
     * Sets the controller that will be handled through the phase
     */
    @Override
    public void setController(GameController controller){
        this.controller = controller;
    }

    /**
     * Checks if two objects are equal
     * @param obj
     *    obj to check equality with
     */
    @Override
    public boolean equals(final Object obj){
        return obj.hashCode() == this.hashCode();
    }
}
