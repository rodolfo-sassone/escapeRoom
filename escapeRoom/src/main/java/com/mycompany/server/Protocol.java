/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Protocol implements Serializable{
    
    private final String username;
    
    private final int score;
    
    private boolean flag;
    
    private final String date;

    public Protocol(String username, int score, String date) {
        this.username = username;
        this.score = score;
        this.date = date;
        flag = false;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    
    
}
