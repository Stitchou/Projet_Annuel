/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import Plugins.Gestionnaire;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableColumn;

import traitment.*;
/**
 *
 * @author 626
 */
public class MyUI extends JFrame {

    /**
     * Creates new form MyUI
     */
    public static ArrayList<Gestionnaire> list=new ArrayList<>();
    public static ArrayList<String> names = new ArrayList<String>();
    
    
    public MyUI() {
        
    }
    public MyUI(ArrayList<Gestionnaire> laListe,ArrayList<String> lesnames)
    {
        list=laListe;
        names=lesnames;
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        
        // listeneur 
        Ecouteur ec= new Ecouteur();
        addWindowListener(ec);

        // paramétrage de la fenetre 
        setTitle("Warning Comunity ");
        setSize(d.width/2,d.height/2);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableEvent = new Table(new TableModel());
        tableUser = new Table(new TableModel("user"));
        tableAppli = new Table(new TableModel("appli"));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/a3 (Copier).png"))); // NOI18N
        jButton1.setText("Consulter");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/a6 (Copier).png"))); // NOI18N
        jButton2.setText("Modifier");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/a2 (Copier).png"))); // NOI18N
        jButton3.setText("Supprimer");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/a7 (Copier).png"))); // NOI18N
        jButton4.setText("Rafraichir");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        
        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        //Ajout des differents onglet au CardLayout
        jTabbedPane2.addTab("Log de connexion", jScrollPane1);
        jTabbedPane2.addTab("Liste des Events", jScrollPane2);
        jTabbedPane2.addTab("Liste des Utilisateurs", jScrollPane3);
        jTabbedPane2.addTab("Liste des Applications Connectees", jScrollPane4);
        
        jMenu1.setText("Fichier");
        jMenu2.setText("Plugins");

        jMenuItem1.setText("Consulter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Modifier");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Supprimer");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Quitter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        // MENU PLUGIN
        for (final String h : names) {
            final JMenuItem qMenu = new JMenuItem(h);
            jMenu2.add(qMenu);
            qMenu.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent event) {
                     list.get(names.indexOf(qMenu.getText())).start();
                 }               
            });
        }
        
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        
        ImageIcon img = new ImageIcon("./src/img/a4.png");
        this.setIconImage(img.getImage());
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // item menu consulter
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // item menu quitter
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Ecouteur ec = new Ecouteur();
        ec.windowClosing(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //bouton toolbar consulter
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Switch pour savoir quel onglet est s�lectionner (Onglet numerote a partir de 0) 0 : Logs / 1 : Event / 2 : User / 3 : Appli Users Connected
    	switch(jTabbedPane2.getSelectedIndex())
    	{
    	case 0:
    		Logs lect= new Logs();
            String mots=lect.lire("./Logs/fermeture.txt");
            jTextArea1.setText(mots);
    		break;
    	case 1 : 
    		jScrollPane2.setViewportView(tableEvent);
    		break;
    	case 2 :
    		jScrollPane3.setViewportView(tableUser);
    		break;
    	case 3:
    		jScrollPane4.repaint();
    		jScrollPane4.setViewportView(tableAppli);
    		break;
    	default : break;
    	}
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // bouton toolbar modifier
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    	System.out.println("Modification");
		String []userTab = {"pseudo", "nom", "prenom", "mail", "level"};
		String []eventTab = {"type_event", "date_debut", "date_fin"};
		BDDConnect bc = new BDDConnect();
		int rows = 0;
    	switch( jTabbedPane2.getSelectedIndex() ){
    		case 0:
    			break;
    		case 1:
    			rows = tableEvent.getRowCount();
    			String []eventValue = new String[3];
    			try{
    				if(JOptionPane.showConfirmDialog(null, "Sur ?", "Modification", 0) == 0){
	    				for(int i = 0; i < rows; i++){
	    					bc.selectTwo("type_event", " type_event ", " WHERE nom=\"" + tableEvent.getValueAt(i, 2) + "\"");
	    					ResultSet rs = bc.getRs();
	    					rs.next();
	    					eventValue[0] = rs.getString(1);
	    					eventValue[1] = (String) tableEvent.getValueAt(i, 3);
	    					eventValue[2] = (String) tableEvent.getValueAt(i, 4);
	    					bc.update("events", eventTab, eventValue, " event_id = " + tableEvent.getValueAt(i, 0));
	    				}
    				}
    			}catch(Exception e){
    				e.getMessage();
    			}
    			break;
    		case 2:
    			rows = tableUser.getRowCount();
    			String []userValue = new String[5];
    			try{
    				if(JOptionPane.showConfirmDialog(null, "Sur ?", "Modification", 0) == 0){
	    				for(int i = 0; i < rows; i++){
	    					userValue[0] = (String) tableUser.getValueAt(i, 1);
	    					userValue[1] = (String) tableUser.getValueAt(i, 2);
	    					userValue[2] = (String) tableUser.getValueAt(i, 3);
	    					userValue[3] = (String) tableUser.getValueAt(i, 4);
                                                userValue[4] = (String) tableUser.getValueAt(i, 5);
	    					bc.update("users", userTab, userValue, " users_id = " + tableUser.getValueAt(i, 0));
	    				}
    				}
    			}catch(Exception e){
    				e.getMessage();
    			}
    			break;
    	}
    }//GEN-LAST:event_jButton2ActionPerformed

    //bouton toolbar supprimer
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    	System.out.println("Supp");
		BDDConnect bc = new BDDConnect();
		int rowCount = 0;
    	switch( jTabbedPane2.getSelectedIndex() ){
    		case 0:
    			break;
    		case 2: // user suppression
    			rowCount = tableUser.getSelectedRowCount();
    			try{
    				if( rowCount == 1 ){
    					int rowIdx = tableUser.getSelectedRow();
    					if(JOptionPane.showConfirmDialog(null, "Sur ?", "Suppression", 0) == 0)
    						bc.delete("users", " users_id = " + tableUser.getValueAt(rowIdx, 0));
    					jButton4ActionPerformed(evt);
    				} else if( rowCount > 1 ){
    					int []rowIdx = tableUser.getSelectedRows();
    					if(JOptionPane.showConfirmDialog(null, "Sur ?", "Suppression", 0) == 0){
    						for(int i = 0; i < rowCount; i++ )
    							bc.delete("users", "users_id = " + tableUser.getValueAt(rowIdx[i], 0));
    					}
    				}
    			}catch(Exception e){
    				e.getMessage();
    			}
    			break;
    		case 1: // event suppression
    			rowCount = tableEvent.getSelectedRowCount();
    			try{
    				if( rowCount == 1 ){
    					int rowIdx = tableEvent.getSelectedRow();
    					if(JOptionPane.showConfirmDialog(null, "Sur ?", "Suppression", 0) == 0)
    						bc.delete("events", " event_id = " + tableEvent.getValueAt(rowIdx, 0));
    					// rafraichir la fenetre
    					jButton4ActionPerformed(evt);
    				} else if( rowCount > 1 ){
    					// Recuperation d'un tableau de int qui contient les lignes selectionn�
    					int []rowIdx = tableEvent.getSelectedRows();
    					if(JOptionPane.showConfirmDialog(null, "Sur ?", "Suppression", 0) == 0){
    						for(int i = 0; i < rowCount; i++ )
    							bc.delete("events", "event_id = " + tableEvent.getValueAt(rowIdx[i], 0));
    					}
    					// rafraichir la fenetre
    					jButton4ActionPerformed(evt);
    				}
    			}catch(Exception e){
    				e.getMessage();
    			}
    			break;
    	}
    }//GEN-LAST:event_jButton3ActionPerformed
    
  //bouton toolbar refresh
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    	  
        switch(jTabbedPane2.getSelectedIndex())
    	{
    	case 0:
    		Logs lect= new Logs();
            String mots=lect.lire("./Logs/fermeture.txt");
            jTextArea1.setText(mots);
    		break;
    	case 1 : 
    		tableEvent = new Table(new TableModel());
    		jScrollPane2.setViewportView(tableEvent);
    		jScrollPane2.repaint();
    		break;
    	case 2 :
    		tableUser = new Table(new TableModel("user"));
    		jScrollPane3.setViewportView(tableUser);
    		jScrollPane3.repaint();
    		break;
    	case 3:
    		tableAppli = new Table(new TableModel("appli"));
    		jScrollPane4.setViewportView(tableAppli);
    		jScrollPane4.repaint();
    		break;
    	default : break;
    	}
    }//GEN-LAST:event_jButton3ActionPerformed

    // item menu modifier
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jButton2ActionPerformed(evt);
        System.out.println("Modifier");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    // item menu supprimer 
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jButton3ActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
     private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private Table tableEvent;
    private Table tableUser;
    private Table tableAppli;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
