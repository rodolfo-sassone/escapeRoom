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
    
    private Room room1;
    
    private Room room2;
    
    private String desc;
    
    private Lock blocked;
    

    public Door(Room room1, Room room2, String desc, Lock Blocked) {
        this.room1 = room1;
        this.room2 = room2;
        this.desc = desc;
        this.blocked = Blocked;
    }

    public Door(Lock Blocked, String desc) {
        this.blocked = Blocked;
        this.desc = desc;
    }

    public Door() {
    }
    
    
    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public Lock getBlocked() {
        return blocked;
    }

    public void setBlocked(Lock Blocked) {
        this.blocked = Blocked;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }   
    
}
