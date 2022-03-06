/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import com.mycompany.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        out.println("Sembra ci sia qualcosa... Con la pinzetta riesci a tirarla fuori. E' un foglietto");
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
                    String comb = in.nextLine();
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
                            toRemove.add(o);
                            score += s;
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
                + "Per muoverti attraverso la mappa:"
                + "Nord o n\n"
                + "Sud o s\n"
                + "Ovest o o\n"
                + "Est o e\n"); 
    }
    
    private boolean exit(PrintStream out) {
        String answer="";
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        
        do{
            out.println("Sei sicuro? (S/N)");
            answer = in.nextLine();
        }while(!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));
        
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
        if (score == 100)
            out.println("Congratulazioni hai vinto ed hai completato tutto il gioco!!!\n"
                    + "Tu ed i tuoi amici ora siete liberi!");
        else
            out.println("Hai vinto si, ma hai lasciato indietro degli amici. Sei una merda.");
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File gameFile = new File("res/game");
        File parserFile = new File("res/stopwords");
        try
        {
            Game game = Utils.gameLoader(gameFile);
            Parser p = new Parser(Utils.loadFileListInSet(parserFile));
            boolean exit = false;
            
            System.out.println("Benvenuto in escapeRoom yeee\n"
                    + "Sei stato imprigionato insieme ai tuoi 3 amici ad Alcatraz, il tuo obiettivo è uscire da li.\n"
                    + "Ogni prigioniero ha una sua cella, puoi cambiare personaggio con il comando \"Cambia\" seguito dal nome del prigioniero.\n"
                    + "I prigionieri sono: Luca, Niky, Riky e Ronny\n"
                    + "Ci saranno molti lucchetti. Quelli a combinazione, se non indicato diversamente, saranno combinazioni a tre cifre.\n"
                    + "Buona Fortuna");
            System.out.println(game.getCurrentPrisoner().toString());
            System.out.println(game.getCurrentRoom().getName());
            System.out.println(game.getCurrentRoom().getDesc());
            
            ParserOutput po = null;
            List<MyObject> objects = new ArrayList();
            do
            {
               
                objects.addAll(game.getInventary());
                objects.addAll(game.getCurrentRoom().getAvailObj());
                
                Scanner in = new Scanner(System.in);
                
                String command = "";
                
                while(command.isEmpty())
                    command = in.nextLine();
                
                po = p.parse(command, objects, game.getCurrentRoom(), game.getRooms());
                
                exit = game.nextMove(po, System.out);
                
                objects.removeAll(objects);
                
            }while(!exit);
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
