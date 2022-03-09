/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parser;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public enum CommandType {
    NORD, SUD, OVEST, EST, HELP, EXIT, INVENTARY, WHERE, WHO,                          //Only command
    PICK_UP, READ, ILLUMINATE, EXTRACT, EMPTY, TURN_ON, UNSCREW, PULL,          //One object
    OPEN, UNLOCK,                                                               //One object or One door
    INSERT, OVERLAP, TIE, THROW,                                                //Two objects
    SWITCH,                                                                     //One prisoner
    CALL,                                                                       //String
    LOOK_AT                                                                     //Only command or One object or One door or One room
}
