/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import com.mycompany.type.MyObject;
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
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;


/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       File gameFile = new File("../res/game.dat");
        
        try
        {
            Game game = Utils.gameLoader(gameFile);
            Scanner in = new Scanner(System.in);
            Parser p = new Parser();
            boolean exit = false;
            Thread timer = game.presentation(in);
            
            ParserOutput po = null;
            List<MyObject> objects = new ArrayList();
            do
            {
                objects.addAll(game.getInventary());
                objects.addAll(game.getCurrentRoom().getAvailObj());
                
                String command = "";
                
                System.out.print(ansi().fg(CYAN));
                
                while(command.isEmpty() && timer.isAlive())
                    command = in.nextLine();
                
                 System.out.print(ansi().fg(YELLOW));
                
                if(timer.isAlive())
                {
                    po = p.parse(command, objects, game.getCurrentRoom(), game.getRooms());

                    exit = game.nextMove(po, System.out); 
                }
                
                objects.removeAll(objects);
            }while(!exit && timer.isAlive());
            
            game.saver(System.out, in);

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AnsiConsole.systemUninstall();
        }
        
    }
    
}
