/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm_desktop.scenario;

import java.awt.Dimension;
import java.io.File;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import rpgTools.GameType;
import rpgTools.Scenario;
import xml.XMLParser;

/**
 *
 * @author nicolas
 */
public class ScenarioPanel extends javax.swing.JPanel {
  
  private String path;
  private GameType relatedGame;
  private Scenario relatedScenario;
  private int countPrime;
  private int countSecond;
  private int countRandom;

  /**
   * Creates new form ScenarioPanel
   */
  public ScenarioPanel() {
    initComponents();
    
    jlblTitle.setPreferredSize(new Dimension(400,14));
    jlblTitle.setMinimumSize(new Dimension(400,14));
    jlblTitle.setMaximumSize(new Dimension(400,14));
    jlblGame.setPreferredSize(new Dimension(100,14));
    jlblGame.setMinimumSize(new Dimension(100,14));
    jlblGame.setMaximumSize(new Dimension(100,14));
    jlblIntelPrime.setPreferredSize(new Dimension(198,14));
    jlblIntelPrime.setMinimumSize(new Dimension(198,14));
    jlblIntelPrime.setMaximumSize(new Dimension(198,14));
    jlblIntelSecond.setPreferredSize(new Dimension(198,14));
    jlblIntelSecond.setMinimumSize(new Dimension(198,14));
    jlblIntelSecond.setMaximumSize(new Dimension(198,14));
    jlblFailure.setPreferredSize(new Dimension(331,14));
    jlblFailure.setMinimumSize(new Dimension(331,14));
    jlblFailure.setMaximumSize(new Dimension(331,14));
    jlblSuccess.setPreferredSize(new Dimension(331,14));
    jlblSuccess.setMinimumSize(new Dimension(331,14));
    jlblSuccess.setMaximumSize(new Dimension(331,14));
    setSize(760,470);
    setVisible(true);
  }
  
  /**
   * 
   * @param gameType 
   */
  public void setGameType(GameType gameType){
    this.relatedGame = gameType;
    this.path = "";
    switch(this.relatedGame.getGame()){
      case "AD&D":
        this.path += "donjons_and_dragons/";
        break;
      case "Patient 13":
        this.path += "patient13/";
        break;
      case "Shadowrun":
        this.path += "shadowrun/";
        break;
      case "Feng Shui":
        this.path += "feng_shui/";
        break;
      case "Cthulhu":
        this.path += "cthulhu/";
        break;
    }
    
    File folder = new File(System.getProperty("user.dir") + "/data/scenarii/" + this.path);
    File[] list = folder.listFiles();
    for(int i = 0 ; i < list.length ; ++i){
      jcbbScenario.addItem(list[i].getName().replaceAll(".xml",""));
    }
    jbtnPrevPrime.setEnabled(false);
    jbtnNextPrime.setEnabled(false);
    jbtnPrevSecond.setEnabled(false);
    jbtnNextSecond.setEnabled(false);
    jbtnPrevRandom.setEnabled(false);
    jbtnNextRandom.setEnabled(false);
  }
  
  /**
   * 
   * @param toLoad 
   */
  public void loadScenario(Scenario toLoad){
    this.relatedScenario = toLoad;
    jlblTitle.setText(this.relatedScenario.getTitle());
    jlblGame.setText(this.relatedScenario.getGameType());
    Document doc = jtextSummary.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getSummary(),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
      
    int countPrime = 0;
    int countSecond = 0;
    int countRandom = 0;
    doc = jtextPrime.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countPrime, "prime"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
    jlblIntelPrime.setText("Event " + countPrime + " of " + (this.relatedScenario.getPrime() - 1));
    jbtnPrevPrime.setEnabled(false);
    if(countPrime == (this.relatedScenario.getPrime() - 1)){
      jbtnNextPrime.setEnabled(false);  
    }
    else{
      jbtnNextPrime.setEnabled(true);  
    }
    doc = jtextSecond.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countSecond, "secondary"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
    jlblIntelSecond.setText("Event " + countSecond + " of " + (this.relatedScenario.getSecond() - 1));
    jbtnPrevSecond.setEnabled(false);
    if(countSecond == (this.relatedScenario.getSecond() - 1)){
      jbtnNextSecond.setEnabled(false);  
    }
    else{
      jbtnNextSecond.setEnabled(true);  
    }
    
    doc = jtextRandom.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countPrime, "random"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
    jbtnPrevRandom.setEnabled(false);
    if(countRandom == (this.relatedScenario.getRandom() - 1)){
      jbtnNextRandom.setEnabled(false);
    }
    else{
      jbtnNextRandom.setEnabled(true);  
    }
    
    jlblSuccess.setText("Success: " + this.relatedScenario.getResult("success"));
    jlblFailure.setText("Failure: " + this.relatedScenario.getResult("failure"));
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jlblTitle = new javax.swing.JLabel();
    jlblGame = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jtextSummary = new javax.swing.JTextArea();
    jScrollPane2 = new javax.swing.JScrollPane();
    jtextSecond = new javax.swing.JTextArea();
    jScrollPane3 = new javax.swing.JScrollPane();
    jtextPrime = new javax.swing.JTextArea();
    jbtnPrevPrime = new javax.swing.JButton();
    jbtnNextPrime = new javax.swing.JButton();
    jbtnNextSecond = new javax.swing.JButton();
    jbtnPrevSecond = new javax.swing.JButton();
    jlblIntelPrime = new javax.swing.JLabel();
    jlblIntelSecond = new javax.swing.JLabel();
    jScrollPane4 = new javax.swing.JScrollPane();
    jtextRandom = new javax.swing.JTextArea();
    jlblSuccess = new javax.swing.JLabel();
    jlblFailure = new javax.swing.JLabel();
    jbtnPrevRandom = new javax.swing.JButton();
    jbtnNextRandom = new javax.swing.JButton();
    jcbbScenario = new javax.swing.JComboBox<>();
    jButton1 = new javax.swing.JButton();

    setMaximumSize(new java.awt.Dimension(760, 470));
    setMinimumSize(new java.awt.Dimension(760, 470));

    jtextSummary.setColumns(20);
    jtextSummary.setLineWrap(true);
    jtextSummary.setRows(5);
    jScrollPane1.setViewportView(jtextSummary);

    jtextSecond.setColumns(20);
    jtextSecond.setLineWrap(true);
    jtextSecond.setRows(5);
    jScrollPane2.setViewportView(jtextSecond);

    jtextPrime.setColumns(20);
    jtextPrime.setLineWrap(true);
    jtextPrime.setRows(5);
    jScrollPane3.setViewportView(jtextPrime);

    jbtnPrevPrime.setText("<<");
    jbtnPrevPrime.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnPrevPrimeActionPerformed(evt);
      }
    });

    jbtnNextPrime.setText(">>");
    jbtnNextPrime.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnNextPrimeActionPerformed(evt);
      }
    });

    jbtnNextSecond.setText(">>");
    jbtnNextSecond.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnNextSecondActionPerformed(evt);
      }
    });

    jbtnPrevSecond.setText("<<");
    jbtnPrevSecond.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnPrevSecondActionPerformed(evt);
      }
    });

    jtextRandom.setColumns(20);
    jtextRandom.setLineWrap(true);
    jtextRandom.setRows(5);
    jScrollPane4.setViewportView(jtextRandom);

    jbtnPrevRandom.setText("<<");
    jbtnPrevRandom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnPrevRandomActionPerformed(evt);
      }
    });

    jbtnNextRandom.setText(">>");
    jbtnNextRandom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnNextRandomActionPerformed(evt);
      }
    });

    jButton1.setText("V");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jlblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jlblGame, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jcbbScenario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jlblIntelPrime, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnPrevPrime)
                .addGap(18, 18, 18)
                .addComponent(jbtnNextPrime)
                .addGap(0, 0, Short.MAX_VALUE))
              .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(jlblSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jlblIntelSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jbtnPrevSecond)
                    .addGap(18, 18, 18)
                    .addComponent(jbtnNextSecond))
                  .addComponent(jlblFailure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addGap(216, 216, 216)
        .addComponent(jbtnPrevRandom)
        .addGap(18, 18, 18)
        .addComponent(jbtnNextRandom)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jlblTitle)
          .addComponent(jlblGame)
          .addComponent(jcbbScenario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButton1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(jbtnNextPrime, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jbtnPrevPrime, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
          .addComponent(jbtnNextSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jbtnPrevSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jlblIntelPrime)
          .addComponent(jlblIntelSecond))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jlblSuccess)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jlblFailure)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jbtnNextRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jbtnPrevRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jbtnPrevPrimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrevPrimeActionPerformed
    countPrime--;
    if(countPrime == 0){
      jbtnPrevPrime.setEnabled(false);
    }
    if(!jbtnNextPrime.isEnabled()){
      jbtnNextPrime.setEnabled(true);
    }
    Document doc = jtextPrime.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countPrime, "prime"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
      jlblIntelPrime.setText("Event " + countPrime + " of " + (this.relatedScenario.getPrime() - 1));
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnPrevPrimeActionPerformed

  private void jbtnNextPrimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextPrimeActionPerformed
    countPrime++;
    if(countPrime == (this.relatedScenario.getPrime() - 1)){
      jbtnNextPrime.setEnabled(false);
    }
    if(!jbtnPrevPrime.isEnabled()){
      jbtnPrevPrime.setEnabled(true);
    }
    Document doc = jtextPrime.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countPrime, "prime"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
      jlblIntelPrime.setText("Event " + countPrime + " of " + (this.relatedScenario.getPrime() - 1));
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnNextPrimeActionPerformed

  private void jbtnPrevSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrevSecondActionPerformed
    countSecond--;
    if(countSecond == 0){
      jbtnPrevSecond.setEnabled(false);
    }
    if(!jbtnNextSecond.isEnabled()){
      jbtnNextSecond.setEnabled(true);
    }
    Document doc = jtextSecond.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countSecond, "secondary"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
      jlblIntelSecond.setText("Event " + countSecond + " of " + (this.relatedScenario.getSecond() - 1));
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnPrevSecondActionPerformed

  private void jbtnNextSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextSecondActionPerformed
    countSecond++;
    if(countSecond == (this.relatedScenario.getSecond() - 1)){
      jbtnNextSecond.setEnabled(false);
    }
    if(!jbtnPrevSecond.isEnabled()){
      jbtnPrevSecond.setEnabled(true);
    }
    Document doc = jtextSecond.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countSecond, "secondary"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
      jlblIntelSecond.setText("Event " + countSecond + " of " + (this.relatedScenario.getSecond() - 1));
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnNextSecondActionPerformed

  private void jbtnPrevRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrevRandomActionPerformed
    countRandom--;
    if(countRandom == 0){
      jbtnPrevRandom.setEnabled(false);
    }
    if(!jbtnNextRandom.isEnabled()){
      jbtnNextRandom.setEnabled(true);
    }
    Document doc = jtextRandom.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countRandom, "random"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnPrevRandomActionPerformed

  private void jbtnNextRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextRandomActionPerformed
    countRandom++;
    if(countRandom == (this.relatedScenario.getRandom() - 1)){
      jbtnNextRandom.setEnabled(false);
    }
    if(!jbtnPrevRandom.isEnabled()){
      jbtnPrevRandom.setEnabled(true);
    }
    Document doc = jtextRandom.getDocument();
    try{
      doc.remove(0, doc.getLength());
      doc.insertString(doc.getLength(),this.relatedScenario.getElement(countRandom, "random"),null);
      doc.insertString(doc.getLength(),"\r\n",null);
    }
    catch(BadLocationException e){
      e.printStackTrace();
    }
  }//GEN-LAST:event_jbtnNextRandomActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    XMLParser xml = new XMLParser();
    loadScenario(xml.loadScenario(System.getProperty("user.dir") + "/data/scenarii/" + this.path + jcbbScenario.getSelectedItem() + ".xml"));
  }//GEN-LAST:event_jButton1ActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JButton jbtnNextPrime;
  private javax.swing.JButton jbtnNextRandom;
  private javax.swing.JButton jbtnNextSecond;
  private javax.swing.JButton jbtnPrevPrime;
  private javax.swing.JButton jbtnPrevRandom;
  private javax.swing.JButton jbtnPrevSecond;
  private javax.swing.JComboBox<String> jcbbScenario;
  private javax.swing.JLabel jlblFailure;
  private javax.swing.JLabel jlblGame;
  private javax.swing.JLabel jlblIntelPrime;
  private javax.swing.JLabel jlblIntelSecond;
  private javax.swing.JLabel jlblSuccess;
  private javax.swing.JLabel jlblTitle;
  private javax.swing.JTextArea jtextPrime;
  private javax.swing.JTextArea jtextRandom;
  private javax.swing.JTextArea jtextSecond;
  private javax.swing.JTextArea jtextSummary;
  // End of variables declaration//GEN-END:variables
}
