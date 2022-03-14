/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import com.mycompany.parser.Parser;
import com.mycompany.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File gameFile = new File("./res/game.dat");

        try {
            Game game = Utils.gameLoader(gameFile);

            Parser p = new Parser();

            game.play(p);

            game.saver(System.out);

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AnsiConsole.systemUninstall();
        }
    }
}
