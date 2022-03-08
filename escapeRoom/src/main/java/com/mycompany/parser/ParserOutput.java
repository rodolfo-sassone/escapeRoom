/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parser;

import com.mycompany.escaperoom.Door;
import com.mycompany.escaperoom.MyObject;
import com.mycompany.escaperoom.Prisoner;
import com.mycompany.escaperoom.Room;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class ParserOutput {
    
    private CommandType cmdType;
    
    private MyObject obj1;
    
    private MyObject obj2;
    
    private Door door;
    
    private Room room;
    
    private Prisoner p;
    
    private String string;

    public ParserOutput(CommandType cmdType, MyObject obj1) {
        this.cmdType = cmdType;
        this.obj1 = obj1;
        obj2 = null;
        door = null;
        room = null;
        p = null;
        string = null;
    }

    public ParserOutput(CommandType cmdType, MyObject obj1, MyObject obj2) {
        this.cmdType = cmdType;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public ParserOutput(CommandType cmdType) {
        this.cmdType = cmdType;
    }

    public ParserOutput(CommandType cmdType, String string) {
        this.cmdType = cmdType;
        this.string = string;
    }

    public ParserOutput(CommandType cmdType, Door door) {
        this.cmdType = cmdType;
        this.door = door;
    }

    public ParserOutput(CommandType cmdType, Room room) {
        this.cmdType = cmdType;
        this.room = room;
    }

    public ParserOutput(CommandType cmdType, Prisoner p) {
        this.cmdType = cmdType;
        this.p = p;
    }
    
    public CommandType getCmdType() {
        return cmdType;
    }

    public void setCmdType(CommandType cmdType) {
        this.cmdType = cmdType;
    }

    public MyObject getObj1() {
        return obj1;
    }

    public void setObj1(MyObject obj1) {
        this.obj1 = obj1;
    }

    public MyObject getObj2() {
        return obj2;
    }

    public void setObj2(MyObject obj2) {
        this.obj2 = obj2;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Prisoner getP() {
        return p;
    }

    public void setP(Prisoner p) {
        this.p = p;
    }
    
    
}
