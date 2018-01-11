package rpgTools;

/**
 *
 * @author Nicolas Brax
 */
public class GameType {
  
  private enum Game {
    ADD,
    CTHULHU,
    FENGSHUI,
    PATIENT13,
    SHADOWRUN,
    NONE
  }
  
  private Game game;
  
  public GameType(){
    this.game = Game.NONE;
  }
  
  public GameType(String game){
    switch(game){
      case "AD&D":
        this.game = Game.ADD;
        break;
      case "Cthulhu":
        this.game = Game.CTHULHU;
        break;
      case "Feng Shui":
        this.game = Game.FENGSHUI;
        break;
      case "Patient 13":
        this.game = Game.PATIENT13;
        break;
      case "Shadowrun":
        this.game = Game.SHADOWRUN;
        break;
      default:
        this.game = Game.NONE;
        break;
    }// switch
  }
  
  public void setGame(String game){
    switch(game){
      case "AD&D":
        this.game = Game.ADD;
        break;
      case "Cthulhu":
        this.game = Game.CTHULHU;
        break;
      case "Feng Shui":
        this.game = Game.FENGSHUI;
        break;
      case "Patient 13":
        this.game = Game.PATIENT13;
        break;
      case "Shadowrun":
        this.game = Game.SHADOWRUN;
        break;
      default:
        this.game = Game.NONE;
        break;
    }// switch
  }
  
  public String getGame(){
    String toReturn = "";
    switch(this.game){
      case ADD:
        toReturn = "AD&D";
        break;
      case CTHULHU:
        toReturn = "Cthulhu";
        break;
      case FENGSHUI:
        toReturn = "Feng Shui";
        break;
      case PATIENT13:
        toReturn = "Patient 13";
        break;
      case SHADOWRUN:
        toReturn = "Shadowrun";
        break;
      default:
        toReturn = "none";
        break;
    }// switch
    return toReturn;
  }
  
  //public enum game{SHADOWRUN,FENGSHUI,PATIENT,ADD,CTHULHU,NONE};
  
}
