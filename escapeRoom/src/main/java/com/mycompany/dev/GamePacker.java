/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dev;

import com.mycompany.escaperoom.BigKeyLock;
import com.mycompany.escaperoom.BrokenLock;
import com.mycompany.escaperoom.CombinationLock;
import com.mycompany.escaperoom.ContainerObject;
import com.mycompany.escaperoom.Door;
import com.mycompany.escaperoom.Game;
import com.mycompany.escaperoom.KeyLock;
import com.mycompany.escaperoom.MyObject;
import com.mycompany.escaperoom.Prisoner;
import com.mycompany.escaperoom.Room;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class GamePacker {
    
    public static boolean pack(Game g, String path) {
        
        boolean success = true;
        try {
            File f = new File(path);
            f.createNewFile();
            
            FileOutputStream outFile = new FileOutputStream(path);
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            
            outStream.writeObject(g);
            
            outStream.close();
            outFile.close();
        } catch (IOException ex) {
            success = false;
            Logger.getLogger(GamePacker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return(success);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Objects
        MyObject key1 = new MyObject(1, "chiave1", "sembra essere della porta di uscita", false, false, true, false, false, 18, (new CombinationLock("378", 0)));
        MyObject key2 = new MyObject(2, "chiave2", "sembra essere della porta di uscita", 24);
        MyObject key3 = new MyObject(3, "chiave3", "sembra essere della porta di uscita", 2);
        MyObject key4 = new MyObject(4, "chiave4", "sembra essere della porta di uscita", 26);
        
        List <MyObject> l = new ArrayList();
        
        MyObject mobile = new MyObject(6, "cellulare", "un vecchio motorola spento(, sul retro, attaccato con lo scotch, un bigliettino con la scritta \"pin max 3 volte\")", false, true, true, false, false, 0, new CombinationLock("5847", 0));
        l.add(mobile);
        MyObject tweezers = new MyObject(7, "pinzette", "normali pinzette per le sopracciglia, sopra c'è scritto cella 3", 0);
        l.add(tweezers);
        MyObject bunchOfKeys = new MyObject(8, "chiavi", "ci saranno sicuramente utili!", 5);
        l.add(bunchOfKeys);
        MyObject cutter = new MyObject(9, "taglierino", "la lama è già fuori, ma a guardarlo sembra non tagli granché", 0);
        l.add(cutter);
        MyObject jacket = new ContainerObject(l, 5, "giubbotto", "è il giubbotto del secondino, sembra pesante, dev'esserci qualcosa nelle tasche…", false, false, true, false, false, 0, null);
        
        MyObject lace = new MyObject(12, "laccio", "un laccio, niente di speciale", 0);
        
        List <MyObject> l12 = new ArrayList();
        l12.add(lace);
        MyObject boot2 = new ContainerObject(l12, 10, "scarpone", "comunissimo scarpone con i lacci marrone, se non fosse per il numero: 84… bah avranno sbagliato a stamparlo…", false, false, true, false, false, 0, null);
        MyObject boot3 = new ContainerObject(l12, 11, "scarpone", "comunissimo scarpone con i lacci marrone, se non fosse per il numero: 92… bah avranno sbagliato a stamparlo…", false, false, true, false, false, 0, null);

        MyObject flashlight = new MyObject(15, "torcia", "piccola torcia accesa legata al tastierino", false, true, false, false, true, 0, null);
        MyObject keypad = new MyObject(16, "tastierino", "è retroilluminato di blu, ma i numeri non sono molto chiari. Vista la posizione e il fatto che la cella1 non ha una serratura fisica, sembra proprio serva a sbloccare la cella1.", false, false, false, false, false, 20, null);
        
        List <MyObject> l1 = new ArrayList();
        MyObject paper = new MyObject(18, "foglietto", "spiegato rivela cosa c'è scritto: JKL TUV GHI PQRS - 3387659893", 0);
        l1.add(paper);
        MyObject hole = new ContainerObject(l1, 17, "buchetto", "buchetto nel muro abbastanza grande per infilarci qualcosa");
        
        List <MyObject> l2 = new ArrayList();
        MyObject cabinet = new MyObject(19, "armadietti", "è un armadio in metallo, grigio; composto da due ante indipendenti. L'antaSinistra e l'antaDestra entrambe chiuse da lucchetti a combinazione");
        //TODO vedi come rappresentare
        MyObject matrix = new MyObject(26, "matrice","+--------+--------+--------+--------+--------+\n"
                                                    +"| police |   10   |   21   |   25   |   55   | ________\n"
                                                    +"+--------+--------+--------+--------+--------+\n"
                                                    +"|   55   | police |   25   |   10   |   21   | ________\n"
                                                    +"+--------+--------+--------+--------+--------+\n"
                                                    +"|   10   |   55   |   10   |   21   |   25   | ________\n"
                                                    +"+--------+--------+--------+--------+--------+\n"
                                                    +"|   25   |   21   |   55   | police |   10   | ________\n"
                                                    +"+--------+--------+--------+--------+--------+\n"
                                                    +"|   21   |   25   |   10   |   55   | police | ________\n"
                                                    +"+--------+--------+--------+--------+--------+\n"
                                                    +" ________ ________ ________ ________ ________   TOTALE", 0);
        l2.add(matrix);
        MyObject cylinder = new ContainerObject(l2, 23, "cilindro", "cilindro in metallo chiuso da un lucchetto a combinazione", true, false, true, false, false, 0, new CombinationLock("101", 0));
        
        List <MyObject> l10 = new ArrayList();
        l10.add(cylinder);
        MyObject phial = new MyObject(24, "boccetta", "boccetta contenente un liquido blu, vista la forma si direbbe di profumo", 0);
        l10.add(phial);
        MyObject shapes = new MyObject(25, "sagome", "Disegnate sulla parte interna dell'armadietto ci sono 3 sagome molto somiglianti alla forma della boccetta");
        l10.add(shapes);
        MyObject sxDoor = new ContainerObject(l10, 20, "antaSinistra", "Sull'anta sinistra, accanto al lucchetto è disegnato, con un pennarello indelebile nero, un quadratino pieno. Il lucchetto non è il solito a combinazione con le rotelle, ma ha 5 righe da 2 pulsanti. Sui pulsanti della prima riga ci sono i numeri 0 e 1, sulla seconda 2 e 3  e così via...", true, false, false, false, false, 0, new CombinationLock("01278", 0));
        
        List <MyObject> l3 = new ArrayList();
        l3.add(key3);
        MyObject bag = new ContainerObject(l3, 22, "busta", "una piccola busta chiusa, sembra che dentro ci sia qualcosa", true, false, true, false, false, 0, null);
        
        List <MyObject> l4 = new ArrayList();
        l4.add(bag);
        MyObject box = new ContainerObject(l4, 21, "cassetta", "cassetta a muro, con una fessura lunga e stretta. Seguendo con gli occhi il tubo che ne fuoriesce porta al sistema di aerazione", false, false, false, false, false, 0, null);
       
        List <MyObject> l5 = new ArrayList();
        l5.add(key2);
        MyObject chest = new ContainerObject(l5, 27, "scrigno", "scrigno in metallo fissato al muro, su di esso è stampato uno stemma con la scritta \"police\", chiuso da un lucchetto a combinazione", true, false, false, false, false, 0, new CombinationLock("555", 0));
        
        List <MyObject> l6 = new ArrayList();
        MyObject magnet = new MyObject(29, "calamita", "Calamita piatta circolare a cui è saldato un gancio perpendicolarmente al centro della calamita; un gancetto con la base magnetica", 0);
        l6.add(magnet);
        MyObject sketch = new MyObject(30, "disegno", "il disegno si divide in tre parti che rappresentano: un laccio, la finestra della porta d'uscita e una calamita", 0);
        l6.add(sketch);
        MyObject dxDoor = new ContainerObject(l6, 28, "antaDestra", "Sull'anta destra, accanto al lucchetto a combinazione sono disegnati, con un pennarello indelebile nero, un quadrato, un rombo e un rettangolo.", true, false, false, false, false, 0, new CombinationLock("303", 0));
        
        MyObject bar = new MyObject(31, "sbarre", "Non sono fissate bene, sembra possano ruotare…");
        MyObject squares = new MyObject(32, "quadrati", "Sono 5 righe da 2 quadrati ciascuna, alcuni bianchi altri neri: la prima riga sono tutti neri, la seconda solo il primo è nero, la terza solo bianchi, la quarta bianco e nero e la quinta nero e bianco");
        List<MyObject> l11 = new ArrayList();
        l11.add(key4);
        MyObject tube = new ContainerObject(l11, 33, "tubo", "un tubo che fuoriesce dal pavimento per un metro o poco più, molto vicino la porta");
        MyObject bench = new MyObject(34, "panca", "Panca in legno attaccata al muro. Niente di speciale. Sotto è buio, ma passando la mano trovi uno scarpone");
        MyObject window = new MyObject(35, "finestra", "Finestra niente di che, oltre la finestra invece, appese al muro di fronte, ci sono 3 manette, la prima è aperta quasi a formare un 3, la seconda è chiusa e la terza è come la prima.\n"
                + "Sotto ad ogni manetta ci sono rispettivametne un quadrato, un rombo e un rettangolo.\n"
                + "In basso, invece, vicino la porta c'è un tubo.");
        //Doors
        Door c1 = new Door(new CombinationLock("2618", 20), "Porta di una cella fatta interamente di sbarre, puoi gurdare nell'atrio o infilare un braccio tra le sbarre e utilizzare oggetti lì vicino.\nNon ha una serratura, ma è comunque bloccata");
        Door c2 = new Door(new BrokenLock(5), "Porta di una cella fatta interamente di sbarre, puoi gurdare nell'atrio. È chiusa a chiave. Agitandola alcune sbarre fanno rumore");
        Door c3 = new Door(new KeyLock(5), "Porta di una cella fatta interamente di sbarre, puoi gurdare nell'atrio. È chiusa a chiave.");
        
        List<KeyLock> kll = new ArrayList();
        KeyLock k1 = new KeyLock(18);
        kll.add(k1);
        KeyLock k2 = new KeyLock(24);
        kll.add(k2);
        KeyLock k3 = new KeyLock(2);
        kll.add(k3);
        KeyLock k4 = new KeyLock(26);
        kll.add(k4);
        
        Door win = new Door(new BigKeyLock(kll), "Porta in ferro, solida, solidissima, ha solo una finestra in alto al centro. È bloccata da 4 chiavi.");
        
        //Rooms
        Room cell1 = new Room(1, "cella1", "una piccola cella, c'è una panca di legno, un lavandino e un gabinetto. C'è una porta ad est; è a sbarre.", null, null, null, c1);
        cell1.getAvailObj().add(keypad);
        cell1.getAvailObj().add(flashlight);
        
        List <MyObject> l7 = new ArrayList();
        
        l7.add(bar);
        l7.add(boot2);
        l7.add(bench);
        Room cell2 = new Room(2, "cella2", "una piccola cella, c'è una panca di legno, un lavandino, un condizionatore e un gabinetto. C'è una porta a nord; è a sbarre.", c2, null, null, null, l7);
        
        List <MyObject> l8 = new ArrayList();
        
        l8.add(bench);
        l8.add(boot3);
        l8.add(hole);
        l8.add(squares);
        Room cell3 = new Room(3, "cella3", "una piccola cella, c'è una panca di legno, un lavandino e un gabinetto.  I muri di questa cella non se la passano benissimo: quadrati disegnati e qualche buchetto… C'è una porta ad ovest; è a sbarre.", null, null, c3, null, l8);
        
        List <MyObject> l9 = new ArrayList();
        l9.add(key1);
        l9.add(jacket);
        l9.add(flashlight);
        l9.add(keypad);
        l9.add(cabinet);
        l9.add(sxDoor);
        l9.add(dxDoor);
        l9.add(box);
        l9.add(chest);
        l9.add(window);
        Room hall = new Room(4, "atrio", "L'atrio dove il secondino controlla a sud cella 2, a ovest cella 1 e ad est cella 3. Poi quando vuole fumare una sigaretta esce dalla porta a nord.\n"
                + "Ora il secondino non c'è, ha lasciato il giubbotto sull'attaccapanni vicino la porta a nord, alla destra del quale c'è una chiave (chiave1) bloccata al muro da un lucchetto a combinazione.\n"
                + "Nell'angolo a nord est ci sono due armadietti, nell'angolo a sud est c'è una cassetta a muro, a sud ovest proprio accanto a cella1 c'è un tastierino numerico\n"
                + "al quale è lagata una piccola torcia e nell'angolo a nord ovest c'è uno scrigno a muro con il simbolo della police.", win, c2, c1, c3, l9);
        
        hall.getAvailObj().add(tube);
        hall.getAvailObj().add(bar);
        
        //Door's room
        c1.setRoom1(cell1);
        c1.setRoom2(hall);
        
        c2.setRoom1(cell2);
        c2.setRoom2(hall);
        
        c3.setRoom1(cell3);
        c3.setRoom2(hall);
        
        //Positions
        Map<Prisoner, Room> positions = new HashMap();
        positions.put(Prisoner.LUKE, cell1);
        positions.put(Prisoner.RIKY, cell1);
        positions.put(Prisoner.NIKY, cell2);
        positions.put(Prisoner.RONNY, cell3);
        
        List<Room> rl = new ArrayList();
        rl.add(hall);
        rl.add(cell1);
        rl.add(cell2);
        rl.add(cell3);
        
        Game game = new Game(rl, Prisoner.RONNY, positions);

        if(GamePacker.pack(game, "res/game.dat"))
            System.out.println("File creato con successo!");
        else
            System.out.println("File non creato o non creato correttamente.");
    }
    
}
