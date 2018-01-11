package characters;

import equipment.Backpack;
import equipment.Worn;
import java.util.ArrayList;
import java.util.HashMap;
import rpgTools.GameType;

/**
 *
 * @author nicolas
 */
abstract public class GenericCharacter {
  
  
  private GameType gameType;                  // which game is the character for

  /***** Generic attributes *****/
  private String playerName;                  // name of the player handling the character
  private String characterFirstname;          // firstname of the character
  private String characterFamilyName;         // family name of the character
  private int characterAge;                   // age of the character
  private double characterWeight;             // weigth of the character
  private double characterSize;               // size of the character
  private boolean female;                     // gender of the character
  private HashMap<String,Integer> attributes; // attributes of the character, depend on the game
  
  /***** Character Equipment *****/
  private Backpack backpack;                  // the backpack of the character
  private Worn wornEquipment;                 // what is worn by the character
  
  /***** Notes and intel *****/
  private ArrayList<String> notes;            // any notes or intel on the character
                                              // and/or the player

  /***** Constructor methods and functions *****/
  /**
   * 
   */
  public GenericCharacter(){
    this.gameType = new GameType();
    this.playerName = "none";
    this.characterFirstname = "none";
    this.characterFamilyName = "none";
    this.female = true;
    this.characterAge = -1;
    this.characterWeight = -1.0;
    this.characterSize = -1.0;
    this.attributes = null;
  }
  
  /**
   * 
   * @param gameType
   * @param player
   * @param firstname
   * @param familyName
   * @param female
   * @param age
   * @param weight
   * @param size 
   */
  public GenericCharacter(GameType gameType, String player, String firstname, String familyName, 
                          boolean female,int age, double weight, double size
                         ){
    this.gameType = new GameType(gameType.getGame());
    this.playerName = player;
    this.characterFirstname = firstname;
    this.characterFamilyName = familyName;
    this.female = female;
    this.characterAge = age;
    this.characterWeight = weight;
    this.characterSize = size;
    this.attributes = new HashMap<>();
  }

  /***** Specific methods and functions *****/
  /**
   * 
   * @param attributeName
   * @param attributeScore 
   */
  public void addAttribute(String attributeName, int attributeScore){
    if(this.attributes.containsKey(attributeName)){
      this.attributes.replace(attributeName, attributeScore);
    }
    else{
      this.attributes.put(attributeName, attributeScore); // note that put make a replacement if
                                                          // the key is already in the map
    }
  }//addAttribute
  
  /**
   * 
   * @param attributeName
   * @return 
   */
  public int getAttribute(String attributeName){
    int toReturn = -1;
    if(this.attributes.containsKey(attributeName)){
      toReturn = this.attributes.get(attributeName);
    }
    return toReturn;
  }
  
  /***** Getter and setter methods and functions *****/
  /**
   * 
   * @return the player name
   */
  public String getPlayerName(){
    return playerName;
  }
  
  /**
   * 
   * @param playerName 
   */
  public void setPlayerName(String playerName){
    this.playerName = playerName;
  }
  
  /**
   * 
   * @return the character first name
   */
  public String getCharacterFirstname() {
    return characterFirstname;
  }

  /**
   * 
   * @param characterFirstname 
   */
  public void setCharacterFirstname(String characterFirstname) {
    this.characterFirstname = characterFirstname;
  }

  /**
   * 
   * @return the character family name
   */
  public String getCharacterFamilyName() {
    return characterFamilyName;
  }

  /**
   * 
   * @param characterFamilyName 
   */
  public void setCharacterFamilyName(String characterFamilyName) {
    this.characterFamilyName = characterFamilyName;
  }

  /**
   * 
   * @return the character age
   */
  public int getCharacterAge() {
    return characterAge;
  }

  /**
   * 
   * @param characterAge 
   */
  public void setCharacterAge(int characterAge) {
    this.characterAge = characterAge;
  }

  /**
   * 
   * @return the character weight
   */
  public double getCharacterWeight() {
    return characterWeight;
  }

  /**
   * 
   * @param characterWeight 
   */
  public void setCharacterWeight(double characterWeight) {
    this.characterWeight = characterWeight;
  }

  /**
   * 
   * @return the character size
   */
  public double getCharacterSize() {
    return characterSize;
  }

  /**
   * 
   * @param characterSize 
   */
  public void setCharacterSize(double characterSize) {
    this.characterSize = characterSize;
  }

  /**
   * 
   * @return 
   */
  public HashMap<String, Integer> getAttributes() {
    return attributes;
  }

  /**
   * 
   * @param attributes 
   */
  public void setAttributes(HashMap<String, Integer> attributes) {
    this.attributes = attributes;
  }

  /**
   * 
   * @return 
   */
  public ArrayList<String> getNotes() {
    return notes;
  }

  /**
   * 
   * @param notes 
   */
  public void setNotes(ArrayList<String> notes) {
    this.notes = notes;
  }
  
  /**
   * 
   * @return 
   */
  public boolean isFemale() {
    return female;
  }

  /**
   * 
   * @param female 
   */
  public void setFemale(boolean female) {
    this.female = female;
  }

  /**
   * 
   * @return 
   */
  public String getGameType() {
    return gameType.getGame();
  }

  /**
   * 
   * @param gameType 
   */
  public void setGameType(String gameType) {
    this.gameType.setGame(gameType);
  }
  
  
  
} // class character
