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
public class KeyLock extends Lock{
    
    private final int keyValue;
    
    public KeyLock(int k) {
        keyValue = k;
    }
    
    public boolean unlock(MyObject k) {
        if(k.getKeyValue() == keyValue)
            setLocked(false);
        
        return isUnlocked();
    }
}
