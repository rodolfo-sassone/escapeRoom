/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import com.mycompany.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*File gameFile = new File("res/game.dat");
        
        try
        {*/
            Game game = new Game();
            Scanner in = new Scanner(System.in);
            Parser p = new Parser();
            boolean exit = false;
            Thread timer = new Lose();
            timer.setDaemon(true);
            
            System.out.println("=====================================================================================================================");
            System.out.println("Benvenuto in escapeRoom 1.0\n"
                    + "Sei stato imprigionato insieme ai tuoi 3 amici ad Alcatraz, il tuo obiettivo è uscire da li in 60 minuti.\n"
                    + "Ogni prigioniero ha una sua cella, puoi cambiare personaggio con il comando \"cambia\" seguito dal nome del prigioniero.\n"
                    + "I prigionieri sono: Luca, Niky, Riky e Ronny\n"
                    + "Ci saranno molti lucchetti. Quelli a combinazione, se non indicato diversamente, saranno combinazioni a tre cifre.\n"
                    + "Buona Fortuna e occhio all'orologio!");
            System.out.println("=====================================================================================================================");
            
            System.out.println("Premi invio per iniziare e far partire il timer");
            in.nextLine();
            
            timer.start();
            
            System.out.println(game.getCurrentPrisoner().toString());
            System.out.println(game.getCurrentRoom().getName());
            System.out.println(game.getCurrentRoom().getDesc());
            
            ParserOutput po = null;
            List<MyObject> objects = new ArrayList();
            do
            {
                
                objects.addAll(game.getInventary());
                objects.addAll(game.getCurrentRoom().getAvailObj());
                
                String command = "";
                
                while(command.isEmpty() && timer.isAlive())
                    command = in.nextLine();
                
                if(timer.isAlive())
                {
                    po = p.parse(command, objects, game.getCurrentRoom(), game.getRooms());

                    exit = game.nextMove(po, System.out); 
                }
                
                objects.removeAll(objects);
            }while(!exit && timer.isAlive());
        
        /*} catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
}
