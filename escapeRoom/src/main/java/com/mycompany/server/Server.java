/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Server {
    
    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS rank(id INT PRIMARY KEY, user VARCHAR(1024), score INT, date VARCHAR(25))";
    public static String INSERT = "INSERT INTO rank VALUES(?,?,?,?)";
    public static String MAXID = "SELECT MAX(id) FROM rank";
    public static String EMPTY_TABLE = "SELECT COUNT(*) FROM rank";
    public static String VIEW = "SELECT * FROM rank";
    
    public static class ServerThread extends Thread {

        private final Socket socket;
        
        private final Connection conn; 
        
        private final Object Lock = new Object();

        public ServerThread(Socket socket, Connection conn) {
            this.socket = socket;
            this.conn = conn;
        }
        
        
        public void writeDB(Protocol msg) throws SQLException {
            msg.setFlag(true);

            PreparedStatement pstm = conn.prepareStatement(INSERT);

            pstm.setString(2, msg.getUsername());
            pstm.setInt(3, msg.getScore());
            pstm.setString(4, msg.getDate());

            synchronized (Lock) {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(EMPTY_TABLE);

                rs.next();
                if (rs.getInt(1) == 0) {
                    pstm.setInt(1, 1);
                    stm.close();
                } else {
                    stm.close();
                    stm = conn.createStatement();
                    rs = stm.executeQuery(MAXID);
                    rs.next();
                    int nextId = rs.getInt(1) + 1;
                    stm.close();
                    rs.close();

                    pstm.setInt(1, nextId);
                }

                pstm.executeUpdate();
                pstm.close();
            }
        }
        
        public void readDB(ObjectOutputStream out) throws SQLException, IOException {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(VIEW);
            
            while(rs.next())
            {
                Protocol msg = new Protocol(rs.getString(2), rs.getInt(3), rs.getString(4));
                
                if(rs.isLast())
                    msg.setFlag(true);
                
                out.writeObject(msg);
            }
            rs.close();
            stm.close();
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                Protocol msg = (Protocol) in.readObject();
                
                try {
                    if(msg.getOp())
                        writeDB(msg);
                    else
                        readDB(out);
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    msg.setFlag(false);
                } finally {
                    if(msg.getOp())
                        out.writeObject(msg);
                    System.out.println("\tConnessione con client " + socket.getInetAddress() + " terminata");
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./res/db/ranking");
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
            
            ServerSocket s = new ServerSocket(6666);
            System.out.println("Avviato");
            
            try {
                while (true) {
                    Socket socket = s.accept();
                    System.out.println("Connessione con client " + socket.getInetAddress() + " accettata");
                    Thread st = new ServerThread(socket, conn);
                    st.start();
                }
            } finally {
                conn.close();
                s.close();
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
