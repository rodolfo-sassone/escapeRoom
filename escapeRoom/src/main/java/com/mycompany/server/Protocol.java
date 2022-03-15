/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.Serializable;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Protocol implements Serializable{
    
    private final String username;
    
    private final int score;
    
    private boolean flag;
    
    private final boolean write;
    
    private final String date;

    //to write on DB
    public Protocol(String username, int score, String date) {
        this.username = username;
        this.score = score;
        this.date = date;
        flag = false;
        this.write = true;
    }
    
    //to read from DB
    public Protocol() {
        username = null;
        score = 0;
        date = null;
        flag = false;
        write = false;
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

    public boolean getOp() {
        return write;
    }

}
