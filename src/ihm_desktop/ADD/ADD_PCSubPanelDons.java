/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm_desktop.ADD;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import xml.XMLParser;

/**
 *
 * @author nicolas
 */
public class ADD_PCSubPanelDons extends javax.swing.JPanel {

  /**
   * Creates new form ADD_PCSubPanelDons
   */
  public ADD_PCSubPanelDons() {
    initComponents();
    
    setSize(232,284);
    setVisible(true);
  }
  
  public void loadDons(HashMap<String,Integer> characterTalents){
    XMLParser xml = new XMLParser();
    HashMap<String,ArrayList<String>> talents = xml.getDDTalents();
    
    DefaultListModel model = new DefaultListModel();
    for(String talent : characterTalents.keySet()){
      model.addElement(talents.get(talent).get(0) + " " 
                      + (characterTalents.get(talent) == 1 ? "" : characterTalents.get(talent))
                      );
    }
    jlistDons.setModel(model);
    
    // recuperer la liste des dons, ensuite parser le fichier xml pour avoir tous les dons avec leur id
    // afficher les infos dont on a besoin au moment ou on en a besoin... eazy peazy !
  }
  
  

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jlistDons = new javax.swing.JList<>();
    jScrollPane2 = new javax.swing.JScrollPane();
    jtextIntelDons = new javax.swing.JTextArea();

    setMaximumSize(new java.awt.Dimension(232, 284));
    setMinimumSize(new java.awt.Dimension(232, 284));
    setPreferredSize(new java.awt.Dimension(232, 284));

    jlistDons.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jlistDonsMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(jlistDons);

    jtextIntelDons.setColumns(20);
    jtextIntelDons.setRows(5);
    jtextIntelDons.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jtextIntelDonsMouseClicked(evt);
      }
    });
    jScrollPane2.setViewportView(jtextIntelDons);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jtextIntelDonsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtextIntelDonsMouseClicked
    
  }//GEN-LAST:event_jtextIntelDonsMouseClicked

  private void jlistDonsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlistDonsMouseClicked
    if(evt.getClickCount() == 2){
      XMLParser xml = new XMLParser();
      HashMap<String,ArrayList<String>> talents = xml.getDDTalents();
      String toDisplay = jlistDons.getSelectedValue();
      for(String key : talents.keySet()){
        if(talents.get(key).get(0).equalsIgnoreCase(toDisplay)){
          Document doc = jtextIntelDons.getDocument();
          try{
            doc.remove(0, doc.getLength());
          }
          catch(BadLocationException e){
            e.printStackTrace();
          }
          if(talents.get(key).size() > 1){
            try{
              doc.insertString(doc.getLength(),talents.get(key).get(1),null);
              doc.insertString(doc.getLength(),"\r\n",null);
              if(talents.get(key).size() > 2){
                doc.insertString(doc.getLength(),"Prerequisite: " 
                                                      + talents.get(key).get(2),null);
                doc.insertString(doc.getLength(),"\r\n",null);
              }
            }
            catch(BadLocationException e){
              e.printStackTrace(); 
            }
          }
          else{
            try{
              doc.insertString(doc.getLength(),"No description available.",null);
              doc.insertString(doc.getLength(),"\r\n",null);
            }
            catch(BadLocationException e){
              e.printStackTrace(); 
            }
          }
        }
      }
    } //if count == 2
  }//GEN-LAST:event_jlistDonsMouseClicked


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JList<String> jlistDons;
  private javax.swing.JTextArea jtextIntelDons;
  // End of variables declaration//GEN-END:variables
}