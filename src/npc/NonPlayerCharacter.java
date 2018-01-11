package npc;

import java.util.ArrayList;

/**
 * This class is here to be extended, no less. It allows the use of a 
 * NonPlayerCharacter object, that's all. :)
 * @author nicolas
 */
public class NonPlayerCharacter {
  
  private String name;
  private ArrayList<String> notes;
  
  
  /***** Contructor methods *****/
  /**
   * 
   */
  NonPlayerCharacter(){
    this.name = "John Doe";
    this.notes = new ArrayList<>();
  }
  
  /**
   * 
   * @param name 
   */
  NonPlayerCharacter(String name){
    this.name = name;
    this.notes = new ArrayList<>();
  }
  
  
  /***** Getter and setter methods and functions *****/
  /**
   * 
   * @return 
   */
  public String getName(){
    return this.name;
  }
  
  /**
   * 
   * @param name 
   */
  public void setName(String name){
    this.name = name;
  }

  public ArrayList<String> getNotes() {
    return notes;
  }

  public void setNotes(ArrayList<String> notes) {
    this.notes.clear();
    this.notes = notes;
  }

  public void addNote(String note){
    this.notes.add(note);
  }  
  
  public void clearNotes(){
    this.notes.clear();
  }
}//NonPlayerCharacter
