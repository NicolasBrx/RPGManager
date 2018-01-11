/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm_desktop.ADD;

import characters.ADD.DD_CharacterClass;
import characters.DD_Character;
import ihm_desktop.ADD.ADD_PCSubPanelDons;
import ihm_desktop.ADD.ADD_PCSubPanelGeneral;
import ihm_desktop.ADD.ADD_PCSubPanelSkills;
import ihm_desktop.ADD.ADD_PCSubPanelSpells;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class ADD_PCPanel extends javax.swing.JPanel {

  private int currentSubPanel;
  private DD_Character relatedCharacter;
  
  /**
   * Creates new form ADD_PCPanel
   */
  public ADD_PCPanel() {
    initComponents();
    
    setSize(258,470);
    setVisible(true);
  }
  
  public void setCharacter(DD_Character relatedOne){
    
    this.relatedCharacter = relatedOne;
    
    jlblPlayer.setText("Player: " + this.relatedCharacter.getPlayerName());
    jlblCharacter.setText("Character: " + this.relatedCharacter.getCharacterFirstname() 
                         + " " + this.relatedCharacter.getCharacterFamilyName()
                         + " (" + this.relatedCharacter.getCharacterRace()+ ")");
    jlblAge.setText("Age: " + this.relatedCharacter.getCharacterAge());
    jlblWeight.setText("Weight: " + this.relatedCharacter.getCharacterWeight());
    jlblSize.setText("Size: " + this.relatedCharacter.getCharacterSize() 
                    + " (" + this.relatedCharacter.getSizeType() + ")");
    jlblGender.setText("Gender: " + (this.relatedCharacter.isFemale() ? "woman" : "man"));
    
    /*
    String race, String sizeType, HashMap<DD_CharacterClass,Integer> classes, ArrayList<Integer> attributes,
                           String alignment, String deity, int xp, ArrayList<Integer> bonuses
    */
    
    this.currentSubPanel = 1;
    jbtnPrevious.setEnabled(false);
    loadPanel(this.currentSubPanel);
    
  }
  
  private void loadPanel(int id){
    switch (id){
      case 1:
        loadGeneralPanel();
        break;
      case 2:
        loadDonsPanel();
        break;
      case 3:
        loadSkillsPanel();
        break;
      case 4:
        loadSpellsPanel();
        break;
      default:
        System.out.println("Should not be reached.");
        break;
    }//switch id
  }
  
  private void loadGeneralPanel(){
    
    jPanelIntel.removeAll();
    jPanelIntel.validate();
    jPanelIntel.repaint();
    
    ADD_PCSubPanelGeneral panel =  new ADD_PCSubPanelGeneral();
    
    ArrayList<String> listClasses = new ArrayList<>();
    for(DD_CharacterClass cl : this.relatedCharacter.getClasses().keySet()){
      listClasses.add(cl.getName());
      listClasses.add(String.valueOf(this.relatedCharacter.getClasses().get(cl)));
    }
    panel.setLabels(this.relatedCharacter.getAttribute("Strength"), this.relatedCharacter.getStrengthMod(),
                    this.relatedCharacter.getAttribute("Dexterity"), this.relatedCharacter.getDexterityMod(),
                    this.relatedCharacter.getAttribute("Constitution"), this.relatedCharacter.getConstitutionMod(),
                    this.relatedCharacter.getAttribute("Intelligence"), this.relatedCharacter.getIntelligenceMod(),
                    this.relatedCharacter.getAttribute("Wisdom"), this.relatedCharacter.getWisdomMod(),
                    this.relatedCharacter.getAttribute("Charisma"), this.relatedCharacter.getCharismaMod(),
                    this.relatedCharacter.getArmorClass(),
                    this.relatedCharacter.getXpPoints(),this.relatedCharacter.getLifePoints(), this.relatedCharacter.getMaxLifePoints(),
                    this.relatedCharacter.getAttackBaseBonus(), this.relatedCharacter.getReflexSavingThrow(),this.relatedCharacter.getWillSavingThrow(),
                    this.relatedCharacter.getFortitudeSavingThrow(),this.relatedCharacter.getDeathSavingThrow(),this.relatedCharacter.getMagicResistance(),
                    listClasses, 
                    this.relatedCharacter.getAlignment()[0].substring(0,1).toUpperCase() + "-" + this.relatedCharacter.getAlignment()[1].substring(0,1).toUpperCase(),
                    this.relatedCharacter.getDeity());
     
    panel.validate();
    panel.repaint();
    jPanelIntel.add(panel);
    jPanelIntel.validate();
    jPanelIntel.repaint();
  }//loadGeneralPanel()
  
  private void loadDonsPanel(){
    jPanelIntel.removeAll();
    jPanelIntel.validate();
    jPanelIntel.repaint();
    ADD_PCSubPanelDons panel = new ADD_PCSubPanelDons();
    panel.loadDons(this.relatedCharacter.getTalents());
    panel.validate();
    panel.repaint();
    jPanelIntel.add(panel);
    jPanelIntel.validate();
    jPanelIntel.repaint();
  }// loadDonsPanel
  
  private void loadSkillsPanel(){
    jPanelIntel.removeAll();
    jPanelIntel.validate();
    jPanelIntel.repaint();
    // les skills du personnage, le score et l'attribut associés, une différenciation
    // entre les skills de classe et les autres
    ADD_PCSubPanelSkills panel = new ADD_PCSubPanelSkills();
    panel.loadSkills(this.relatedCharacter.getSkills());
    panel.validate();
    panel.repaint();
    jPanelIntel.add(panel);
    jPanelIntel.validate();
    jPanelIntel.repaint();
  }//loadSkillsPanel
  
  private void loadSpellsPanel(){
    jPanelIntel.removeAll();
    jPanelIntel.validate();
    jPanelIntel.repaint();
    if(this.relatedCharacter.getKnownSpells().size() != 0){
      ADD_PCSubPanelSpells panel = new ADD_PCSubPanelSpells();
      panel.setSpells(this.relatedCharacter.getKnownSpells());
      panel.validate();
      panel.repaint();
      jPanelIntel.add(panel);
      jPanelIntel.validate();
      jPanelIntel.repaint();
    }
    // les sorts auxquels peut prétendre le personnage le cas échéant
  }//loadSpellsPanel

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jlblPlayer = new javax.swing.JLabel();
    jlblCharacter = new javax.swing.JLabel();
    jlblAge = new javax.swing.JLabel();
    jlblWeight = new javax.swing.JLabel();
    jlblSize = new javax.swing.JLabel();
    jlblGender = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jbtnPrevious = new javax.swing.JButton();
    jbtnNext = new javax.swing.JButton();
    jPanelIntel = new javax.swing.JPanel();

    setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    setMaximumSize(new java.awt.Dimension(258, 470));
    setMinimumSize(new java.awt.Dimension(258, 470));
    setPreferredSize(new java.awt.Dimension(258, 470));

    jlblPlayer.setText("Player:");

    jlblCharacter.setText("Character:");

    jlblAge.setText("Age:");

    jlblWeight.setText("Weight:");

    jlblSize.setText("Size:");

    jlblGender.setText("Gender:");

    jbtnPrevious.setText("<<");
    jbtnPrevious.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnPreviousActionPerformed(evt);
      }
    });

    jbtnNext.setText(">>");
    jbtnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnNextActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanelIntelLayout = new javax.swing.GroupLayout(jPanelIntel);
    jPanelIntel.setLayout(jPanelIntelLayout);
    jPanelIntelLayout.setHorizontalGroup(
      jPanelIntelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanelIntelLayout.setVerticalGroup(
      jPanelIntelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 284, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jSeparator1)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jlblPlayer)
              .addComponent(jlblAge)
              .addComponent(jlblCharacter)
              .addComponent(jlblWeight)
              .addComponent(jlblSize)
              .addComponent(jlblGender))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addGap(52, 52, 52)
        .addComponent(jbtnPrevious)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
        .addComponent(jbtnNext)
        .addGap(41, 41, 41))
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelIntel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jlblPlayer)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jlblCharacter)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jlblAge)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jlblWeight)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jlblSize)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jlblGender)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanelIntel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jbtnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jbtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jbtnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPreviousActionPerformed
    if(this.currentSubPanel > 1){
      this.currentSubPanel--;
      if(this.currentSubPanel == 1){
        jbtnPrevious.setEnabled(false);
      }
      else if((this.relatedCharacter.getKnownSpells().size() != 0 && this.currentSubPanel == 3)
           || (this.relatedCharacter.getKnownSpells().size() == 0 && this.currentSubPanel == 2))
           {
        jbtnNext.setEnabled(true);
      }
      loadPanel(this.currentSubPanel);
    }
  }//GEN-LAST:event_jbtnPreviousActionPerformed

  private void jbtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextActionPerformed
    if(this.currentSubPanel < 4){
      this.currentSubPanel++;
      if((this.relatedCharacter.getKnownSpells().size() != 0 && this.currentSubPanel == 4)
      || (this.relatedCharacter.getKnownSpells().size() == 0 && this.currentSubPanel == 3))
      {
        jbtnNext.setEnabled(false);
      }
      else if(this.currentSubPanel == 2){
        jbtnPrevious.setEnabled(true);
      }
      loadPanel(this.currentSubPanel);
    }
  }//GEN-LAST:event_jbtnNextActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanelIntel;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JButton jbtnNext;
  private javax.swing.JButton jbtnPrevious;
  private javax.swing.JLabel jlblAge;
  private javax.swing.JLabel jlblCharacter;
  private javax.swing.JLabel jlblGender;
  private javax.swing.JLabel jlblPlayer;
  private javax.swing.JLabel jlblSize;
  private javax.swing.JLabel jlblWeight;
  // End of variables declaration//GEN-END:variables
}
