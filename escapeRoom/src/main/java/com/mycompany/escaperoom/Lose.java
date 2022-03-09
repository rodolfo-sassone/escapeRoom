/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;



import java.util.logging.Level;
import java.util.logging.Logger;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.*;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Lose extends Thread{
      
    @Override
    public void run() {
        try {
            Lose.sleep(3600000);
            
            ASCIIArtGenerator artGen = new ASCIIArtGenerator();
            
            System.out.print(ansi().fg(GREEN));
            artGen.printTextArt( "          ┌┬┬┬┐", ASCIIArtGenerator.ART_SIZE_MEDIUM,  ASCIIArtGenerator.ASCIIArtFont.ART_FONT_DIALOG, "#");
            artGen.printTextArt( "          ├┼┼┼┤", ASCIIArtGenerator.ART_SIZE_MEDIUM,  ASCIIArtGenerator.ASCIIArtFont.ART_FONT_DIALOG, "#");
            artGen.printTextArt( "          └┴┴┴┘", ASCIIArtGenerator.ART_SIZE_MEDIUM,  ASCIIArtGenerator.ASCIIArtFont.ART_FONT_DIALOG, "#");
            System.out.print(ansi().fg(YELLOW));
            System.out.println("Tempo scaduto. Non sei riuscito ad uscire, passerai il resto della tua vita qui dentro.\n"
                    + "Se ti andrà bene morirai per primo, altrimenti assisterai alla triste morte di tutti i tuoi amici per poi morire solo.\n"
                    + "Addios.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Lose.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Lose.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
