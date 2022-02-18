/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dev;

import com.mycompany.escaperoom.Game;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class GamePacker {
    
    public static void pack(Game g, String path) throws IOException {
        
        File f = new File(path);
        f.createNewFile();
        
        FileOutputStream outFile = new FileOutputStream(path);
        ObjectOutputStream outStream = new ObjectOutputStream(outFile);
        
        outStream.writeObject(g);
        
        outStream.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
}
