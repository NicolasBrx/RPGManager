package characters;

import java.util.HashMap;
import rpgTools.GameType;

/**
 *
 * @author nicolas
 */
public class P13_Character extends GenericCharacter{
  
  // the attribute of a patient 13 character are lucidity, vitality and cold blood
  
  /***** Character attributes *****/   
  private String surname;                     // the surname given to the character
  private int seniority;                      // senriority of the character in the asylum
  private HashMap<String,Integer> lineaments; // the set of positive and negative traits of 
                                              // the character
  
  /***** Character relationship in the  clinic *****/
  private String supervisor;                  // the doctor in charge of the character
  private String room;                        // the room assigned to the character
   
  /***** Constructor methods and functions *****/
  /**
   * 
   */
  public P13_Character(){
    super(new GameType("Patient 13"),"Jane Doe","unknown","unknown",true,-1,-1.0,-1.0);
    this.surname = "none";
    this.seniority = -1;
    this.lineaments = null;
    this.supervisor = "unknown";
    this.room = "unknown";
  }
  
  /**
   * 
   * @param player
   * @param firstname
   * @param familyname
   * @param surname
   * @param age
   * @param weight
   * @param size
   * @param female
   * @param lucidity
   * @param vitality
   * @param coldBlood
   * @param seniority
   * @param lineaments
   * @param notes 
   * @param supervisor
   * @param room
   */
  public P13_Character(String player,String firstname, String familyname,
                       String surname, int age, double weight, double size, boolean female,
                       int lucidity, int vitality, int coldBlood, int seniority,
                       String lineaments, String notes, String supervisor, String room
                      ){
    
    super(new GameType("Patient 13"),player,firstname,familyname,female,age,weight,size);
    this.surname = surname;
    this.seniority = seniority;
    this.supervisor = supervisor;
    this.room = room;
    super.addAttribute("lucidity",lucidity);
    super.addAttribute("vitality",vitality);
    super.addAttribute("coldblood",coldBlood);
    
    // line split for lineaments -- values and note -- note to put in arraylist or hashmap
    
  }

  
  
  /***** Specific methods and functions *****/
  /**
   * 
   * @return 
   */
  @Override
  public String toString(){
    String toReturn;
    
    toReturn = "";
    toReturn += "\r\n";
    
    return toReturn;
  }
  
  /**
   * 
   * @param amount 
   */
  public void inflictWound(int amount){
    super.addAttribute("vitality",super.getAttribute("vitality") - amount);
  }
  
  /**
   * 
   * @param amount 
   */
  public void inflictPsychic(int amount){
    super.addAttribute("lucidity",super.getAttribute("lucidity") - amount);
  }
  
  /**
   * 
   * @param amount 
   */
  public void inflictCalm(int amount){
    super.addAttribute("coldblood",super.getAttribute("coldblood") - amount);
  }
  
  /**
   * 
   * @param amount 
   */
  public void healWound(int amount){
    super.addAttribute("vitality",super.getAttribute("vitality") + amount);
  }
  
  /**
   * 
   * @param amount 
   */
  public void healPsychic(int amount){
    super.addAttribute("lucidity",super.getAttribute("lucidity") + amount);
  }
  
  /**
   * 
   * @param amount 
   */
  public void healCalm(int amount){
    super.addAttribute("coldblood",super.getAttribute("coldblood") + amount);
  }
  
  /***** Getter and setter methods and functions *****/
  /**
   * 
   * @return 
   */
  public String getSurname() {
    return surname;
  }

  /**
   * 
   * @param surname 
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * 
   * @return 
   */
  public int getSeniority() {
    return seniority;
  }

  /**
   * 
   * @param seniority 
   */
  public void setSeniority(int seniority) {
    this.seniority = seniority;
  }

  /**
   * 
   * @return 
   */
  public HashMap<String, Integer> getLineaments() {
    return lineaments;
  }

  /**
   * 
   * @param lineaments 
   */
  public void setLineaments(HashMap<String, Integer> lineaments) {
    this.lineaments = lineaments;
  }

  /**
   * 
   * @return 
   */
  public String getSupervisor() {
    return supervisor;
  }

  /**
   * 
   * @param supervisor 
   */
  public void setSupervisor(String supervisor) {
    this.supervisor = supervisor;
  }

  /**
   * 
   * @return 
   */
  public String getRoom() {
    return room;
  }

  /**
   * 
   * @param room 
   */
  public void setRoom(String room) {
    this.room = room;
  }
}//P13_Character
