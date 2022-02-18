/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class BrokenLock extends Lock{
    
    private final int scoreValue;

    public BrokenLock(int scoreValue) {
        this.scoreValue = scoreValue;
    }
    
    public boolean unlock() {
        setLocked(false);
        
        return isUnlocked();
    }
    
}
