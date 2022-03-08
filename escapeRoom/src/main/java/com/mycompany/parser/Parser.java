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
import com.mycompany.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Parser {
    

    public Parser() {
    }
    
    public ParserOutput parse(String s, List<MyObject> objects, Room currentRoom, List<Room> rooms) {
        ParserOutput po = null;
        CommandType cmdType = null;
        List<String> tokens = Utils.tokenizer(s);
        
        Iterator<String> it = tokens.iterator();
        while(it.hasNext() && cmdType == null)
        {
            String t = it.next();
            cmdType = checkForCommand(t);
        }
        if(cmdType != null)
        {
            if (cmdType.ordinal() >= CommandType.NORD.ordinal() && cmdType.ordinal() <= CommandType.WHO.ordinal())
                po = new ParserOutput(cmdType);
            else
            {   
                if (cmdType == CommandType.LOOK_AT && !it.hasNext())
                    po = new ParserOutput(cmdType, currentRoom);
                else if (cmdType == CommandType.LOOK_AT && it.hasNext())
                {
                    MyObject obj = null;
                    Room room = null;
                    Door door = null;
                    
                    while(it.hasNext() && (obj == null && room == null && door == null))
                    {
                        String t = it.next();

                        obj = checkForObject(t, objects);
                        if(obj != null)
                            po = new ParserOutput(cmdType, obj);
                        else
                        {
                            room = checkForRoom(t, rooms);
                            if(room != null)
                                po = new ParserOutput(cmdType, room);
                            else
                            {
                                door = checkForDoor(t, currentRoom);
                                if(door != null)
                                    po = new ParserOutput(cmdType, door);
                            }
                        }
                    }
                }
                else if (cmdType.ordinal() >= CommandType.PICK_UP.ordinal() && cmdType.ordinal() <= CommandType.PULL.ordinal() && it.hasNext())
                {
                    MyObject obj = null;
                    
                    while(it.hasNext() && obj == null)
                    {
                        String t = it.next();
                        
                        obj = checkForObject(t, objects);
                    }
                    
                    if(obj != null)
                        po = new ParserOutput(cmdType, obj);
                }
                else if (cmdType == CommandType.OPEN || cmdType == CommandType.UNLOCK)
                {
                    MyObject obj = null;
                    Door door = null;
                    
                    while(it.hasNext() && (obj == null && door == null))
                    {
                        String t = it.next();
                        
                        obj = checkForObject(t, objects);
                        if(obj != null)
                            po = new ParserOutput(cmdType, obj);
                        else
                        {
                            door = checkForDoor(t, currentRoom); //accept both "portanord" and "porta nord" and therefore "nord" alone.
                            if(door != null)
                                po = new ParserOutput(cmdType, door);
                        }
                    }
                }
                else if(cmdType.ordinal() >= CommandType.INSERT.ordinal() && cmdType.ordinal() <= CommandType.THROW.ordinal())
                {
                    MyObject obj1 = null;
                    MyObject obj2 = null;
                    
                    while(it.hasNext() && (obj1 == null || obj2 == null))
                    {
                        String t = it.next();
                        
                        if(obj1 == null)
                            obj1 = checkForObject(t, objects);
                        else
                            obj2 = checkForObject(t, objects);
                    }
                    
                    if(obj1 != null && obj2 != null)
                        po = new ParserOutput(cmdType, obj1, obj2);
                }
                else if (cmdType == CommandType.SWITCH)
                {
                    Prisoner p = null;
                    
                    while(it.hasNext() && p == null)
                    {
                        String t = it.next();
                        
                        p = checkForPrisoner(t);
                    }
                    
                    if(p != null)
                        po = new ParserOutput(cmdType, p);
                }
                else if (cmdType == CommandType.CALL);
                {
                    if(it.hasNext())
                        po = new ParserOutput(cmdType, it.next());
                }
            }
        }
        
        return po;
    }

    
    private CommandType checkForCommand(String t) {
        CommandType cmdType = null;
        
        if(t.equalsIgnoreCase("nord") || t.equalsIgnoreCase("n"))
            cmdType = CommandType.NORD;
        else if(t.equalsIgnoreCase("sud") || t.equalsIgnoreCase("s"))
            cmdType = CommandType.SUD;
        else if(t.equalsIgnoreCase("ovest") || t.equalsIgnoreCase("o"))
            cmdType = CommandType.OVEST;
        else if(t.equalsIgnoreCase("est") || t.equalsIgnoreCase("e"))
            cmdType = CommandType.EST;
        else if(t.equalsIgnoreCase("aiuto") || t.equalsIgnoreCase("help") || t.equalsIgnoreCase("h"))
            cmdType = CommandType.HELP;
        else if(t.equalsIgnoreCase("esci") || t.equalsIgnoreCase("exit"))
            cmdType = CommandType.EXIT;
        else if(t.equalsIgnoreCase("who") || t.equalsIgnoreCase("chi") || t.equalsIgnoreCase("w"))
            cmdType = CommandType.WHO;
        else if(t.equalsIgnoreCase("inventario") || t.equalsIgnoreCase("inventary") || t.equalsIgnoreCase("i"))
            cmdType = CommandType.INVENTARY;
        else if(t.equalsIgnoreCase("esamina") || t.equalsIgnoreCase("guarda"))
            cmdType = CommandType.LOOK_AT;
        else if(t.equalsIgnoreCase("chiama"))
            cmdType = CommandType.CALL;
        else if(t.equalsIgnoreCase("sfila") || t.equalsIgnoreCase("estrai"))
            cmdType = CommandType.EXTRACT;
        else if(t.equalsIgnoreCase("svuota"))
            cmdType = CommandType.EMPTY;
        else if(t.equalsIgnoreCase("illumina"))
            cmdType = CommandType.ILLUMINATE;
        else if(t.equalsIgnoreCase("apri"))
            cmdType = CommandType.OPEN;
        else if(t.equalsIgnoreCase("prendi"))
            cmdType = CommandType.PICK_UP;
        else if(t.equalsIgnoreCase("leggi"))
            cmdType = CommandType.READ;
        else if(t.equalsIgnoreCase("cambia") || t.equalsIgnoreCase("switch") || t.equalsIgnoreCase("cambio") || t.equalsIgnoreCase("c"))
            cmdType = CommandType.SWITCH;
        else if(t.equalsIgnoreCase("tira"))
            cmdType = CommandType.PULL;
        else if(t.equalsIgnoreCase("accendi"))
            cmdType = CommandType.TURN_ON;
        else if(t.equalsIgnoreCase("sblocca"))
            cmdType = CommandType.UNLOCK;
        else if(t.equalsIgnoreCase("svita") || t.equalsIgnoreCase("ruota") || t.equalsIgnoreCase("gira"))
            cmdType = CommandType.UNSCREW;
        else if(t.equalsIgnoreCase("inserisci") || t.equalsIgnoreCase("infila"))
            cmdType = CommandType.INSERT;
        else if(t.equalsIgnoreCase("sovrapponi") || t.equalsIgnoreCase("coincidere") || t.equalsIgnoreCase("combaciare") || t.equalsIgnoreCase("accavalla") || t.equalsIgnoreCase("accavallare"))
            cmdType = CommandType.OVERLAP;
        else if(t.equalsIgnoreCase("lancia"))
            cmdType = CommandType.THROW;
        else if(t.equalsIgnoreCase("lega") || t.equalsIgnoreCase("attacca") || t.equalsIgnoreCase("annoda"))
            cmdType = CommandType.TIE;

        return cmdType;
    }

    private Prisoner checkForPrisoner(String t) {
        Prisoner p = null;
        
        if(t.equalsIgnoreCase("luca") || t.equalsIgnoreCase("luke") || t.equalsIgnoreCase("l"))
            p = Prisoner.LUKE;
        else if (t.equalsIgnoreCase("nicola") || t.equalsIgnoreCase("niky") || t.equalsIgnoreCase("nico") || t.equalsIgnoreCase("n"))
            p = Prisoner.NIKY;
        else if (t.equalsIgnoreCase("riccardo") || t.equalsIgnoreCase("riky") || t.equalsIgnoreCase("r"))
            p = Prisoner.RIKY;
        else if (t.equalsIgnoreCase("rodolfo") || t.equalsIgnoreCase("ronny") || t.equalsIgnoreCase("poffy") || t.equalsIgnoreCase("p"))
            p = Prisoner.RONNY;
        
        return p;
    }

    private MyObject checkForObject(String t, List<MyObject> objects) {
        MyObject obj = null;
        
        Iterator<MyObject> it = objects.iterator();
        while(it.hasNext() && obj == null)
        {
            MyObject o = it.next();
            
            if(o.getName().equalsIgnoreCase(t))
                obj = o;
        }
        
        return obj;
    }

    private Room checkForRoom(String t, List<Room> rooms) {
        Room room = null;
        
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext() && room == null)
        {
            Room r = it.next();
            if(r.getName().equalsIgnoreCase(t))
                room = r;
        }
        
        return room;
    }

    private Door checkForDoor(String t, Room currentRoom) {
        Door door = null;
        Door errorDoor = new Door();
        
        if(t.toLowerCase().contains("nord"))
        {
            if(currentRoom.getNorthDoor() != null)
                door = currentRoom.getNorthDoor();
            else
                door = errorDoor;     
        }
        else if (t.toLowerCase().contains("sud"))
        {
            if(currentRoom.getSouthDoor() != null)
                door = currentRoom.getSouthDoor();
            else
                door = errorDoor; 
        }
        else if (t.toLowerCase().contains("ovest"))
        {
            if(currentRoom.getWestDoor() != null)
                door = currentRoom.getWestDoor();
            else
                door = errorDoor; 
        }
        else if (t.toLowerCase().contains("est"))
        {
            if(currentRoom.getEastDoor() != null)
                door = currentRoom.getEastDoor();
            else
                door = errorDoor; 
        }
        
        return door;
    }
}
