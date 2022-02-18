/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.util.Iterator;
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
    
    public boolean unlock(MyObject k){
        boolean unlock = false;
        
        Iterator<KeyLock> it = locks.iterator();
        while(it.hasNext() && (unlock == false))
        {
            KeyLock l = it.next();
            if(l.isLocked())
                unlock = l.unlock(k);
        }
        
        //se abbiamo sbloccato una serratura controlliamo se abbiamo aperto la porta
        if(unlock)
        {
            if(locks.stream().allMatch(l -> l.isUnlocked()))
                setLocked(false);
        }

        return unlock;
    }
    
    public int unlockCounter() {
        return (int) locks.stream().filter(l -> l.isUnlocked()).count(); 
    }
    
}
