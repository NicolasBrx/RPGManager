package npc;

import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class P13_Supervisor extends NonPlayerCharacter{
  
  /***** Attributes of a supervisor *****/
  private String office;              // office number of the supervisor
  private String secret;              // secret of the supervisor if he has one
  private boolean OHS;                // is the supervisor member of OHS?
  private ArrayList<String> patients; // patients monitored by the supervisor
  private ArrayList<String> allies;   
  private ArrayList<String> ennemies; 
  
  /***** Constructor methods and functions *****/
  /**
   * 
   */
  public P13_Supervisor(){
    super("unknown");
    this.office = "unknown";
    this.secret = "unknown";
    this.OHS = false;
    this.patients = new ArrayList<>();
    this.allies = new ArrayList<>();
    this.ennemies = new ArrayList<>();
  }
  
  /**
   * 
   * @param name
   * @param office
   * @param secret 
   * @param ohs
   */
  public P13_Supervisor(String name, String office, String secret,boolean ohs){
    super(name);
    this.office = office;
    this.secret = secret;
    this.OHS = ohs;
    this.patients = new ArrayList<>();
  }
  
  @Override
  public String toString(){
    String toReturn = super.getName();
    toReturn += " - " + office;
    toReturn += " - " + secret;
    toReturn += " - " + patients.get(0);
    toReturn += " - " + super.getNotes().get(0);
    toReturn += " - " + allies.get(0);
    toReturn += " - " + ennemies.get(0);
    return toReturn;
  }
  
  /***** Tool methods and functions *****/
  /**
   * 
   * @param patientName 
   */
  public void addPatient(String patientName){
    this.patients.add(patientName);
  }
  
  /**
   * 
   * @param note 
   */
  @Override
  public void addNote(String note){
    super.addNote(note);
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
  public String getOffice() {
    return office;
  }

  /**
   * 
   * @param office 
   */
  public void setOffice(String office) {
    this.office = office;
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
  public ArrayList<String> getPatients() {
    return patients;
  }

  /**
   * 
   * @param patients 
   */
  public void setPatients(ArrayList<String> patients) {
    this.patients = patients;
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
   */
  @Override
  public void clearNotes(){
    super.clearNotes();
  }

  /**
   * 
   * @return 
   */
  public boolean isOHS() {
    return OHS;
  }

  /**
   * 
   * @param OHS 
   */
  public void setOHS(boolean OHS) {
    this.OHS = OHS;
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
  
  
}//P13_Supervisor
