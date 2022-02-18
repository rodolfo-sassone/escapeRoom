/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Room implements Serializable{
    
    private final int id;   //controlla se da problemi con Integer
    
    private final String name;
    
    private final String desc;
    
    private boolean unexplored;
    
    private final Door northDoor;
    
    private final Door southDoor;
     
    private final Door westDoor;
      
    private final Door eastDoor;
       
    private List<MyObject> objects;
    
    private List<MyObject> availObj;

    public Room(int id, String name, String desc, Door northDoor, Door southDoor, Door westDoor, Door eastDoor) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        unexplored = true;
        this.northDoor = northDoor;
        this.southDoor = southDoor;
        this.westDoor = westDoor;
        this.eastDoor = eastDoor;
        objects = new ArrayList();
        availObj= new ArrayList();
    }

    public Room(int id, String name, String desc, Door northDoor, Door southDoor, Door westDoor, Door eastDoor, List<MyObject> objects) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        unexplored = true;
        this.northDoor = northDoor;
        this.southDoor = southDoor;
        this.westDoor = westDoor;
        this.eastDoor = eastDoor;
        this.objects = objects;
        availObj = new ArrayList();
        availObj.addAll(objects);
    }

    public List<MyObject> getObjects() {
        return objects;
    }

    public void setObjects(List<MyObject> objects) {
        this.objects = objects;
    }

    public int getId() {
        return id;
    }

    public Door getNorthDoor() {
        return northDoor;
    }

    public Door getSouthDoor() {
        return southDoor;
    }

    public Door getWestDoor() {
        return westDoor;
    }

    public Door getEastDoor() {
        return eastDoor;
    }

    public List<MyObject> getAvailObj() {
        return availObj;
    }

    public void setAvailObj(List<MyObject> availObj) {
        this.availObj = availObj;
    }

    public void setUnexplored(boolean unexplored) {
        this.unexplored = unexplored;
    }
    
    public boolean isUnexplored() {
        return unexplored;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        this.setUnexplored(false);
        return desc;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }
    
    public Room getNorthRoom() {
        if(this.getNorthDoor().getRoom1().equals(this))
            return this.getNorthDoor().getRoom2();
        else
            return this.getNorthDoor().getRoom1();
    }
    
    public Room getSouthRoom() {
        if(this.getSouthDoor().getRoom1().equals(this))
            return this.getSouthDoor().getRoom2();
        else
            return this.getSouthDoor().getRoom1();
    }
   
    public Room getWestRoom() {
        if(this.getWestDoor().getRoom1().equals(this))
            return this.getWestDoor().getRoom2();
        else
            return this.getWestDoor().getRoom1();
    }    

    public Room getEastRoom() {
        if(this.getEastDoor().getRoom1().equals(this))
            return this.getEastDoor().getRoom2();
        else
            return this.getEastDoor().getRoom1();
    }
}
