/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import com.mycompany.type.*;
import com.mycompany.utils.ASCIIArtGenerator;
import com.mycompany.parser.ParserOutput;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.*;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Game implements Serializable{
    
    private List<Room> rooms;
    
    private List<MyObject> inventary;
    
    private Prisoner currentPrisoner;
    
    private Map<Prisoner,Room> positions;
    
    private Room currentRoom;
    
    private int score;
   

    public Game(List<Room> rooms, List<MyObject> inventary, Prisoner currentPrisoner, Map<Prisoner, Room> positions, int score) {
        this.rooms = rooms;
        this.inventary = inventary;
        this.currentPrisoner = currentPrisoner;
        this.positions = positions;
        this.score = score;
        updateCurrentRoom(currentPrisoner);
    }

    public Game(List<Room> rooms, Prisoner currentPrisoner, Map<Prisoner, Room> positions) {
        this.rooms = rooms;
        this.currentPrisoner = currentPrisoner;
        this.positions = positions;
        this.inventary = new ArrayList();
        this.score = 0;
        updateCurrentRoom(currentPrisoner);
    }
    
    public Game(Game g)
    {
        this.rooms = g.rooms;
        this.inventary = g.inventary;
        this.currentPrisoner = g.currentPrisoner;
        this.positions = g.positions;
        this.score = g.score;
        updateCurrentRoom(currentPrisoner);
    }

    public Game() {
        inventary = new ArrayList();
        // Objects
        MyObject key1 = new MyObject(1, "chiave1", "sembra essere della porta di uscita", false, false, true, false, false, 18, (new CombinationLock("378", 0)));
        MyObject key2 = new MyObject(2, "chiave2", "sembra essere della porta di uscita", 24);
        MyObject key3 = new MyObject(3, "chiave3", "sembra essere della porta di uscita", 2);
        MyObject key4 = new MyObject(4, "chiave4", "sembra essere della porta di uscita", 26);
        
        List <MyObject> l = new ArrayList();
        
        MyObject mobile = new MyObject(6, "cellulare", "un vecchio motorola spento", false, true, true, false, false, 0, new CombinationLock("5847", 0));
        l.add(mobile);
        MyObject tweezers = new MyObject(7, "pinzette", "normali pinzette per le sopracciglia, sopra c'è scritto cella 3", 0);
        l.add(tweezers);
        MyObject bunchOfKeys = new MyObject(8, "chiavi", "ci saranno sicuramente utili!", 5);
        l.add(bunchOfKeys);
        MyObject cutter = new MyObject(9, "taglierino", "la lama è già fuori, ma a guardarlo sembra non tagli granché", 0);
        l.add(cutter);
        MyObject jacket = new ContainerObject(l, 5, "giubbotto", "è il giubbotto del secondino, sembra pesante, dev'esserci qualcosa nelle tasche...", false, false, true, false, false, 0, null);
        
        MyObject lace = new MyObject(12, "laccio", "un laccio, niente di speciale", 0);
        
        List <MyObject> l12 = new ArrayList();
        l12.add(lace);
        MyObject boot2 = new ContainerObject(l12, 10, "scarpone", "comunissimo scarpone con i lacci marrone, se non fosse per il numero: 84... bah avranno sbagliato a stamparlo…", false, false, true, false, false, 0, null);
        MyObject boot3 = new ContainerObject(l12, 11, "scarpone", "comunissimo scarpone con i lacci marrone, se non fosse per il numero: 92... bah avranno sbagliato a stamparlo…", false, false, true, false, false, 0, null);

        MyObject flashlight = new MyObject(15, "torcia", "piccola torcia accesa legata al tastierino", false, true, false, false, true, 0, null);
        MyObject keypad = new MyObject(16, "tastierino", "è retroilluminato di blu, ma i numeri non sono molto chiari. Vista la posizione e il fatto che la cella1 non ha una serratura fisica, sembra proprio serva a sbloccare la cella1.", false, false, false, false, false, 20, null);
        
        List <MyObject> l1 = new ArrayList();
        MyObject paper = new MyObject(18, "foglietto", "spiegato rivela cosa c'è scritto: JKL TUV GHI PQRS - 3387659893", 0);
        l1.add(paper);
        MyObject hole = new ContainerObject(l1, 17, "buchetto", "buchetto nel muro abbastanza grande per infilarci qualcosa");
        
        List <MyObject> l2 = new ArrayList();
        MyObject cabinet = new MyObject(19, "armadietti", "è un armadio in metallo, grigio; composto da due ante indipendenti. L'antaSinistra e l'antaDestra entrambe chiuse da lucchetti a combinazione");
        //TODO vedi come rappresentare
        MyObject matrix = new MyObject(26, "matrice", "+--------+--------+--------+--------+--------+\n"
                                                    + "| police |   10   |   21   |   25   |   55   | ________\n"
                                                    + "+--------+--------+--------+--------+--------+\n"
                                                    + "|   55   | police |   25   |   10   |   21   | ________\n"
                                                    + "+--------+--------+--------+--------+--------+\n"
                                                    + "|   10   |   55   |   10   |   21   |   25   | ________\n"
                                                    + "+--------+--------+--------+--------+--------+\n"
                                                    + "|   25   |   21   |   55   | police |   10   | ________\n"
                                                    + "+--------+--------+--------+--------+--------+\n"
                                                    + "|   21   |   25   |   10   |   55   | police | ________\n"
                                                    + "+--------+--------+--------+--------+--------+\n"
                                                    + " ________ ________ ________ ________ ________   TOTALE", 0);
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
        MyObject chest = new ContainerObject(l5, 27, "scrigno", "scrigno in metallo fissato al muro, su di esso è stampato uno stemma con la scritta \"police\", chiuso da un lucchetto a combinazione", true, false, false, false, false, 0, new CombinationLock("515", 0));
        
        List <MyObject> l6 = new ArrayList();
        MyObject magnet = new MyObject(29, "calamita", "Calamita piatta circolare a cui è saldato un gancio perpendicolarmente al centro della calamita; un gancetto con la base magnetica", 0);
        l6.add(magnet);
        MyObject sketch = new MyObject(30, "disegno", "il disegno si divide in tre parti che rappresentano: un laccio, la finestra della porta d'uscita e una calamita", 0);
        l6.add(sketch);
        MyObject dxDoor = new ContainerObject(l6, 28, "antaDestra", "Sull'anta destra, accanto al lucchetto a combinazione sono disegnati, con un pennarello indelebile nero, un quadrato, un rombo e un rettangolo.", true, false, false, false, false, 0, new CombinationLock("303", 0));
        
        MyObject bar = new MyObject(31, "sbarre", "Non sono fissate bene, sembra possano ruotare...");
        MyObject squares = new MyObject(32, "quadrati", "Sono 5 righe da 2 quadrati ciascuna, alcuni bianchi altri neri: la prima riga sono tutti neri, la seconda solo il primo è nero, la terza solo bianchi, la quarta bianco e nero e la quinta nero e bianco");
        List<MyObject> l11 = new ArrayList();
        l11.add(key4);
        MyObject tube = new ContainerObject(l11, 33, "tubo", "un tubo che fuoriesce dal pavimento per un metro o poco più, molto vicino la porta");
        MyObject bench = new MyObject(34, "panca", "Panca in legno attaccata al muro. Niente di speciale. Sotto è buio, ma passando la mano trovi uno scarpone");
        MyObject window = new MyObject(35, "finestra", "Finestra niente di che, oltre la finestra invece, appese al muro di fronte, ci sono 3 manette, la prima è aperta quasi a formare un 3, la seconda è chiusa e la terza è come la prima.\n"
                + "Sotto ad ogni manetta ci sono rispettivametne un quadrato, un rombo e un rettangolo.\n"
                + "In basso, invece, vicino la porta c'è un tubo.");
        MyObject wc = new MyObject(36, "gabinetto", "Un gabinetto come tanti, un po' ti somiglia anche");
        MyObject sink = new MyObject(37, "lavandino", "Un normalissimo lavandino. L'acqua non c'è quindi avete la stessa utilità praticamente.");
        MyObject airCondit = new MyObject(38, "condizionatore", "Un vecchio condizionatore, fa aria fredda ma fa già freddo, le manopole non funzionano.\n"
                + "Potevi finire in una semplice cella e invece sei finito in una cella frigorifera; che culo!");
        MyObject normalBench = new MyObject(39, "panca", "Panca in legno attaccata al muro.");
        MyObject coatRak = new MyObject(40, "attaccapanni", "Un attaccapanni. Cosa ti aspetti da un attaccapanni?");
        
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
        List <MyObject> lcell1 = new ArrayList();
        lcell1.add(wc);
        lcell1.add(sink);
        lcell1.add(normalBench);
        Room cell1 = new Room(1, "cella1", "una piccola cella, c'è una panca di legno, un lavandino e un gabinetto. C'è una porta ad est; è a sbarre.", null, null, null, c1, lcell1);
        cell1.getAvailObj().add(keypad);
        cell1.getAvailObj().add(flashlight);
        
        List <MyObject> lcell2 = new ArrayList();
        
        lcell2.add(bar);
        lcell2.add(boot2);
        lcell2.add(bench);
        lcell2.add(wc);
        lcell2.add(sink);
        lcell2.add(airCondit);
        Room cell2 = new Room(2, "cella2", "una piccola cella, c'è una panca di legno, un lavandino, un condizionatore e un gabinetto. C'è una porta a nord; è a sbarre.", c2, null, null, null, lcell2);
        
        List <MyObject> lcell3 = new ArrayList();
        
        lcell3.add(bench);
        lcell3.add(boot3);
        lcell3.add(hole);
        lcell3.add(squares);
        lcell3.add(wc);
        lcell3.add(sink);
        Room cell3 = new Room(3, "cella3", "una piccola cella, c'è una panca di legno, un lavandino e un gabinetto.  I muri di questa cella non se la passano benissimo: quadrati disegnati e qualche buchetto... C'è una porta ad ovest; è a sbarre.", null, null, c3, null, lcell3);
        
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
        l9.add(coatRak);
        Room hall = new Room(4, "atrio", "L'atrio dove il secondino controlla a sud cella 2, a ovest cella 1 e ad est cella 3. Poi quando vuole fumare una sigaretta esce dalla porta a nord.\n"
                + "Ora il secondino non c'è, ha lasciato il giubbotto sull'attaccapanni vicino la porta a nord, alla destra del quale c'è una chiave (chiave1) bloccata al muro da un lucchetto a combinazione.\n"
                + "Nell'angolo a nord est ci sono due armadietti, nell'angolo a sud est c'è una cassetta a muro, a sud ovest proprio accanto a cella1 c'è un tastierino numerico\n"
                + "al quale è lagata una piccola torcia e nell'angolo a nord ovest c'è uno scrigno a muro con la scitta \"police\".", win, c2, c1, c3, l9);
        
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
        positions = new HashMap();
        positions.put(Prisoner.LUKE, cell1);
        positions.put(Prisoner.RIKY, cell1);
        positions.put(Prisoner.NIKY, cell2);
        positions.put(Prisoner.RONNY, cell3);
        
        rooms = new ArrayList();
        rooms.add(hall);
        rooms.add(cell1);
        rooms.add(cell2);
        rooms.add(cell3);
        
        currentPrisoner = Prisoner.RONNY;
        score = 0;
        updateCurrentRoom(currentPrisoner);
    }
    
    

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<MyObject> getInventary() {
        return inventary;
    }

    public void setInventary(List<MyObject> inventary) {
        this.inventary = inventary;
    }

    public Prisoner getCurrentPrisoner() {
        return currentPrisoner;
    }

    public void setCurrentPrisoner(Prisoner currentPlayer) {
        this.currentPrisoner = currentPlayer;
    }

    public Map<Prisoner, Room> getPositions() {
        return positions;
    }

    public void setPositions(Map<Prisoner, Room> positions) {
        this.positions = positions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    public void updateCurrentRoom(Prisoner p) {
        currentRoom = positions.get(p);
    }
    
    public boolean nextMove(ParserOutput po, PrintStream out) {
        boolean move = false;
        boolean noRoom = false;
        boolean lockedDoor = false;
        boolean ext = false;
        
        
        if(po != null)
        {
            switch (po.getCmdType()) {
                case HELP:
                    help(out);
                    break;

                case NORD:
                    if (currentRoom.getNorthDoor() != null) 
                    {
                        if(currentRoom.getNorthDoor().getBlocked().isUnlocked())
                        {
                            positions.replace(currentPrisoner, currentRoom.getNorthRoom());
                            updateCurrentRoom(currentPrisoner);
                            move = true;
                        }
                        else
                            lockedDoor = true;
                    } 
                    else
                        noRoom = true;
                    break;

                case SUD:
                    if (currentRoom.getSouthDoor() != null) 
                    {
                        if(currentRoom.getSouthDoor().getBlocked().isUnlocked())
                        {
                            positions.replace(currentPrisoner, currentRoom.getSouthRoom());
                            updateCurrentRoom(currentPrisoner);
                            move = true;
                        }
                        else
                            lockedDoor = true;
                    } 
                    else
                        noRoom = true;
                    break;

                case OVEST:
                    if (currentRoom.getWestDoor() != null) 
                    {
                        if(currentRoom.getWestDoor().getBlocked().isUnlocked())
                        {
                            positions.replace(currentPrisoner, currentRoom.getWestRoom());
                            updateCurrentRoom(currentPrisoner);
                            move = true;
                        }
                        else
                            lockedDoor = true;
                    } 
                    else
                        noRoom = true;
                    break;

                case EST:
                    if (currentRoom.getEastDoor() != null) 
                    {
                        if(currentRoom.getEastDoor().getBlocked().isUnlocked())
                        {
                            positions.replace(currentPrisoner, currentRoom.getEastRoom());
                            updateCurrentRoom(currentPrisoner);
                            move = true;
                        }
                        else
                            lockedDoor = true;
                    } 
                    else
                        noRoom = true;
                    break;

                case WHO:
                    out.println("Sei " + currentPrisoner.toString());
                    break;

                case SWITCH:
                    if (po.getP() == currentPrisoner) 
                        out.println("Sei già lui");
                    else 
                    {
                        setCurrentPrisoner(po.getP());
                        updateCurrentRoom(currentPrisoner);
                        move = true;
                    }
                    break;

                case EXIT:
                    ext = exit(out);
                    break;
                    
                case WHERE:
                    out.println("Sei in " + getCurrentRoom().getName());
                    break;

                case PICK_UP:
                    if(po.getObj1().isPickupable())
                    {
                        if(po.getObj1().getId() == 1 && po.getObj1().getBlocked().isLocked())
                            out.println("E' bloccata al muro da un luccheto a combinazione. Non puoi prenderla.");
                        else
                        {
                            inventary.add(po.getObj1());
                            currentRoom.getAvailObj().remove(po.getObj1());
                            out.println("Fatto");
                        }
                    }
                    else
                        out.println("Non c'è niente da prendere qui");
                    break;

                case INVENTARY:
                    if (inventary.isEmpty())
                        out.println("L'inventario è vuoto");
                    else
                    {
                        out.println("Nell'inventario ci sono: ");
                        for(MyObject o: inventary)
                            out.println(o.getName());
                    }
                    break;

                case OPEN:
                    if(po.getObj1() != null)
                    {
                        if(po.getObj1().isOpenable())
                        {
                            if(po.getObj1().isOpen())
                                out.println("Già aperto");
                            if(po.getObj1().getBlocked() == null || po.getObj1().getBlocked().isUnlocked())
                            {
                                po.getObj1().setOpen(true);

                                if(po.getObj1() instanceof ContainerObject)
                                {
                                    ContainerObject c = (ContainerObject) po.getObj1();

                                    out.println("Contiene: ");
                                    if(c.getObjects().isEmpty())
                                        out.println("niente, è vuoto");
                                    else
                                    {
                                        for(MyObject o : c.getObjects())
                                        {
                                            out.println(o.getName());
                                            currentRoom.getAvailObj().add(o);
                                        }
                                    }
                                }
                            }  
                            else
                                out.println("Oggetto bloccato");
                        }
                        else
                            out.println("Niente da aprire");
                    }
                    else
                        ext = unlocker(po, out);
                    break;

                case UNLOCK:
                    ext = unlocker(po, out);
                    break;

                case LOOK_AT:
                    if (po.getRoom() != null) 
                    {
                        if (po.getRoom().equals(currentRoom)) 
                        {
                            out.println(currentRoom.getDesc());
                        } 
                        else 
                        {
                            if (currentRoom.getNextRooms().contains(po.getRoom()))
                                out.println(po.getRoom().getDesc());
                            else
                                out.println(currentPrisoner.toString() + " ancora non ha il Byakugan");
                        }
                    } 
                    else if (po.getDoor() != null) 
                    {
                        if (po.getDoor().getRoom1() == null) //errorDoor
                            out.println("Non c'è nulla di là");
                        else
                            out.println(po.getDoor().getDesc());
                    } 
                    else if (po.getObj1() != null) 
                    {
                        out.println(po.getObj1().getDescription());
                        if (po.getObj1() instanceof ContainerObject && po.getObj1().isOpen()) 
                        {
                            ContainerObject c = (ContainerObject) po.getObj1();
                            
                            if(!c.getObjects().isEmpty())
                            {
                                out.println("Contiene: ");
                                for (MyObject o : c.getObjects())
                                    out.println(o.getName());
                            }
                        } 
                    }
                    break;

                case EXTRACT:
                    if(po.getObj1().getId() == 10 || po.getObj1().getId() == 11)
                    {
                        ContainerObject c = (ContainerObject) po.getObj1();
                        currentRoom.getAvailObj().addAll(c.getObjects());
                        c.getObjects().removeAll(c.getObjects());
                        out.println("Laccio sfilato");
                    }
                    else
                        out.println("Niente da estrarre qui");
                    break;
                    
                case EMPTY:
                    if(po.getObj1() instanceof ContainerObject)
                    {
                        if(po.getObj1().getBlocked() == null || po.getObj1().getBlocked().isUnlocked())
                        {
                            if(!po.getObj1().isOpenable() || po.getObj1().isOpenable() && po.getObj1().isOpen())
                            {
                                ContainerObject c = (ContainerObject) po.getObj1();

                                if(c.getObjects().isEmpty())
                                    out.println("E' vuoto");
                                else
                                {
                                    List<MyObject> toRemove = new ArrayList();
                                    out.println("Hai estratto: ");

                                    for(MyObject o : c.getObjects())
                                    {
                                        out.println(o.getName());

                                        currentRoom.getObjects().add(o);
                                        currentRoom.getAvailObj().add(o);
                                        toRemove.add(o);
                                    }
                                    c.getObjects().removeAll(toRemove);
                                }
                            }
                            else
                                out.println("Se prima non lo apri non si svuoterà, te lo dico da amico");
                        }
                        else
                            out.println("E' chiuso e bloccato, non posso svuotarlo");
                    }
                    else
                        out.println("Niente da svuotare qui");
                    break;

                case UNSCREW:
                    if(po.getObj1().getId() == 31)      //bar
                    {
                        BrokenLock bl = null;
                        if(currentRoom.getId() == 2)    //cell2
                            bl = (BrokenLock) currentRoom.getNorthDoor().getBlocked();
                        else                            //hall
                            bl = (BrokenLock) currentRoom.getSouthDoor().getBlocked();

                        bl.unlock();
                        out.println("Le sbarre si svitano e vengono via, puoi passare");
                        score += bl.getScore();
                    }
                    else
                        out.println("Non gira");
                    break;

                case CALL:
                    List<MyObject> objects = new ArrayList();
                    objects.addAll(inventary);
                    objects.addAll(currentRoom.getAvailObj());
                    MyObject mobile = null;

                    for(MyObject o : objects)
                    {
                        if(o.getId() == 6)
                            mobile = o;
                    }
                    if(mobile != null)
                    {
                        if(mobile.isPush())
                        {
                            if(mobile.getBlocked().isUnlocked())
                            {
                                if(po.getString().equalsIgnoreCase("3387659893"))
                                    out.println("Squilla \n risponde una voce registrata \"La combinazione è: 378\"");
                                else
                                    out.println("Non va");
                            }
                            else
                                out.println("Se non sblocchi il cellulare la vedo dura");
                        }
                        else
                            out.println("Con il cellulare spento al massimo provo a voce, ma visto dove siamo non so quanto ti convenga...");
                    }
                    else if(po.getString().equalsIgnoreCase("voce") || po.getString().equalsIgnoreCase("a"))
                        out.println("A PAAAOOOLOOO\nniente, Paolo non risponde... Che po'\nChi cazz è Paolo? Boh");
                    else
                        out.println("Non ho mica un telefono");
                    break;

                case ILLUMINATE:
                    if(po.getObj1().getId() == 16)
                    {
                        MyObject f = null;
                        for(MyObject o : currentRoom.getAvailObj())
                        {
                            if(o.getId() == 15)
                                f = o;
                        }

                        if(f != null)
                        {
                            if(f.isPush())
                            {
                                out.println("Sotto la luce della torcia il tastierino rivela la disposizione dei numeri: con l'1 in alto a sinistra, il 9 in basso a destra e lo zero ancora più giù al centro. I tasti con i numeri da 1 a 9 hanno nella parte in alto a destra in piccolo il proprio complemento a 10");
                            }
                            else
                                out.println("Con la torcia spenta?");
                        }     
                    }
                    break;

                case READ:
                    if(po.getObj1().getId() == 18 || po.getObj1().getId() == 26 || po.getObj1().getId() == 30)
                        out.println(po.getDoor().getDesc());
                    else
                        out.println("Niente da leggere qui");
                    break;

                case TURN_ON:
                    if(po.getObj1().isPushable())
                    {
                        if(!po.getObj1().isPush())
                        {
                            po.getObj1().setPush(true);
                            out.println("Acceso");
                            if(po.getObj1().getId() == 6)
                                out.println("E' bloccato dal pin");
                        }
                        else
                            out.println("Già acceso");
                    }
                    break;

                case TIE:
                    if(po.getObj1().getId() == 12 && po.getObj2().getId() == 29)
                    {
                        po.getObj2().setPush(true);
                        out.println("Fatto");
                    }
                    else
                        out.println("Ma cosa leghi? Ma che fai? Ma stai fermo!");
                    break;

                case THROW:
                    if(po.getObj1().getId() == 29 && po.getObj2().getId() == 33)
                    {
                        if(po.getObj1().isPush())
                            {
                            out.println("Dopo un paio di tentavi la calamita entra nel tubo e si sente un piccolo rumore metallico");
                            for(MyObject o : inventary)
                            {
                                if(o.getId() == 12)
                                    o.setPush(true);
                            }
                        }
                        else
                            out.println("Si, e poi? Solo idee geniali tu...");
                    }
                    else
                        out.println("Niente da lanciare qui");
                    break;

                case PULL:
                    if(po.getObj1().getId() == 12 && po.getObj1().isPush())
                    {
                        List<MyObject> toAdd = new ArrayList();
                        for(MyObject o : currentRoom.getAvailObj())
                        {
                            if(o.getId() == 33)
                            {
                                ContainerObject tube = (ContainerObject) o;
                                toAdd.addAll(tube.getObjects());
                                tube.getObjects().removeAll(tube.getObjects());
                            }
                        }
                        currentRoom.getAvailObj().addAll(toAdd);
                        out.println("Tiri su la calamita ed attaccata ad essa c'è chiave4");
                    }
                    else
                        out.println("Non c'è niente da tirare qui");
                    break;

                case OVERLAP:
                    if(po.getObj1().getId() == 24 & po.getObj2().getId() == 25)
                        out.println("La rifrazione della luce attraverso il liquido contenuto nella boccetta fa apparire sulla prima sagoma un trattino verticale, sulla seconda un cerchio e la terza come la prima.");
                    else
                        out.println("Ma queste idee ti vengono così o ti impegni?");
                    break;

                case INSERT:
                    if(po.getObj1().getId() == 9 && po.getObj2().getId() == 21)
                    {
                        out.println("Il sistema di aerazione si attiva, e da una presa d'aria viene fuori una busta");
                        ContainerObject c = (ContainerObject) po.getObj2();
                        currentRoom.getAvailObj().addAll(c.getObjects());
                    }
                    else if(po.getObj1().getId() == 7 && po.getObj2().getId() == 17)
                    {
                        ContainerObject c = (ContainerObject) po.getObj2();
                        currentRoom.getAvailObj().addAll(c.getObjects());
                        out.println("Sembra ci sia qualcosa... Con le pinzette riesci a tirarla fuori. E' un foglietto");
                    }
                    else
                        out.println("Niente da inserire da nessuna parte...");
                    break;

                default:
                    out.println("Non ho capito, cosa devo fare??");
                    break;

            }

            if(move)
            {
                out.println("Sei in " + currentRoom.getName());
                if(currentRoom.isUnexplored())
                    out.println(currentRoom.getDesc());
            }
            if(noRoom)
                out.println("Ehm c'è il muro...");
            if(lockedDoor)
                out.println("La porta è bloccata");
        }
        else
            out.println("Non ho capito, cosa devo fare??");
        
        return ext;
    }

    
    public boolean unlocker(ParserOutput po, PrintStream out) {
        Lock l = null;
        boolean win = false;
        
        if(po.getObj1() != null)
            l = po.getObj1().getBlocked();
        else 
            l = po.getDoor().getBlocked();
        
        if(l != null)
        {
            if(l.isLocked())
            {
                if(l instanceof KeyLock)
                {
                    boolean f = false;
                    KeyLock kl = (KeyLock) l;
                    
                    Iterator<MyObject> it = inventary.iterator();
                    while(it.hasNext() && f == false)
                    {
                        MyObject o = it.next();
                        f = kl.unlock(o);
                        if(f)
                            inventary.remove(o);
                    }
                    if(f)
                    {
                        out.println("Sbloccato");
                        score = score + kl.getScore();
                    }
                    else
                        out.println("Hai bisogno della chiave");
                }
                else if(l instanceof BrokenLock)
                    out.println("Non si sblocca");
                else if(l instanceof CombinationLock)
                {
                    Scanner in = new Scanner(System.in);
                    CombinationLock cl = (CombinationLock) l;
                    boolean f = false;
                    
                    out.println("Digita la combinazione:");
                    out.print(ansi().fg(CYAN));
                    String comb = in.nextLine();
                    
                    out.print(ansi().fg(YELLOW));
                    if(!comb.isEmpty())
                    {
                        f = cl.unlock(comb);
                        if(f)
                        {
                            out.println("Sbloccato");
                            score += cl.getScore();
                        }
                        else
                            out.println("Combinazione sbagliata, non si sblocca");
                    }
                }
                else if(l instanceof BigKeyLock)
                {
                    int s = -1;
                    BigKeyLock kl = (BigKeyLock) l;
                    int alreadyUnl = kl.unlockCounter();
                    List<MyObject> toRemove = new ArrayList();
                    
                    for(MyObject o : inventary)
                    {
                        s = kl.unlock(o);
                        
                        if(s != -1)
                        {
                            toRemove.add(o);
                            score += s;
                        }
                    }
                    
                    inventary.removeAll(toRemove);
                    
                    int diffUnl = kl.unlockCounter() - alreadyUnl;
                    
                    if(diffUnl == 0)
                        out.println("Non hai nessuna chiave per questo");
                    else if(l.isUnlocked())
                    {
                        out.println("Tutte le serrature sbloccate");
                        win = true;
                        if(win)
                            celebration(out);
                    }
                    else
                        out.println("Hai appena sbloccato " + diffUnl + " serratura, ne rimangono ancora " + (kl.numberOfLocks()-kl.unlockCounter()) );
                }
            }
            else
                out.println("Già sbloccato");
        }
        else
            out.println("Niente da sbloccare");
        
        return win;
    }
    
    private void help(PrintStream out) {
        out.println("Ecco alcuni comandi che conosco e forse capisco:\n"
                + "Aiuto\n"
                + "Accendi\n"
                + "Apri\n"
                + "Cambia\n"
                + "Chi\n"
                + "Chiama\n"
                + "Dove\n"
                + "Esamina\n"
                + "Esci\n"
                + "Illumina\n"
                + "Infila\n"
                + "Inventario\n"
                + "Lancia\n"
                + "Lega\n"
                + "Leggi\n"
                + "Prendi\n"
                + "Sblocca\n"
                + "Sfila\n"
                + "Sovrapponi\n"
                + "Svita\n"
                + "Svuota\n"
                + "Tira\n"
                + "*Per muoverti attraverso la mappa:"
                + "Nord o n\n"
                + "Sud o s\n"
                + "Ovest o o\n"
                + "Est o e\n"
                + "*Per sbloccare una porta:\n"
                + "Apri\n"
                + "Sblocca\n"
                + "*Seguiti dalla direzione dove si trova la porta da sbloccare\n"
                + "**Esempi equivalenti\n"
                + "**apri porta nord\n"
                + "**sblocca nord\n");
    }
    
    private boolean exit(PrintStream out) {
        String answer="";
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        
        do{
            out.print(ansi().fg(YELLOW));
            out.println("Sei sicuro? (S/N)");
            
            out.print(ansi().fg(CYAN));
            answer = in.nextLine();
            
        }while(!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));
        
        out.print(ansi().fg(YELLOW));
        if(answer.equalsIgnoreCase("s"))
        {
            out.println("Eri al " + score + "%\n"
                + "Peccato, sarà per la prossima.");
            exit = true;
        }
        else
        {
            out.println(currentRoom.getName());
            out.println(currentRoom.getDesc());
        }
        return exit;
    }
    
    private void celebration(PrintStream out) {
        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
        try
        {
            if (score == 100)
            {
                artGen.printTextArt("     WIN", ASCIIArtGenerator.ART_SIZE_LARGE,  ASCIIArtGenerator.ASCIIArtFont.ART_FONT_MONO, "#");
                out.println("Congratulazioni hai vinto ed hai completato tutto il gioco!!!\n"
                        + "Tu ed i tuoi amici ora siete liberi!");
            }
            else
            {
                artGen.printTextArt("Sei una merda", ASCIIArtGenerator.ART_SIZE_LARGE,  ASCIIArtGenerator.ASCIIArtFont.ART_FONT_MONO, "@");
                out.println("Hai vinto si, ma hai lasciato indietro degli amici. Sei una merda.");
            }
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
    }
}
