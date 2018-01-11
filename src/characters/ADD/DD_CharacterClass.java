package characters.ADD;

import java.util.ArrayList;
import java.util.HashMap;
import xml.XMLParser;
import org.jdom2.*;

public class DD_CharacterClass{
  
  private boolean epic;
  
  private String name ;
  private int lifePointDice;
  private int skillPoints;
  
  private int[] attackBonus;
  private int[] reflexBonus;
  private int[] fortitudeBonus;
  private int[] willBonus;
  private ArrayList<String> classSkill;
  private HashMap<Integer,ArrayList<String>> levelIntel;
  private String spellAttribute;
  private HashMap<Integer,ArrayList<Integer>> spellNumbers;
  
  private String alignmentCondition;
  
  /**
   * Default constructor
   */
  public DD_CharacterClass(){
    this.epic = false;
    this.name = "";
    this.lifePointDice = -1;
    this.skillPoints = -1;
    this.attackBonus = null;
    this.reflexBonus = null;
    this.fortitudeBonus = null;
    this.willBonus = null;
    this.classSkill = null;
    this.levelIntel = null;
    this.spellNumbers = null;
    this.alignmentCondition = null;
    this.spellAttribute = "";
  }
  
  /**
   * Constructor with name to load according to xml intel
   * @param className 
   */
  public DD_CharacterClass(String className){
    
    this.classSkill = new ArrayList<>();
    this.levelIntel = new HashMap<>();
    this.spellNumbers = new HashMap<>();
    
    XMLParser xml = new XMLParser();
    Element classe = xml.getDDClass(className);
    
    this.epic = classe.getAttributeValue("epic").equalsIgnoreCase("yes");
    // TODO: prerequis si besoin...
    this.name = classe.getAttributeValue("name");
    this.lifePointDice = Integer.parseInt(classe.getChildText("life_point_dice"));
    this.skillPoints = Integer.parseInt(classe.getChild("skill_points").getChildText("level_increase"));
    fillABB(classe.getChildText("attack_base_bonus"));
    fillSavingThrows(classe.getChildText("reflex_base_bonus"),classe.getChildText("fortitude_base_bonus"),classe.getChildText("will_base_bonus"));
    for(Element e: classe.getChild("class_skills").getChildren()){
      this.classSkill.add(e.getText());
    }
    int tmpLvl = -1;
    ArrayList<String> tmpArray = new ArrayList<>();
    for(Element e: classe.getChild("class_advantages").getChildren()){
      if (Integer.parseInt(e.getAttributeValue("level")) != tmpLvl){
        if(tmpLvl != -1){
          this.levelIntel.put(tmpLvl,tmpArray);
        }
        tmpLvl = Integer.parseInt(e.getAttributeValue("level"));
        tmpArray = new ArrayList<>();
      }
      tmpArray.add(e.getText());
    }
    
    this.alignmentCondition = classe.getChildText("alignment");
    
    if(classe.getChild("spells").getAttributeValue("allowed").equalsIgnoreCase("yes")){
      this.spellAttribute = classe.getChild("spells").getAttributeValue("attribute");
      for(Element p : classe.getChild("spells").getChildren()){
        ArrayList<Integer> tmpNumbers = new ArrayList<>();
        for(Element sp : p.getChildren()){
          tmpNumbers.add(Integer.parseInt(sp.getText()));
        }
        this.spellNumbers.put(Integer.parseInt(p.getAttributeValue("level")), tmpNumbers);
      }
    }
    
  }
  
  /***** Utility methods and function *****/
  /**
   * Return a list of prerequisites in order for a character to use the current class
   * @return 
   */
  public String getPrerequisites(){
    return null;
  }
  
  private void fillABB(String type){
    if(type.equalsIgnoreCase("high")){
      this.attackBonus = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    }
    else if(type.equalsIgnoreCase("mean")){
      this.attackBonus = new int[]{0,1,2,3,3,4,5,6,6,7,8,9,9,10,11,12,12,13,14,15};
    }
    else{
      this.attackBonus = new int[]{0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10};
    }
  }
  
  private void fillSavingThrows(String reflex,String fortitude,String will){
    for(int i = 0 ; i < 3 ; ++i){
      String tmp = (i==0)?reflex:((i==1)?fortitude:will);
      if(tmp.equalsIgnoreCase("high")){
        if(i==0)
          this.reflexBonus = new int[]{2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12};
        if(i==1)
          this.fortitudeBonus = new int[]{2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12};
        if(i==2)
          this.willBonus = new int[]{2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12};
      }
      else{
        if(i==0)
          this.reflexBonus = new int[]{0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6};
        if(i==1)
          this.fortitudeBonus = new int[]{0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6};
        if(i==2)
          this.willBonus = new int[]{0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6};
      }
    }
  }
  
  /***** Only getter, appointed intel *****/
  /**
   * 
   * @return 
   */
  public boolean isEpic(){
    return this.epic;
  }
  
  public String getName(){
    return this.name;
  }
  
  public int getLifePointDice(){
    return this.lifePointDice;
  }
  
  public int getAttackBaseBonus(int level){
    return this.attackBonus[level - 1];
  }
  
  public int getReflexBaseBonus(int level){
    return this.reflexBonus[level - 1];
  }
  
  public int getFortitudeBaseBonus(int level){
    return this.fortitudeBonus[level - 1];
  }
  public int getWillBaseBonus(int level){
    return this.willBonus[level - 1];
  }
  
  public ArrayList<String> getClassSkills(){
    return this.classSkill;
  }
  
  public int getSkillPoints(){
    return this.skillPoints;
  }
  
  public ArrayList<String> getLevelIntel(int level){
    return this.levelIntel.get(level);
  }
  
  public HashMap<Integer,ArrayList<String>> getLevelInte(){
    return this.levelIntel;
  }
  
  public ArrayList<Integer> getSpellNumberForLevel(int level){
    return this.spellNumbers.get(level);
  }
  
  /**
   * 
   * @return 
   */
  @Override
  public String toString(){
    String toReturn = "";
  
    toReturn += "-------------------------------------\n\r"
             +  this.epic + ";"
             +  this.name + "\n\r"
             +  this.lifePointDice + ";"
             +  this.skillPoints + "\n\r"
             +  this.attackBonus[0] + ";"
             +  this.attackBonus[19] + "\n\r"
             +  this.reflexBonus[0] + ";"
             +  this.reflexBonus[19] + "\n\r"
             +  this.fortitudeBonus[0] + ";"
             +  this.fortitudeBonus[19] + "\n\r"
             +  this.willBonus[0] + ";"
             +  this.willBonus[19] + "\n\r"
             +  this.classSkill.get(0) + ";"
             +  this.levelIntel.get(0) + ";"
             +  this.alignmentCondition + "\n\r"
             + "-------------------------------------";
    
    return toReturn;
  }

}
