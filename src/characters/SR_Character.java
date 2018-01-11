package characters;

import characters.SR.SR_Augmentation;
import characters.SR.SR_Power;
import characters.SR.SR_Skill;
import characters.SR.SR_Spell;
import java.util.ArrayList;
import java.util.HashMap;
import rpgTools.GameType;

/**
 *
 * @author Nicolas Brax
 */
public class SR_Character extends GenericCharacter{
    
  private String characterMetatype;
  
  private int totalKarma;
  private int unspentKarma;
  private int streetCred;
  private int notoriety;
  
  private HashMap<String,ArrayList<String>> identities; // id, {nuyens, lifestyle, licences}
  private HashMap<String,Double> contacts;              // name, (double)loyalty,influence
  
  private int physicalTrack;
  private int stunTrack;
  
  private ArrayList<SR_Skill> skills; 
  private ArrayList<String> qualities;
  private ArrayList<SR_Augmentation> augmentations;
  private ArrayList<SR_Power> powers;
  private ArrayList<SR_Spell> spells;
  
  /***** Computed and generated attributes and intel on the character *****/
  private double essence;
  private ArrayList<String> metatypeAdvantages;
  private HashMap<String,Integer> initiaves;            // init, matrix, astral, passes
  private boolean hasAugmentation;
  private boolean hasPowers;
  private boolean hasSpells;
  private int physicalMalus;
  private int stunMalus;
  
  // body, agility, reaction, strength, charisma, intuition, logic, willpower, edge, magic|resonance
  
  /***** Constructor methods and functions *****/
  /**
   * 
   */
  public SR_Character(){
    super();
    this.characterMetatype = "";
    this.totalKarma = -1;
    this.unspentKarma = -1;
    this.streetCred = -1;
    this.notoriety = -1;
    this.identities = null;
    this.contacts = null;
    this.physicalTrack = -1;
    this.stunTrack = -1;
    this.skills = null;
    this.qualities = null;
    this.augmentations = null;
    this.powers = null;
    this.spells = null;
  }
  
  /**
   * 
   * @param player
   * @param firstname
   * @param familyName
   * @param female
   * @param age
   * @param weight
   * @param size
   * @param metatype
   * @param totalKarma
   * @param unspentKarma
   * @param streetCred
   * @param notoriety
   * @param attributes
   * @param identities
   * @param contacts
   * @param physical
   * @param stun
   * @param augmentations
   * @param spells
   * @param powers
   * @param qualities
   * @param skills 
   */
  public SR_Character(String player,String firstname,String familyName,boolean female,int age,double weight,double size,
                      String metatype,int totalKarma,int unspentKarma,int streetCred,int notoriety,
                      ArrayList<String> attributes,ArrayList<String> identities, ArrayList<String> contacts,
                      int physical,int stun,
                      ArrayList<String> augmentations,ArrayList<String> spells,ArrayList<String> powers,
                      ArrayList<String> qualities,ArrayList<String> skills
  ){
    super(new GameType("Shadowrun"), player,firstname,familyName,female,age,weight,size);
    this.characterMetatype = metatype;
    this.totalKarma = totalKarma;
    this.unspentKarma = unspentKarma;
    this.streetCred = streetCred;
    this.notoriety = notoriety;
    this.physicalTrack = physical;
    this.stunTrack = stun;
    
    this.identities = null;    //
    this.contacts = null;      //
    this.skills = null;        //
    this.qualities = null;     //
    this.augmentations = null; //
    this.powers = null;        //
    this.spells = null;        //
    
    autoCompute();
  }
  
  /***** Specific methods and functions *****/
  private void autoCompute(){
    // compute the automatically generated attributes and parameters
  }

  /***** getter and setter methods and functions *****/
  /**
   * 
   * @return 
   */
  public String getCharacterMetatype() {
    return characterMetatype;
  }

  public void setCharacterMetatype(String characterMetatype) {
    this.characterMetatype = characterMetatype;
  }

  public ArrayList<String> getMetatypeAdvantages() {
    return metatypeAdvantages;
  }

  public void setMetatypeAdvantages(ArrayList<String> metatypeAdvantages) {
    this.metatypeAdvantages = metatypeAdvantages;
  }

  public int getTotalKarma() {
    return totalKarma;
  }

  public void setTotalKarma(int totalKarma) {
    this.totalKarma = totalKarma;
  }

  public int getUnspentKarma() {
    return unspentKarma;
  }

  public void setUnspentKarma(int unspentKarma) {
    this.unspentKarma = unspentKarma;
  }

  public int getStreetCred() {
    return streetCred;
  }

  public void setStreetCred(int streetCred) {
    this.streetCred = streetCred;
  }

  public int getNotoriety() {
    return notoriety;
  }

  public void setNotoriety(int notoriety) {
    this.notoriety = notoriety;
  }

  public double getEssence() {
    return essence;
  }

  public void setEssence(double essence) {
    this.essence = essence;
  }

  public HashMap<String, Integer> getInitiaves() {
    return initiaves;
  }

  public void setInitiaves(HashMap<String, Integer> initiaves) {
    this.initiaves = initiaves;
  }

  public HashMap<String, ArrayList<String>> getIdentities() {
    return identities;
  }

  public void setIdentities(HashMap<String, ArrayList<String>> identities) {
    this.identities = identities;
  }

  public HashMap<String, Double> getContacts() {
    return contacts;
  }

  public void setContacts(HashMap<String, Double> contacts) {
    this.contacts = contacts;
  }

  public int getPhysicalTrack() {
    return physicalTrack;
  }

  public void setPhysicalTrack(int physicalTrack) {
    this.physicalTrack = physicalTrack;
  }

  public int getPhysicalMalus() {
    return physicalMalus;
  }

  public void setPhysicalMalus(int physicalMalus) {
    this.physicalMalus = physicalMalus;
  }

  public int getStunTrack() {
    return stunTrack;
  }

  public void setStunTrack(int stunTrack) {
    this.stunTrack = stunTrack;
  }

  public int getStunMalus() {
    return stunMalus;
  }

  public void setStunMalus(int stunMalus) {
    this.stunMalus = stunMalus;
  }

  public boolean isHasAugmentation() {
    return hasAugmentation;
  }

  public void setHasAugmentation(boolean hasAugmentation) {
    this.hasAugmentation = hasAugmentation;
  }

  public boolean isHasPowers() {
    return hasPowers;
  }

  public void setHasPowers(boolean hasPowers) {
    this.hasPowers = hasPowers;
  }

  public boolean isHasSpells() {
    return hasSpells;
  }

  public void setHasSpells(boolean hasSpells) {
    this.hasSpells = hasSpells;
  }

  public ArrayList<SR_Skill> getSkills() {
    return skills;
  }

  public void setSkills(ArrayList<SR_Skill> skills) {
    this.skills = skills;
  }

  public ArrayList<String> getQualities() {
    return qualities;
  }

  public void setQualities(ArrayList<String> qualities) {
    this.qualities = qualities;
  }

  public ArrayList<SR_Augmentation> getAugmentations() {
    return augmentations;
  }

  public void setAugmentations(ArrayList<SR_Augmentation> augmentations) {
    this.augmentations = augmentations;
  }

  public ArrayList<SR_Power> getPowers() {
    return powers;
  }

  public void setPowers(ArrayList<SR_Power> powers) {
    this.powers = powers;
  }

  public ArrayList<SR_Spell> getSpells() {
    return spells;
  }

  public void setSpells(ArrayList<SR_Spell> spells) {
    this.spells = spells;
  }
  
  
  
}
