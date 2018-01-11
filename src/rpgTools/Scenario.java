package rpgTools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nicolas Brax
 */
public class Scenario {
  
  private String title;
  private String summary;
  private GameType game;
  private HashMap<Integer,ScenarioElement> primeElements;
  private HashMap<Integer,ScenarioElement> secondaryElements;
  private ArrayList<String> randomElements;
  private HashMap<String,String> results;
  
  /**
   * 
   */
  public Scenario(){
    this.title = "none";
    this.summary = "none";
    this.game = new GameType();
    this.primeElements = new HashMap<>();
    this.secondaryElements = new HashMap<>();
    this.randomElements = new ArrayList<>();
    this.results = new HashMap<>();
  }
  
  /**
   * 
   * @param title
   * @param summary
   * @param gameType 
   */
  public Scenario(String title, String summary, String gameType){
    this.title = title;
    this.summary = summary;
    this.game = new GameType(gameType);
    this.primeElements = new HashMap<>();
    this.secondaryElements = new HashMap<>();
    this.randomElements = new ArrayList<>();
    this.results = new HashMap<>();
  }
  
  /**
   * 
   * @param type
   * @param order
   * @param element
   * @param followups 
   */
  public void addElement(String type, int order, String element, ArrayList<Integer> followups){
    if(type.equalsIgnoreCase("prime")){
      this.primeElements.put(order, new ScenarioElement(element,followups));
    }
    else if(type.equalsIgnoreCase("secondary")){
      this.secondaryElements.put(order, new ScenarioElement(element,followups));
    }
    else{
      this.randomElements.add(element);
    }
  }
  
  /**
   * 
   * @param order
   * @param type
   * @return 
   */
  public ArrayList<Integer> getFollowers(int order, String type){
    if(type.equalsIgnoreCase("prime")){
      return this.primeElements.get(order).getFollowups();
    }
    else if(type.equalsIgnoreCase("secondary")){
      return this.secondaryElements.get(order).getFollowups();
    }
    else{
      return null;
    }
  }
  
  /**
   * 
   * @param order
   * @param type
   * @return 
   */
  public String getElement(int order, String type){
    if(type.equalsIgnoreCase("prime")){
      return this.primeElements.get(order).getElement();
    }
    else if(type.equalsIgnoreCase("secondary")){
      return this.secondaryElements.get(order).getElement();
    }
    else{
      return this.randomElements.get(order);
    }
  }
  
  /**
   * 
   * @param type
   * @param result 
   */
  public void setResult(String type, String result){
    this.results.put(type, result);
  }
  
  /**
   * 
   * @param type
   * @return 
   */
  public String getResult(String type){
    return this.results.get(type);
  }
  
  /**
   * 
   * @return 
   */
  public String getTitle(){
    return this.title;
  }
  
  /**
   * 
   * @return 
   */
  public String getSummary(){
    return this.summary;
  }
  
  /**
   * 
   * @return 
   */
  public String getGameType(){
    return this.game.getGame();
  }
  
  @Override
  public String toString(){
    return this.title + "\r\n"
            + this.summary + "\r\n"
            + this.game.getGame() + "\r\n"
            + getElement(0,"prime") + " of " + this.primeElements.size() + " elements" + "\r\n"
            + getElement(0,"secondary") + " of " + this.secondaryElements.size() + " elements" + "\r\n"
            + this.results.get("success") + " of " + this.randomElements.size() + " elements" + "\r\n"
            + this.results.get("failure")
            ;
  }
  
  public int getPrime(){
    return this.primeElements.size();
  }
  
  public int getSecond(){
    return this.secondaryElements.size();
  }
  
  public int getRandom(){
    return this.randomElements.size();
  }
  
  
  /**
   * 
   */
  class ScenarioElement{
    private String element;
    private ArrayList<Integer> followups;
    
    /**
     * 
     */
    public ScenarioElement(){
      
    }
    
    /**
     * 
     * @param element
     * @param followups 
     */
    public ScenarioElement(String element, ArrayList<Integer> followups){
      this.element = element;
      this.followups = new ArrayList<>();
      if(followups != null){
        this.followups.addAll(followups);
      }
      else{
        this.followups = null;
      }
    }
    
    /**
     * 
     * @return 
     */
    public String getElement(){
      return this.element;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Integer> getFollowups(){
      return this.followups;
    }
    
  }; // class ScenarioElement
  
  
  
}
