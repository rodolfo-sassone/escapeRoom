/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.mycompany.escaperoom.Game;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pierpaolo
 */
public class Utils {

    public static List<String> tokenizer(String string) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        
        for (String t : split)
            tokens.add(t);

        return tokens;
    }
    
    public static List<String> removeStopword(List<String> tokens, Set<String> stopwords) {
        for (String t : tokens) {
            if (stopwords.contains(t))
                tokens.remove(t);
        }
        return tokens;
    }
    
    public static Game gameLoader(File f) throws IOException, ClassNotFoundException {
        
        Game g = null;
        
        if(f.exists() && f.isFile())
        {
            FileInputStream inFile = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(inFile);
            g = (Game) inStream.readObject();
            inStream.close();
            inFile.close();
        }
        
        return g;
    }

}
