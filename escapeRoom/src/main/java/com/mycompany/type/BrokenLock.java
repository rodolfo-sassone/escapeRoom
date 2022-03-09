/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.type;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class BrokenLock extends Lock {

    public BrokenLock(int scoreValue) {
        super(scoreValue);
    }
    
    public boolean unlock() {
        setLocked(false);
        
        return isUnlocked();
    }
    
}
