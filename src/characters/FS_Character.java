package characters;

import java.util.ArrayList;
import java.util.HashMap;
import rpgTools.GameType;

/**
 *
 * @author nicolas
 */
public class FS_Character extends GenericCharacter{
  private String lifeStyle;
  private int junction;
  private HashMap<String,Integer> skills;
  private ArrayList<String> shticks;
  
  public FS_Character(){
    super();
    this.lifeStyle = "";
  }
  
  public FS_Character(GameType gameType, String player, String firsname, String familyName,
          boolean female, int age, double weight, double size,
          String lifeStyle, int junction,
          ArrayList<String> attributes, ArrayList<String> skills, ArrayList<String> shticks
  ){
    
  }
  
  
}
