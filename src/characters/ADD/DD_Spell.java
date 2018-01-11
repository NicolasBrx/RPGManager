package characters.ADD;

/**
 *
 * @author Nicolas Brax
 */
public class DD_Spell {
  
  private String name;
  private String classe;
  private int level;
  private String school;
  private String description;
  private String prerequisite;
  private String attribute;
  
  /**
   * 
   */
  public DD_Spell(){
    
  }
  
  /**
   * 
   * @param name
   * @param classe
   * @param level
   * @param school
   * @param description
   * @param prerequisite 
   */
  public DD_Spell(String name, String classe, int level, String school, String description, String prerequisite){
    this.name = name;
    this.classe = classe;
    this.level = level;
    this.school = school;
    this.description = description;
    this.prerequisite = prerequisite;
    
    switch(this.classe){
      case "Magicien": this.attribute = "int";break;
      case "Barde": this.attribute = "cha";break;
      case "Druide": this.attribute = "wis";break;
      case "Ensorceleur": this.attribute = "cha";break;
      case "Paladin": this.attribute = "wis";break;
      case "Pretre": this.attribute = "wis";break;
      case "Rodeur": this.attribute = "wis";break;
      default: this.attribute = "int";break;
    }
    
  }

  /**
   * 
   * @return 
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * @param name 
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 
   * @return 
   */
  public String getClasse() {
    return classe;
  }

  /**
   * 
   * @param classe 
   */
  public void setClasse(String classe) {
    this.classe = classe;
  }

  /**
   * 
   * @return 
   */
  public int getLevel() {
    return level;
  }

  /**
   * 
   * @param level 
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * 
   * @return 
   */
  public String getSchool() {
    return school;
  }

  /**
   * 
   * @param school 
   */
  public void setSchool(String school) {
    this.school = school;
  }

  /**
   * 
   * @return 
   */
  public String getDescription() {
    return description;
  }

  /**
   * 
   * @param description 
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 
   * @return 
   */
  public String getPrerequisite() {
    return prerequisite;
  }

  /**
   * 
   * @param prerequisite 
   */
  public void setPrerequisite(String prerequisite) {
    this.prerequisite = prerequisite;
  }
  
  /**
   * 
   * @return 
   */
  public String getAttribute(){
    return this.attribute;
  }
  
  
}
