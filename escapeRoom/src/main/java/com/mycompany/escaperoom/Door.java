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
public class Door implements Serializable{
    
    private final Room room1;
    
    private final Room room2;
    
    private Lock Blocked;

    public Door(Room room1, Room room2, Lock Blocked) {
        this.room1 = room1;
        this.room2 = room2;
        this.Blocked = Blocked;
    }

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public Lock getBlocked() {
        return Blocked;
    }

    public void setBlocked(Lock Blocked) {
        this.Blocked = Blocked;
    }
    
    
}
