package rpgmanager;

import characters.GenericCharacter;
import ihm_desktop.MainFrame;
import java.util.ArrayList;

/**
 *
 * @author nicolas
 */
public class RPGManager {
  
  private static ArrayList<GenericCharacter> players = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      MainFrame ihm = new MainFrame();
      ihm.setVisible(true);
      
    }
    
}//RPGManager
