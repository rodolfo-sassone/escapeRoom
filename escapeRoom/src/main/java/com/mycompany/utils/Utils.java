/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.mycompany.escaperoom.Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pierpaolo
 */
public class Utils {

    public static Set<String> loadFileListInSet(File file) throws IOException {
        Set<String> set = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            set.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
        return set;
    }
    
    //dividi in tokenizer e rimozione stopword
    public static List<String> parseString(String string, Set<String> stopwords) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for (String t : split) {
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
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
        }
        
        return g;
    }

}
