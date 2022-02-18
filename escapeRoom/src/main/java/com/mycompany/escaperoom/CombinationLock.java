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
public class CombinationLock extends Lock{
    
    private final String combination;
    
    private final int scoreValue;

    public CombinationLock(String combination, int scoreValue) {
        this.combination = combination;
        this.scoreValue = scoreValue;
    }
    
    public int getScoreValue() {
        return scoreValue;
    }
    
    public boolean unlock(String c) {
        if(c.equalsIgnoreCase(combination))
            setLocked(false);
        
        return isUnlocked();
    }

}
