/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.launcher;

import com.mycompany.server.Protocol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Launcher extends javax.swing.JFrame {

    /**
     * Creates new form Launcher
     */
    public Launcher() {
        initComponents();
        myInit();
    }

    private void myInit() {
        jLabel1.setIcon(new javax.swing.ImageIcon("./res/img/escapeRoom.png"));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        play = new javax.swing.JButton();
        help = new javax.swing.JButton();
        rank = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTextPane1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(255, 204, 0));
        jTextPane1.setPreferredSize(new java.awt.Dimension(400, 300));
        jScrollPane1.setViewportView(jTextPane1);

        jDialog1.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EscapeRoom: Alcatraz");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        setForeground(java.awt.Color.yellow);
        setPreferredSize(new java.awt.Dimension(1005, 445));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        play.setBackground(new java.awt.Color(0, 0, 0));
        play.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        play.setForeground(new java.awt.Color(255, 204, 0));
        play.setText("Gioca");
        play.setToolTipText("Si parte!");
        play.setFocusable(false);
        play.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        play.setPreferredSize(new java.awt.Dimension(200, 35));
        play.setRolloverEnabled(false);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        getContentPane().add(play, gridBagConstraints);

        help.setBackground(new java.awt.Color(0, 0, 0));
        help.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        help.setForeground(new java.awt.Color(255, 204, 0));
        help.setText("Aiuto");
        help.setToolTipText("First time?");
        help.setFocusable(false);
        help.setPreferredSize(new java.awt.Dimension(200, 35));
        help.setRolloverEnabled(false);
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(help, gridBagConstraints);

        rank.setBackground(new java.awt.Color(0, 0, 0));
        rank.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        rank.setForeground(new java.awt.Color(255, 204, 0));
        rank.setText("Ranking");
        rank.setToolTipText("Classifica altri giocatori");
        rank.setFocusable(false);
        rank.setPreferredSize(new java.awt.Dimension(200, 35));
        rank.setRolloverEnabled(false);
        rank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        getContentPane().add(rank, gridBagConstraints);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        getContentPane().add(jLabel1, gridBagConstraints);

        jSeparator1.setPreferredSize(new java.awt.Dimension(0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jSeparator2.setPreferredSize(new java.awt.Dimension(0, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jSeparator2, gridBagConstraints);

        jSeparator3.setPreferredSize(new java.awt.Dimension(0, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jSeparator3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankActionPerformed
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jDialog1.setPreferredSize(new java.awt.Dimension(600, 400));
        jTextPane1.setPreferredSize(new java.awt.Dimension(600, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 400));

        jDialog1.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jDialog1.setBackground(new java.awt.Color(0, 0, 0));
        
        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html");
        jTextPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTextPane1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        jScrollPane1.setViewportView(jTextPane1);
        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        
        jDialog1.getContentPane().add(jScrollPane1);
        
        jDialog1.pack();
        jDialog1.setVisible(true);
        
        StringBuilder sb;
        
        File rankFile = new File("./res/rank.html");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(rankFile));
            sb = new StringBuilder();
            while(reader.ready())
            {
                sb.append(reader.readLine()).append("\n");
            }
            reader.close();

            InetAddress addr = InetAddress.getByName("localhost");
            Socket s = new Socket(addr, 6666);
            try {
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());

                //request to server
                Protocol msg = new Protocol();
                out.writeObject(msg);
                do {
                    sb.append("<tr>");
                    msg = (Protocol) in.readObject();
                    sb.append("<td>" + msg.getUsername() + "</td>");
                    sb.append("<td>" + msg.getScore() + "</td>");
                    sb.append("<td>" + msg.getDate() + "</td>");
                    sb.append("</tr>");
                }while(msg.getFlag() == false);

                sb.append("</table>");
                sb.append("</p>");
                sb.append("</body>");
                sb.append("</html>");
                
                jTextPane1.setText(sb.toString());
            
            }finally {
                s.close();
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rankActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jDialog1.setPreferredSize(new java.awt.Dimension(1000, 800));
        jTextPane1.setPreferredSize(new java.awt.Dimension(1000, 800));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 800));

        jDialog1.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html");
        jTextPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTextPane1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jScrollPane1.setViewportView(jTextPane1);

        jDialog1.getContentPane().add(jScrollPane1);

        File helpFile = new File("./res/help.html");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(helpFile));
            StringBuilder sb = new StringBuilder();
            while(reader.ready())
            {
                sb.append(reader.readLine()).append("\n");
            }
            reader.close();
            jTextPane1.setText(sb.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        jScrollPane1.setAlignmentY(TOP_ALIGNMENT);
        jScrollPane1.setAlignmentX(TOP_ALIGNMENT);
        jDialog1.pack();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_helpActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        Runtime rt = Runtime.getRuntime();
        try {
            //String os = System.getProperty("os.name").toLowerCase();

            //if(os.contains("win"))
            rt.exec("cmd.exe /c start java -cp escapeRoom-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.escaperoom.Main");
            //else if(os.contains("mac"))
            //stackoverflow
            /*else if(os.contains("nux"))
            {
                //(dialog o simili)seleziona un terminale tra questi: se non ne hai nessuno scaricane uno
            }
            //else*/

        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_playActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Launcher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton help;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton play;
    private javax.swing.JButton rank;
    // End of variables declaration//GEN-END:variables

    
}
