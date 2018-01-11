package equipment;

import java.util.HashMap;

/**
 *
 * @author nicolas
 */
public class Backpack {
  
  // a backpack with some things in some number inside
  private final HashMap<String,Integer> backpack;
  
  /**
   * 
   */
  Backpack(){
    this.backpack = new HashMap<>();
  }
  
  /**
   * 
   * @param item
   * @param number 
   */
  public void add(String item, int number){
    this.backpack.put(item,number);
  }
  
  /**
   * 
   * @param item 
   */
  public void remove(String item){
    if(this.backpack.get(item) == 1){
      this.backpack.remove(item);
    }
    else{
      this.backpack.replace(item,this.backpack.get(item)-1);
    }
    
  }
  
  /**
   * 
   * @return 
   */
  public HashMap<String,Integer> getBackpack(){
    return this.backpack;
  }
  
  /**
   * 
   * @param backpack 
   */
  public void setBackpack(HashMap<String,Integer> backpack){
    this.backpack.putAll(backpack);
  }
  
  /**
   * 
   * @return 
   */
  @Override
  public String toString(){
    String toReturn;
    toReturn = "";
    
    return toReturn;
  }
  
}
