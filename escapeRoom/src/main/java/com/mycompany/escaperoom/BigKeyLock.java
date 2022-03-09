/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.util.List;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class BigKeyLock extends Lock{
    
    private List<KeyLock> locks;

    public BigKeyLock(List<KeyLock> locks) {
        this.locks = locks;
    }

    public List<KeyLock> getLocks() {
        return locks;
    }

    public void setLocks(List<KeyLock> locks) {
        this.locks = locks;
    }
    
    public int unlock(MyObject k){
        int score = -1;
        boolean unlock = false;
        
        for(KeyLock l : this.getLocks())
        {
            if(l.isLocked())
                unlock = l.unlock(k);
            if(unlock)
                score = l.getScore();
            
            if (locks.stream().allMatch(kl -> kl.isUnlocked()))
                setLocked(false);
        }
        

        return score;
    }
    
    public int unlockCounter() {
        return (int) locks.stream().filter(l -> l.isUnlocked()).count(); 
    }
    
    public int numberOfLocks() {
        return (int) locks.stream().count();
    }
    
}
