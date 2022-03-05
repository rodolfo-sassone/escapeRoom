/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.io.Serializable;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public abstract class Lock implements Serializable{
    
    private boolean locked;
    
    private final int score;

    public Lock() {
        this.locked = true;
        this.score = 0;
    }

    public Lock(int score) {
        this.locked = true;
        this.score = score;
    }

    public boolean isLocked() {
        return locked;
    }
    
    public boolean isUnlocked() {
        return !locked;
    }
    
    public void setLocked(boolean b) {
        locked = b;
    }

    public int getScore() {
        return score;
    }
    
    
}
