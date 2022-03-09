/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.type;

import java.io.Serializable;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class MyObject implements Serializable{
    
    private final int id;
    
    private final String name;
    
    private final String description;
    
    private final boolean openable;
    
    private final boolean pushable;
    
    private final boolean pickupable;
    
    private boolean open;
    
    private boolean push;
    
    private final int keyValue; //final dà problemi?
    
    private Lock blocked;

    public MyObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openable = false;
        this.pushable = false;
        this.pickupable = false;
        this.open = false;
        this.push = false;
        this.keyValue = 0;
        this.blocked = null;
    }

        public MyObject(int id, String name, String description, int keyValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openable = false;
        this.pushable = false;
        this.pickupable = true;
        this.open = false;
        this.push = false;
        this.keyValue = keyValue;
        this.blocked = null;
    }
    
    public MyObject(int id, String name, String description, boolean openable, boolean pushable, boolean pickupable, boolean open, boolean push, int keyValue, Lock Blocked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openable = openable;
        this.pushable = pushable;
        this.pickupable = pickupable;
        this.open = open;
        this.push = push;
        this.keyValue = keyValue;
        this.blocked = Blocked;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    

    public boolean isOpenable() {
        return openable;
    }

    public boolean isPushable() {
        return pushable;
    }

    public boolean isPickupable() {
        return pickupable;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public int getKeyValue() {
        return keyValue;
    }

    public Lock getBlocked() {
        return blocked;
    }

    public void setBlocked(Lock Blocked) {
        this.blocked = Blocked;
    }
   
}
