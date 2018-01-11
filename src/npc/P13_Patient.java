package npc;

import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class P13_Patient extends NonPlayerCharacter{
  
  /***** Attributes of a patient *****/
  private String room;             // room number of the patient
  private String secret;           // secret of the patient if he has one
  private String supervisor;       // name of the supervisor of the patient
  private ArrayList<String> allies;
  private ArrayList<String> ennemies;
  
  /***** Constructor methods and functions *****/
  /**
   * 
   */
  public P13_Patient(){
    super("unknown");
    this.room = "unknown";
    this.secret = "unknown";
    this.supervisor = "unknown";
  }
  
  /**
   * 
   * @param name
   * @param room
   * @param secret
   * @param supervisor 
   */
  public P13_Patient(String name, String room, String secret, String supervisor){
    super(name);
    this.room = room;
    this.secret = secret;
    this.supervisor = supervisor;
  }
  
  /***** Tool methods and functions *****/
  /**
   * 
   * @param note 
   */
  @Override
  public void addNote(String note){
    super.addNote(note);
  }
  
  @Override
  public void clearNotes(){
    super.clearNotes();
  }
  
  /**
   * 
   * @return 
   */
  @Override
  public String toString(){
    String toReturn = super.getName();
    toReturn += " - " + room;
    toReturn += " - " + secret;
    toReturn += " - " + supervisor;
    toReturn += " - " + allies.get(0);
    toReturn += " - " + ennemies.get(0);
    return toReturn;
  }

  /***** Getter and setter methods and functions *****/
  /**
   * 
   * @return 
   */
  @Override
  public String getName() {
    return super.getName();
  }

  /**
   * 
   * @param name 
   */
  @Override
  public void setName(String name) {
    super.setName(name);
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

  /**
   * 
   * @return 
   */
  public String getSecret() {
    return secret;
  }

  /**
   * 
   * @param secret 
   */
  public void setSecret(String secret) {
    this.secret = secret;
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
  @Override
  public ArrayList<String> getNotes() {
    return super.getNotes();
  }

  /**
   * 
   * @param notes 
   */
  @Override
  public void setNotes(ArrayList<String> notes) {
    super.setNotes(notes);
  }

  /**
   * 
   * @return 
   */
  public ArrayList<String> getAllies() {
    return allies;
  }

  /**
   * 
   * @param allies 
   */
  public void setAllies(ArrayList<String> allies) {
    this.allies = allies;
  }

  /**
   * 
   * @return 
   */
  public ArrayList<String> getEnnemies() {
    return ennemies;
  }

  /**
   * 
   * @param ennemies 
   */
  public void setEnnemies(ArrayList<String> ennemies) {
    this.ennemies = ennemies;
  }
  
  
}//P13_Patient
