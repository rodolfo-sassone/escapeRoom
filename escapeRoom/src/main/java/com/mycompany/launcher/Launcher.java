/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.launcher;

import com.mycompany.server.Protocol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class Launcher extends javax.swing.JFrame {

    private String[] command = null;

    public String[] getCommand() {
        return command;
    }

    public void setCommand(String[] command) {
        this.command = command;
    }
    
    protected class TerminalAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "gnometerminal":
                    command = new String[]{"gnome-terminal", "-e", "java -cp escapeRoom-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.escaperoom.Main"};
                    break;
                case "xterm":
                    command = new String[]{"xterm", "-e", "java -cp escapeRoom-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.escaperoom.Main"};
                    break;
                case "konsole":
                    command = new String[]{"konsole", "-e", "java -cp escapeRoom-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.escaperoom.Main"};
                    break;
            }
        }
    }
    
    /**
     * Creates new form Launcher
     */
    public Launcher() {
        initComponents();
        myInit();
    }

    private void myInit() {
        jLabel1.setIcon(new javax.swing.ImageIcon("./res/img/escapeRoom.png"));
        
        TerminalAction ta = new TerminalAction();
        xterm.addActionListener(ta);
        gnometerminal.addActionListener(ta);
        konsole.addActionListener(ta);
        
        ButtonGroup terminals = new ButtonGroup();
        terminals.add(xterm);
        terminals.add(gnometerminal);
        terminals.add(konsole);
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
        jDialog2 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        xterm = new javax.swing.JRadioButton();
        gnometerminal = new javax.swing.JRadioButton();
        konsole = new javax.swing.JRadioButton();
        confirm = new javax.swing.JButton();
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

        jDialog2.setTitle("Selezione terminale");
        jDialog2.setResizable(false);

        jLabel2.setText("<html>\nSeleziona un terminale fra questi. Se non hai installato nessuno di questi<br>\ninstallane uno e riavvia il gioco\n</html>");

        xterm.setText("xterm");
        xterm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xtermActionPerformed(evt);
            }
        });

        gnometerminal.setText("gnome terminal");
        gnometerminal.setActionCommand("gnometerminal");
        gnometerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnometerminalActionPerformed(evt);
            }
        });

        konsole.setText("konsole");
        konsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konsoleActionPerformed(evt);
            }
        });

        confirm.setText("Conferma");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(konsole, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gnometerminal)
                            .addComponent(xterm)
                            .addComponent(confirm)))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gnometerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(xterm)
                .addGap(15, 15, 15)
                .addComponent(konsole, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirm)
                .addContainerGap(43, Short.MAX_VALUE))
        );

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
            String os = System.getProperty("os.name").toLowerCase();

            if(os.contains("win"))
                rt.exec("cmd.exe /c start java -cp escapeRoom-1.0-SNAPSHOT-jar-with-dependencies.jar com.mycompany.escaperoom.Main");
            //else if(os.contains("mac"))
            else if(os.contains("nux"))
            {
                jDialog2.pack();
                jDialog2.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(null, null, "Sistema operativo non supportato", ERROR);
        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_playActionPerformed

    private void konsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konsoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_konsoleActionPerformed

    private void gnometerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnometerminalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gnometerminalActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        if (command != null)
        {
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec(command);
            } catch (IOException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Devi selezionare un terminale", "Errore", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_confirmActionPerformed

    private void xtermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xtermActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xtermActionPerformed

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
    private javax.swing.JButton confirm;
    private javax.swing.JRadioButton gnometerminal;
    private javax.swing.JButton help;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JRadioButton konsole;
    private javax.swing.JButton play;
    private javax.swing.JButton rank;
    private javax.swing.JRadioButton xterm;
    // End of variables declaration//GEN-END:variables

    
}
