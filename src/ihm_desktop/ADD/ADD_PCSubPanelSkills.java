/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm_desktop.ADD;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import xml.XMLParser;

/**
 *
 * @author nicolas
 */
public class ADD_PCSubPanelSkills extends javax.swing.JPanel {

  /**
   * Creates new form ADD_PCSubPanelSkills
   */
  public ADD_PCSubPanelSkills() {
    initComponents();
    
    setSize(232,284);
    setVisible(true);
  }
  
  public void loadSkills(HashMap<String,Integer> characterSkills){
    
    XMLParser xml = new XMLParser();
    HashMap<String,String> skills = xml.getDDSkills();
    
    DefaultTableModel model = (DefaultTableModel) jtableSkills.getModel();
    for(String key : characterSkills.keySet()){
      model.addRow(new Object[]{key,characterSkills.get(key),skills.get(key)});
    }
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
    jtableSkills = new javax.swing.JTable();

    setMaximumSize(new java.awt.Dimension(232, 284));
    setMinimumSize(new java.awt.Dimension(232, 284));
    setPreferredSize(new java.awt.Dimension(232, 284));

    jtableSkills.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Skills", "Score", "Attr."
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Integer.class, java.lang.String.class
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
    });
    jScrollPane1.setViewportView(jtableSkills);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(13, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(13, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jtableSkills;
  // End of variables declaration//GEN-END:variables
}