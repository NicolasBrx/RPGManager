package characters;

import characters.ADD.DD_CharacterClass;
import characters.ADD.DD_Spell;
import java.util.ArrayList;
import java.util.HashMap;
import rpgTools.GameType;
import xml.XMLParser;

//TODO: gerer le multiclassage en ce qui concerne les points de vie et le niveau global, fonctionadjustValue

/**
 *
 * @author Nicolas Brax
 */
public class DD_Character extends GenericCharacter{

  /***** Character attributes *****/  //+1 every multiple of 4 level
  private int strengthMod;
  private int dexterityMod;
  private int constitutionMod;
  private int intelligenceMod;
  private int wisdomMod;
  private int charismaMod;
  
  /***** Character classes (one or two plus epic classes if available) *****/
  private HashMap<DD_CharacterClass,Integer> classes;
  
  /***** Race related attributes other than attribute modification *****/
  private String characterRace;
  private String sizeType;
  private ArrayList<String> advantages;
  private String preferredClass;
  
  /***** Other generic attributes and particularities *****/
  private String[] alignment;
  private String deity;
  private int xpPoints;
  private int globalLevel;
  private int lifePoints;
  private int armorClass;
  
  private HashMap<String,Integer> skills;
  private HashMap<String,Integer> talents;       // generic: one new every multiple of 3 level
  private HashMap<String,DD_Spell> knownSpells;
    
  /***** Computed derivated attributes *****/
  private int attackBaseBonus;
  private int willSavingThrow;
  private int fortitudeSavingThrow;
  private int reflexSavingThrow;
  private int deathSavingThrow;
  private int baseArmorClass;
  private int maxLifePoints;
  private int magicResistance;

  /***** Constructor methods and functions *****/
  public DD_Character(){
    super();
    this.strengthMod = 0;
    this.dexterityMod = 0;
    this.constitutionMod = 0;
    this.intelligenceMod = 0;
    this.wisdomMod = 0;
    this.charismaMod = 0;
    this.classes = null;
    this.characterRace = null;
    this.sizeType = null;
    this.advantages = null;
    this.preferredClass = null;
    this.alignment = null;
    this.deity = null;
    this.xpPoints = -1;
    this.globalLevel = -1;
    this.lifePoints = -1;
    this.armorClass = -1;
    this.skills = null;
    this.talents = null;
    this.attackBaseBonus = -1;
    this.willSavingThrow = -1;
    this.fortitudeSavingThrow = -1;
    this.reflexSavingThrow = -1;
    this.deathSavingThrow = -1;
    this.baseArmorClass = -1;
    this.maxLifePoints = -1;
    this.magicResistance = -1;
  }
  
  public DD_Character(String player,String firstname,String familyName,boolean female,int age,double weight,double size,
                      int str,int dex,int con,int intel,int wis,int cha,
                      String charRace,String[] alignment,String deity,int xp,int glvl,int currentLife,
                      ArrayList<String> charClasses,ArrayList<String> skills,ArrayList<String> talents,
                      ArrayList<String> knownSpells
  ){
    super(new GameType("AD&D"), player,firstname,familyName,female,age,weight,size);
    
    this.classes = new HashMap<>();
    this.skills = new HashMap<>();
    this.advantages = new ArrayList<>();
    this.talents = new HashMap<>();
    this.knownSpells = new HashMap<>();
    
    XMLParser xml = new XMLParser();
    HashMap<String,ArrayList<String>> tmpRace = xml.getDDRace(charRace);
    this.characterRace = charRace;
    for(String key : tmpRace.keySet()){
      switch(key){
        case "strength":
          str += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "dexterity":
          dex += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "constitution":
          con += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "intelligence":
          intel += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "wisdom":
          wis += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "charisma":
          cha += Integer.parseInt(tmpRace.get(key).get(0));
          break;
        case "size":
          this.sizeType = tmpRace.get(key).get(0);
          break;
        case "advantages":
          this.advantages = tmpRace.get(key);
          break;
        case "languages":
          for(String s : tmpRace.get(key)){
            this.skills.put(s,99);
          }
          break;
        case "preferred":
          this.preferredClass = tmpRace.get(key).get(0);
          break;
        default:
          System.err.println("Error while reading DD race loader..." + key);
          break;
      }
    }
    
    super.addAttribute("Strength", str);
    super.addAttribute("Dexterity", dex);
    super.addAttribute("Constitution", con);
    super.addAttribute("Intelligence", intel);
    super.addAttribute("Wisdom", wis);
    super.addAttribute("Charisma", cha);
    this.strengthMod = computeMod(str);
    this.dexterityMod = computeMod(dex);
    this.constitutionMod = computeMod(con);
    this.intelligenceMod = computeMod(intel);
    this.wisdomMod = computeMod(wis);
    this.charismaMod = computeMod(cha);
    
    this.baseArmorClass = 10;
    this.alignment = alignment;
    this.deity = deity;
    this.xpPoints = xp;
    this.globalLevel = glvl;
    this.lifePoints = currentLife;

    for(int i = 0; i < charClasses.size() ; i += 2){
      this.classes.put(new DD_CharacterClass(charClasses.get(i)), Integer.parseInt(charClasses.get(i+1)));
    }
    
    for(int i = 0; i < skills.size() ; i += 2){
      this.skills.put(skills.get(i), Integer.parseInt(skills.get(i + 1)));
    }
    
    for(String s : talents){
      if(this.talents.containsKey(s)){
        this.talents.put(s, this.talents.get(s) + 1);
      }
      else{
        this.talents.put(s, 1);
      }
    }
    
    HashMap<String,DD_Spell> tmp = xml.loadDDSpells();
    for(String ks : knownSpells){
      this.knownSpells.put(ks, tmp.get(ks));
    }
    
    for(DD_CharacterClass cl : classes.keySet()){
      for(int i = 0 ; i <= classes.get(cl) ; ++i){
        if(cl.getLevelIntel(classes.get(cl)) != null){
          for(String s : cl.getLevelIntel(i)){
            if(this.talents.containsKey(s)){
              this.talents.put(s, this.talents.get(s) + 1);
            }
            else{
              this.talents.put(s, 1);
            }
          } // for levelIntel
        } // if != null
      } //for
    }
    
    adjustValues();
  }
  
  /***** Specific methods and functions *****/
  /**
   * 
   * @param attributeValue
   * @return 
   */
  public int computeMod(int attributeValue){
    int toReturn;
    switch(attributeValue){
      case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:
        toReturn = (int)Math.ceil((attributeValue - 10) / 2);
        break;
      default:
        toReturn = (int)Math.floor((attributeValue - 10) / 2);
        break;
    }// switch attributeValue
    return toReturn;
  }
  
  /**
   * TODO:
   * Adjust the values of internal parameters according to all parameters:
   * armor class, saving throws, ...
   * Very useful for multiclassing... xD
   */
  private void adjustValues(){
    for(DD_CharacterClass cl : this.classes.keySet()){
      this.maxLifePoints += (cl.getLifePointDice() * this.classes.get(cl))
                          + (getConstitutionMod() * this.classes.get(cl));
      this.attackBaseBonus += cl.getAttackBaseBonus(this.classes.get(cl));
      this.reflexSavingThrow += cl.getReflexBaseBonus(this.classes.get(cl));
      this.fortitudeSavingThrow += cl.getFortitudeBaseBonus(this.classes.get(cl));
      this.willSavingThrow += cl.getWillBaseBonus(this.classes.get(cl));
    }
    
    // ces valeurs, je ne sais pas comment on les calcule...
    //magicResistance
    //armorClass
    //deathSavingThrow
  }
  
  /***** getter and setter methods and functions *****/
  public int getStrengthMod() {
    return strengthMod;
  }

  public void setStrengthMod(int strengthMod) {
    this.strengthMod = strengthMod;
  }

  public int getDexterityMod() {
    return dexterityMod;
  }

  public void setDexterityMod(int dexterityMod) {
    this.dexterityMod = dexterityMod;
  }

  public int getConstitutionMod() {
    return constitutionMod;
  }

  public void setConstitutionMod(int constitutionMod) {
    this.constitutionMod = constitutionMod;
  }

  public int getIntelligenceMod() {
    return intelligenceMod;
  }

  public void setIntelligenceMod(int intelligenceMod) {
    this.intelligenceMod = intelligenceMod;
  }

  public int getWisdomMod() {
    return wisdomMod;
  }

  public void setWisdomMod(int wisdomMod) {
    this.wisdomMod = wisdomMod;
  }

  public int getCharismaMod() {
    return charismaMod;
  }

  public void setCharismaMod(int charismaMod) {
    this.charismaMod = charismaMod;
  }

  public HashMap<DD_CharacterClass,Integer> getClasses() {
    return classes;
  }

  public void setClasses(HashMap<DD_CharacterClass,Integer> classes) {
    this.classes = classes;
  }

  public String getCharacterRace() {
    return characterRace;
  }

  public void setCharacterRace(String characterRace) {
    this.characterRace = characterRace;
  }

  public String getSizeType() {
    return sizeType;
  }

  public void setSizeType(String sizeType) {
    this.sizeType = sizeType;
  }

  public ArrayList<String> getAdvantages() {
    return advantages;
  }

  public void setAdvantages(ArrayList<String> advantages) {
    this.advantages = advantages;
  }

  public String[] getAlignment() {
    return alignment;
  }

  public void setAlignment(String[] alignment) {
    this.alignment = alignment;
  }

  public String getDeity() {
    return deity;
  }

  public void setDeity(String deity) {
    this.deity = deity;
  }

  public int getXpPoints() {
    return xpPoints;
  }

  public void setXpPoints(int xpPoints) {
    this.xpPoints = xpPoints;
  }

  public int getLevel() {
    return globalLevel;
  }

  public void setLevel(int level) {
    this.globalLevel = level;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public void setLifePoints(int lifePoints) {
    this.lifePoints = lifePoints;
  }

  public int getArmorClass() {
    return armorClass;
  }

  public void setArmorClass(int armorClass) {
    this.armorClass = armorClass;
  }

  public HashMap<String, Integer> getSkills() {
    return skills;
  }

  public void setSkills(HashMap<String, Integer> skills) {
    this.skills = skills;
  }

  public HashMap<String,Integer> getTalents() {
    return talents;
  }

  public void setTalents(HashMap<String,Integer> talents) {
    this.talents = talents;
  }

  public int getWillSavingThrow() {
    return willSavingThrow;
  }

  public void setWillSavingThrow(int willSavingThrow) {
    this.willSavingThrow = willSavingThrow;
  }

  public int getFortitudeSavingThrow() {
    return fortitudeSavingThrow;
  }

  public void setFortitudeSavingThrow(int viguorSavingThrow) {
    this.fortitudeSavingThrow = viguorSavingThrow;
  }

  public int getDeathSavingThrow() {
    return deathSavingThrow;
  }

  public void setDeathSavingThrow(int deathSavingThrow) {
    this.deathSavingThrow = deathSavingThrow;
  }

  public int getBaseArmorClass() {
    return baseArmorClass;
  }

  public void setBaseArmorClass(int baseArmorClass) {
    this.baseArmorClass = baseArmorClass;
  }

  public int getMaxLifePoints() {
    return maxLifePoints;
  }

  public void setMaxLifePoints(int maxLifePoints) {
    this.maxLifePoints = maxLifePoints;
  }

  public int getMagicResistance() {
    return magicResistance;
  }

  public void setMagicResistance(int magicResistance) {
    this.magicResistance = magicResistance;
  }

  public String getPreferredClass() {
    return preferredClass;
  }

  public void setPreferredClass(String preferredClass) {
    this.preferredClass = preferredClass;
  }

  public int getGlobalLevel() {
    return globalLevel;
  }

  public void setGlobalLevel(int globalLevel) {
    this.globalLevel = globalLevel;
  }

  public int getAttackBaseBonus() {
    return attackBaseBonus;
  }

  public void setAttackBaseBonus(int attackBaseBonus) {
    this.attackBaseBonus = attackBaseBonus;
  }

  public int getReflexSavingThrow() {
    return reflexSavingThrow;
  }

  public void setReflexSavingThrow(int reflexSavingThrow) {
    this.reflexSavingThrow = reflexSavingThrow;
  }
  
  public ArrayList<DD_Spell> getKnownSpells(){
    ArrayList<DD_Spell> toReturn = new ArrayList<>();
    toReturn.addAll(this.knownSpells.values());
    return toReturn;
  }
  
  /**
   * 
   */
  public void display(){
    String toDisplay = "";

    toDisplay += "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\r";
    toDisplay += super.getPlayerName() + ";"
              + super.getCharacterFirstname() + ";"
              + super.getCharacterFamilyName()+ "\n\r"
              + super.getCharacterSize() + ";"
              + super.getCharacterWeight() + ";"
              + super.getCharacterAge()+ "\n\r"
              ;
    for(String s : super.getAttributes().keySet()){
      toDisplay += super.getAttribute(s) + " ; ";
    }
    toDisplay += "\n\r";
    toDisplay += this.characterRace + ";"
              +  this.deity + ";"
              +  this.preferredClass + ";"
              +  this.sizeType + "\n\r"
              +  this.maxLifePoints + ";"
              +  this.attackBaseBonus + ";"
              +  this.reflexSavingThrow + ";"
              +  this.fortitudeSavingThrow + ";"
              +  this.willSavingThrow + ";"
              +  this.globalLevel + "\n\r"
             ;
    
    for(String s : this.advantages){
      toDisplay += s + ";";
    }
    toDisplay += "\n\r";
    for(String s : this.talents.keySet()){
      toDisplay += s + ";";
    }
    toDisplay += "\n\r";
    for(DD_CharacterClass cl : this.classes.keySet()){
      toDisplay += cl.getName() + " level " + this.classes.get(cl) + "\r\n";
      toDisplay += cl.toString() + "\n\r";
    }
    toDisplay += "\n\r";
    toDisplay += "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
    
    System.out.println(toDisplay);
  }
}
