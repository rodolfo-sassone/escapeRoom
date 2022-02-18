/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Game implements Serializable{
    
    private List<Room> rooms;
    
    private List<MyObject> inventary;
    
    private String currentPlayer;
    
    private Map<String,Room> positions;
    
    private int score;
   

    public Game(List<Room> rooms, List<MyObject> inventary, String currentPlayer, Map<String, Room> positions, int score) {
        this.rooms = rooms;
        this.inventary = inventary;
        this.currentPlayer = currentPlayer;
        this.positions = positions;
        this.score = score;
    }

    public Game(List<Room> rooms, String currentPlayer, Map<String, Room> positions) {
        this.rooms = rooms;
        this.currentPlayer = currentPlayer;
        this.positions = positions;
        this.inventary = new ArrayList();
        this.score = 0;
    }
    
    public Game(Game g)
    {
        this.rooms = g.rooms;
        this.inventary = g.inventary;
        this.currentPlayer = g.currentPlayer;
        this.positions = g.positions;
        this.score = g.score;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<MyObject> getInventary() {
        return inventary;
    }

    public void setInventary(List<MyObject> inventary) {
        this.inventary = inventary;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map<String, Room> getPositions() {
        return positions;
    }

    public void setPositions(Map<String, Room> positions) {
        this.positions = positions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    }
    
}
