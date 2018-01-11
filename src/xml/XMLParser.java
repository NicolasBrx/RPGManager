package xml;

import characters.ADD.DD_Spell;
import characters.DD_Character;
import characters.GenericCharacter;
import characters.P13_Character;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import npc.NonPlayerCharacter;
import npc.P13_Patient;
import npc.P13_Supervisor;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import rpgTools.GameType;
import rpgTools.Scenario;

/**
 * This class is used to parse different xml files providing data for the different game
 * that can be handled by the RPGManager software.
 * 
 * @author Nicolas Brax
 */
public class XMLParser {

  /***** Player Characters save and load *****/
  /**
   *
   * @param charactersToSave
   * @return
   */
  public int saveCharacters(ArrayList<GenericCharacter> charactersToSave) {
    int toReturn = -1;

    // generic data
    try {
      
      String filepath = System.getProperty("user.dir") + "/data/player_characters/";
      switch (charactersToSave.get(0).getGameType()){
        case "Patient 13":
          filepath += "patient13.xml";
          break;
        case "Shadowrun":
          filepath += "shadowrun.xml";
          break;
        case "Feng Shui":
          filepath += "fengshui.xml";
          break;
        case "AD&D":
          filepath += "add.xml";
          break;
        case "Cthulhu":
          filepath += "cthulhu.xml";
          break;
        default:break;
      }//switch

      File file = new File(filepath);
      if (file.exists()) {
        File renamed = new File(filepath + ".bk");
        file.renameTo(renamed);
        file.delete();
      }
      file.createNewFile();
      DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));

      // root
      Element patient13 = new Element("patient13"); //TODO: changer en fonction du jeu!!!
      Document doc = new Document(patient13);

      for (GenericCharacter cts : charactersToSave) {

        // player
        Element player = new Element("player");
        player.setAttribute(new Attribute("name", cts.getPlayerName()));

        // civil state
        Element character = new Element("character");
        character.setAttribute(new Attribute("firstname", cts.getCharacterFirstname()));
        character.setAttribute(new Attribute("familyname", cts.getCharacterFamilyName()));

        // physical
        Element age = new Element("age");
        age.setText(Integer.toString(cts.getCharacterAge()));
        character.addContent(age);
        Element weight = new Element("weight");
        weight.setText(Double.toString(cts.getCharacterWeight()));
        character.addContent(weight);
        Element size = new Element("size");
        size.setText(Double.toString(cts.getCharacterSize()));
        character.addContent(size);
        Element gender = new Element("gender");
        gender.setText(cts.isFemale() ? "femme" : "homme");
        character.addContent(gender);

        // attributes
        HashMap<String, Integer> tmp = cts.getAttributes();
        for (String cle : tmp.keySet()) {
          Element tmpElement = new Element(cle);
          tmpElement.setText(Integer.toString(tmp.get(cle)));
          character.addContent(tmpElement);
        }

        // game type specific
        switch (cts.getGameType()) {
          case "Patient 13":
            character.setAttribute(new Attribute("surname", ((P13_Character) cts).getSurname()));
            Element seniority = new Element("seniority");
            character.addContent(seniority);
            seniority.setText(Integer.toString(((P13_Character) cts).getSeniority()));
            Element supervisor = new Element("supervisor");
            character.addContent(supervisor);
            supervisor.setText(((P13_Character) cts).getSupervisor());
            Element room = new Element("room");
            room.setText(((P13_Character) cts).getRoom());
            character.addContent(room);
            Element lineaments = new Element("lineaments");
            for(String cle : ((P13_Character) cts).getLineaments().keySet()){
              Element tmpL = new Element(cle);
              tmpL.setText(Integer.toString(((P13_Character) cts).getLineaments().get(cle)));
              lineaments.addContent(tmpL);
            }
            character.addContent(lineaments);
            break;
            
          case "AD&D":
            break;
            
          case "Feng Shui":
            break;
            
          case "Shadowrun":
            break;
            
          case "Cthulhu":
            break;

          default:
            break;

        }//switch

        player.addContent(character);
        Element equipment = new Element("equipement");
        equipment.setText("Test");
        player.addContent(equipment);
        doc.getRootElement().addContent(player);

      }//for characters size
      
      XMLOutputter xmlOut = new XMLOutputter();
      xmlOut.setFormat(Format.getPrettyFormat());
      xmlOut.output(doc, stream);
      stream.close();
      
    }// try
    catch (IOException ioe) {
      System.err.println("ECHEC!");
    }

    return toReturn;
  }

  /**
   *
   * @param filepath
   * @param gameType
   * @return
   */
  public ArrayList<GenericCharacter> loadCharacters(String filepath, GameType gameType) {
    ArrayList<GenericCharacter> toReturn = new ArrayList<>();

    try {
      File inputFile = new File(filepath);
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> playerList = root.getChildren();
      
      for (Element player : playerList) {

        String playerName = player.getAttribute("name").getValue();
        Element character = player.getChild("character");
        String firstname = character.getAttribute("firstname").getValue();
        String familyname = character.getAttribute("familyname").getValue();
        int age = Integer.parseInt(character.getChild("age").getValue());
        double weight = Double.parseDouble(character.getChild("weight").getValue());
        double size = Double.parseDouble(character.getChild("size").getValue());
        boolean gender = character.getChild("gender").getValue().equalsIgnoreCase("femme");

        switch (gameType.getGame()) {
          case "Patient 13":
            String surname = character.getAttribute("surname").getValue();
            int vitality = Integer.parseInt(character.getChild("vitality").getText());
            int lucidity = Integer.parseInt(character.getChild("lucidity").getText());
            int coldblood = Integer.parseInt(character.getChild("coldblood").getText());
            int seniority = Integer.parseInt(character.getChild("seniority").getText());
            String supervisor = character.getChild("supervisor").getText();
            String room = character.getChild("room").getText();
            Element lineaments = character.getChild("lineaments");
            List<Element> lineamentList = lineaments.getChildren();
            HashMap<String,Integer> lineamentMap = new HashMap<>();
            for (Element lineament : lineamentList) {
              lineamentMap.put(lineament.getName(),
                               Integer.parseInt(lineament.getText()));
            }
            P13_Character patient = new P13_Character(
                    playerName, firstname, familyname, surname, age, weight, size, gender, lucidity,
                    vitality, coldblood, seniority, "testL", "notesL", supervisor, room
            );
            patient.setLineaments(lineamentMap);
            toReturn.add(patient);
            break;
            
          case "AD&D":
            int str = Integer.parseInt(character.getChild("strength").getText());
            int dex = Integer.parseInt(character.getChild("dexterity").getText());
            int con = Integer.parseInt(character.getChild("constitution").getText());
            int inte = Integer.parseInt(character.getChild("intelligence").getText());
            int wis = Integer.parseInt(character.getChild("wisdom").getText());
            int cha = Integer.parseInt(character.getChild("charisma").getText());
            int xp = Integer.parseInt(character.getChild("xp").getText());
            int currentLife = Integer.parseInt(character.getChild("currentlife").getText());
            String race = character.getChild("race").getText();
            String[] alignment = {character.getChild("alignments").getChildText("loyalty"),
                                  character.getChild("alignments").getChildText("goodness")};
            String deity = character.getChild("deity").getText();
            ArrayList tmpClasses = new ArrayList<>();
            int glvl = 0;
            for(Element classe : character.getChild("classes").getChildren()){
              tmpClasses.add(classe.getText());
              tmpClasses.add(classe.getAttributeValue("level"));
              glvl += Integer.parseInt(classe.getAttributeValue("level"));
            }
            ArrayList tmpSkills = new ArrayList<>();
            for(Element skill : character.getChild("skills").getChildren()){
              tmpSkills.add(skill.getText());
              tmpSkills.add(skill.getAttributeValue("level"));
            }
            ArrayList tmpTalents = new ArrayList<>();
            for(Element talent : character.getChild("talents").getChildren()){
              tmpTalents.add(talent.getText());
            }
            ArrayList tmpSpells = new ArrayList<>();
            for(Element spell : character.getChild("spells").getChildren()){
              tmpSpells.add(spell.getText());
            }
            GenericCharacter hero = new DD_Character(playerName,firstname,familyname,gender,age,weight,size,
                                                     str,dex,con,inte,wis,cha,race,alignment,deity,xp,
                                                     glvl,currentLife,tmpClasses,tmpSkills,tmpTalents,tmpSpells
                                                    );
            toReturn.add(hero);
            break;
            
          case "Feng Shui":
            break;
            
          case "Shadowrun":
            break;
            
          case "Cthulhu":
            break;

          default:
            break;
        }//switch
      }//for playerList
    }//try
    catch (JDOMException | IOException e) {
      e.printStackTrace();
    }
    return toReturn;
  }

  /***** Non Player Characters save and load *****/
  /**
   * 
   * @param gameType
   * @param npcToSave
   * @return 
   */
  public int saveNPC(HashMap<String,ArrayList<NonPlayerCharacter>> npcToSave, GameType gameType){
    int toReturn = -1;
    
    System.out.println(npcToSave);
    
    try {
      
      String filepath = System.getProperty("user.dir") + "/data/";
      switch (gameType.getGame()){
        case "Patient 13":
          filepath += "patient_13/npc.xml";
          break;
        case "Shadowrun":
          filepath += "shadowrun/npc.xml";
          break;
        case "Feng Shui":
          filepath += "feng_shui/npc.xml";
          break;
        case "AD&D":
          filepath += "donjons_and_dragons/npc.xml";
          break;
        case "Cthulhu":
          filepath += "cthulhu/npc.xml";
          break;
        default:break;
      }//switch

      File file = new File(filepath);
      if (file.exists()) {
        File renamed = new File(filepath + ".bk");
        file.renameTo(renamed);
        file.delete();
      }
      file.createNewFile();
      DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));

      // root
      Element root = new Element(
              (gameType.getGame().equals("Patient 13") ? "p13npc" : 
                      (gameType.getGame().equals("AD&D") ? "addnpc" : 
                              (gameType.getGame().equals("Cthulhu") ? "cthnpc" : 
                                      (gameType.getGame().equals("Shadowrun") ? "shrnpc" : "fsnpc"))))
      );
      Document doc = new Document(root);
      
      for(String key : npcToSave.keySet()){
        
        for(NonPlayerCharacter nts : npcToSave.get(key)){
          Element npc = new Element("npc");
          Element name = new Element("name");
          name.setText(nts.getName());
          npc.addContent(name);
          
          switch(key){
            case "supervisors":
              npc.setAttribute("type","supervisor");
              Element office = new Element("office");
              office.setText(((P13_Supervisor)nts).getOffice());
              npc.addContent(office);
              Element secret = new Element("secret");
              secret.setText(((P13_Supervisor)nts).getSecret());
              npc.addContent(secret);
              Element ohs = new Element("OHS");
              ohs.setText(((P13_Supervisor)nts).isOHS() ? "oui" : "non");
              npc.addContent(ohs);
              
              Element patients = new Element("patients");
              for(String p : ((P13_Supervisor)nts).getPatients()){
                Element patient = new Element("name");
                patient.setText(p);
                patients.addContent(patient);
              }
              npc.addContent(patients);
              
              Element allies = new Element("allies");
              for(String a : ((P13_Supervisor)nts).getAllies()){
                Element ally = new Element("ally");
                ally.setText(a);
                allies.addContent(ally);
              }
              npc.addContent(allies);
              
              Element ennemies = new Element("ennemies");
              for(String e : ((P13_Supervisor)nts).getEnnemies()){
                Element ennemy = new Element("ennemy");
                ennemy.setText(e);
                ennemies.addContent(ennemy);
              }
              npc.addContent(ennemies);
              break;
              
            case "patients":
              npc.setAttribute("type","patient");
              Element room = new Element("room");
              room.setText(((P13_Patient)nts).getRoom());
              npc.addContent(room);
              Element supervisor = new Element("supervisor");
              supervisor.setText(((P13_Patient)nts).getSupervisor());
              npc.addContent(supervisor);
              Element secretP = new Element("secret");
              secretP.setText(((P13_Patient)nts).getSecret());
              npc.addContent(secretP);
              
              Element alliesP = new Element("allies");
              for(String a : ((P13_Patient)nts).getAllies()){
                Element ally = new Element("ally");
                ally.setText(a);
                alliesP.addContent(ally);
              }
              npc.addContent(alliesP);
              
              Element ennemiesP = new Element("ennemies");
              for(String e : ((P13_Patient)nts).getEnnemies()){
                Element ennemy = new Element("ennemy");
                ennemy.setText(e);
                ennemiesP.addContent(ennemy);
              }
              npc.addContent(ennemiesP);
              break;
              
            default:
              break;
          }//switch key(patients|supervisors)
          
          Element notes = new Element("notes");
          for(String n : nts.getNotes()){
            Element note = new Element("note");
            note.setText(n);
            notes.addContent(note);
          }
          npc.addContent(notes);
          doc.getRootElement().addContent(npc);
          
        }//for npcToSave
      }//for types of npcToSave
      
      XMLOutputter xmlOut = new XMLOutputter();
      xmlOut.setFormat(Format.getPrettyFormat());
      xmlOut.output(doc, stream);
      stream.close();

    }// try
    catch (IOException ioe) {
      System.err.println("ECHEC!");
    }
    
    return toReturn;
  }
  
  /**
   * 
   * @param filepath
   * @param gameType
   * @return 
   */
  public HashMap<String,ArrayList<NonPlayerCharacter>> loadNPC(String filepath, GameType gameType){
    HashMap<String,ArrayList<NonPlayerCharacter>> toReturn = new HashMap<>();

    try {
      File inputFile = new File(filepath);
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> npcList = root.getChildren();

      boolean initialized = false;
      for (Element npc : npcList) {

        String npcName = npc.getChild("name").getValue();
      
        switch(gameType.getGame()){
          case "Patient 13":
            if(!initialized){
              toReturn.put("supervisors",new ArrayList<>());
              toReturn.put("patients",new ArrayList<>());
              initialized = !initialized;
            }
            
            String secret = npc.getChild("secret").getValue();
            
            List<Element> noteList = npc.getChild("notes").getChildren();
            ArrayList<String> notes = new ArrayList<>();
            for(Element note : noteList){
              notes.add(note.getText());
            }
            List<Element> allyList = npc.getChild("allies").getChildren();
            ArrayList<String> allies = new ArrayList<>();
            for(Element ally : allyList){
              allies.add(ally.getText());
            }
            List<Element> ennemyList = npc.getChild("ennemies").getChildren();
            ArrayList<String> ennemies = new ArrayList<>();
            for(Element ennemy : ennemyList){
              ennemies.add(ennemy.getText());
            }
            
            if(npc.getAttribute("type").getValue().equalsIgnoreCase("supervisor")){
              String office = npc.getChild("office").getValue();
              String ohs = npc.getChild("OHS").getValue();
              List<Element> patientList = npc.getChild("patients").getChildren();
              ArrayList<String> patients = new ArrayList<>();
              for(Element patient : patientList){
                patients.add(patient.getText());
              }
              P13_Supervisor tmpNPC = new P13_Supervisor(npcName,office,secret,(ohs.equalsIgnoreCase("oui")));
              tmpNPC.setNotes(notes);
              tmpNPC.setAllies(allies);
              tmpNPC.setEnnemies(ennemies);
              tmpNPC.setPatients(patients);
              toReturn.get("supervisors").add(tmpNPC);
            }
            else{
              String room = npc.getChild("room").getValue();
              String supervisor = npc.getChild("supervisor").getValue();
              P13_Patient tmpNPC = new P13_Patient(npcName,room,secret,supervisor);
              tmpNPC.setNotes(notes);
              tmpNPC.setAllies(allies);
              tmpNPC.setEnnemies(ennemies);
              toReturn.get("patients").add(tmpNPC);
            }
            break;
            
          case "Shadowrun":
            break;
            
          case "Cthulhu":
            break;
            
          case "AD&D":
            break;
            
          case "Feng Shui":
            break;
            
          default:
            break;
        }
        
      }
      
    }//try
    catch (JDOMException | IOException e) {

    }
    return toReturn;
  }//load
  
  /***** Stuff and all sort of data to use *****/
  /**
   * 
   * @param raceName
   * @return 
   */
  public HashMap<String,ArrayList<String>> getDDRace(String raceName){
    HashMap<String,ArrayList<String>> toReturn = new HashMap<>();
    
    try {
      File inputFile = new File(System.getProperty("user.dir") + "/data/donjons_and_dragons/races/playable_races.xml");
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> raceList = root.getChildren();
      for (Element cl : raceList) {
        if(cl.getAttributeValue("name").equalsIgnoreCase(raceName)){
          
          ArrayList<String> toInsert = new ArrayList<>();
          toInsert.add(cl.getChildText("size"));
          toReturn.put("size", toInsert);
          for(Element e : cl.getChild("modificators").getChildren()){
            toInsert = new ArrayList<>();
            toInsert.add(e.getText());
            toReturn.put(e.getName(),toInsert);
          }
          toInsert = new ArrayList<>();
          for(Element e : cl.getChild("advantages").getChildren()){
            toInsert.add(e.getText());
          }
          toReturn.put("advantages",toInsert);
          toInsert = new ArrayList<>();
          for(Element e : cl.getChild("languages").getChildren()){
            toInsert.add(e.getText());
          }
          toReturn.put("languages",toInsert);
          toInsert = new ArrayList<>();
          toInsert.add(cl.getChildText("preferredClass"));
          toReturn.put("preferred", toInsert);
        }
      }
    }//try
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    
    return toReturn;
  }
  
  /**
   * 
   * @param className
   * @return 
   */
  public Element getDDClass(String className){
    Element toReturn = null;
    try {
      File inputFile = new File(System.getProperty("user.dir") + "/data/donjons_and_dragons/classes/playable_classes.xml");
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> classList = root.getChildren();
      for (Element cl : classList) {
        if(cl.getAttributeValue("name").equalsIgnoreCase(className)){
          toReturn = cl;
        }
      }
    }//try
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    return toReturn;
  } // getDDClass
  
  
  /**
   * 
   * @return 
   */
  public HashMap<String,String> getDDSkills(){
    HashMap<String,String> toReturn = new HashMap<>();
    
    try{
      File inputFile = new File(System.getProperty("user.dir") + "/data/donjons_and_dragons/data/skills.xml");
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> skillList = root.getChildren();
      for(Element skill : skillList){
        String key = skill.getChildText("name");
        String value = skill.getChildText("attribute");
        toReturn.put(key, value);
      }
    } //try
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    
    return toReturn;
  }
  
  /**
   * 
   * @return 
   */
  public HashMap<String,ArrayList<String>> getDDTalents(){
    HashMap<String,ArrayList<String>> toReturn = new HashMap<>();
    
    try{
      File inputFile = new File(System.getProperty("user.dir") + "/data/donjons_and_dragons/data/talents.xml");
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      List<Element> talentList = root.getChildren();
      for(Element talent : talentList){
        String key = talent.getAttributeValue("id");
        ArrayList<String> value = new ArrayList<>();
        value.add(talent.getChildText("name"));
        if(talent.getChildText("description") != null){
          value.add(talent.getChildText("description"));
        }
        if(talent.getChildText("prerequisite") != null){
          value.add(talent.getChildText("prerequisite"));
        }
        toReturn.put(key, value);
      }
    } //try
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    
    return toReturn;
  }
  
  public Scenario loadScenario(String file){
    try{
      File inputFile = new File(file);
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();
      
      Scenario toReturn = new Scenario(root.getChildText("title"),
                                       root.getChildText("abstract"),
                                       root.getChildText("game")
                                      );
      
      List<Element> primeList = root.getChild("primeevents").getChildren();
      for(Element prime : primeList){
        ArrayList<Integer> fups = new ArrayList<>();
        if(prime.getAttribute("followup") != null){
          String[] parts = prime.getAttributeValue("followup").split(",");
          for(int i = 0 ; i < parts.length ; ++i){
            fups.add(Integer.parseInt(parts[i]));
          }
        }
        toReturn.addElement("prime", Integer.parseInt(prime.getAttributeValue("order")), prime.getText(), fups.size() > 0 ? fups : null);
      }
      List<Element> secondList = root.getChild("secondevents").getChildren();
      for(Element second : secondList){
        ArrayList<Integer> fups = new ArrayList<>();
        if(second.getAttribute("followup") != null){
          String[] parts = second.getAttributeValue("followup").split(",");
          for(int i = 0 ; i < parts.length ; ++i){
            fups.add(Integer.parseInt(parts[i]));
          }
        }
        toReturn.addElement("secondary", Integer.parseInt(second.getAttributeValue("order")), second.getText(), fups.size() > 0 ? fups : null);
      }
      List<Element> randomList = root.getChild("randomevents").getChildren();
      for(Element random : randomList){
        toReturn.addElement("random", -1, random.getText(), null);
      }
      
      toReturn.setResult("success", root.getChild("results").getChildText("success"));
      toReturn.setResult("failure", root.getChild("results").getChildText("failure"));
      
      return toReturn;
      
    }
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    return null;    
  }
  
  public HashMap<String,DD_Spell> loadDDSpells(){
    try{
      File inputFile = new File(System.getProperty("user.dir") + "/data/donjons_and_dragons/data/spells.xml");
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(inputFile);
      Element root = document.getRootElement();

      HashMap<String,DD_Spell> toReturn = new HashMap<>();
      
      for(Element spell : root.getChildren()){
        String spellId = spell.getAttributeValue("id");
        String name = spell.getChildText("name");
        String classe = spell.getChildText("class");
        int level = Integer.parseInt(spell.getChildText("level"));
        String school = spell.getChildText("school");
        String description = spell.getChildText("description");
        String prerequisite = spell.getChildText("prerequisite");
        
        toReturn.put(spellId, new DD_Spell(name,classe, level,school,description,prerequisite));
      }
      
      return toReturn;
      
    }
    catch (JDOMException | IOException e) {
      System.err.println("Error ta mère!");
      e.printStackTrace();
    }
    return null;    
  }
  
  public void saveScenario(HashMap<String,ArrayList<String>> scenario, GameType gameType){
    try {
      
      String filepath = System.getProperty("user.dir") + "/data/scenarii/";
      switch (gameType.getGame()){
        case "Patient 13":
          filepath += "patient13/";
          break;
        case "Shadowrun":
          filepath += "shadowrun/";
          break;
        case "Feng Shui":
          filepath += "feng_shui/";
          break;
        case "AD&D":
          filepath += "donjons_and_dragons/";
          break;
        case "Cthulhu":
          filepath += "cthulhu/";
          break;
        default:break;
      }//switch
      filepath += scenario.get("title").get(0) + ".xml";

      File file = new File(filepath);
      if (file.exists()) {
        File renamed = new File(filepath + ".bk");
        file.renameTo(renamed);
        file.delete();
      }
      file.createNewFile();
      DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));

      // root
      Element root = new Element("scenario"); //TODO: changer en fonction du jeu!!!
      Document doc = new Document(root);
      
      Element game = new Element("game");
      game.setText(gameType.getGame());
      root.addContent(game);
      
      Element title = new Element("title");
      title.setText(scenario.get("title").get(0));
      root.addContent(title);
      
      Element summary = new Element("abstract");
      summary.setText(scenario.get("summary").get(0));
      root.addContent(summary);
      
      Element prime = new Element("primeevents");
      int count = 0;
      System.out.println(scenario.get("prime").size());
      for(String s :scenario.get("prime")){
        Element event = new Element("event");
        event.setAttribute("order",String.valueOf(count));
        if(count < scenario.get("prime").size() - 1){
          event.setAttribute("followup",String.valueOf(count + 1));  
        }
        count++;
        event.setText(s);
        prime.addContent(event);
      }
      root.addContent(prime);
      
      Element second = new Element("secondevents");
      count = 0;
      for(String s : scenario.get("second")){
        Element event = new Element("event");
        event.setAttribute("order",String.valueOf(count));
        if(count < scenario.get("second").size() - 1){
          event.setAttribute("followup",String.valueOf(count + 1));  
        }
        count++;
        event.setText(s);
        second.addContent(event);
      }
      root.addContent(second);
      
      Element random = new Element("randomevents");
      for(String s : scenario.get("random")){
        Element event = new Element("event");
        event.setText(s);
        random.addContent(event);
      }
      root.addContent(random);
      
      Element results = new Element("results");
      Element success = new Element("success");
      success.setText(scenario.get("success").get(0));
      results.addContent(success);
      Element failure = new Element("failure");
      failure.setText(scenario.get("failure").get(0));
      results.addContent(failure);
      root.addContent(results);

      XMLOutputter xmlOut = new XMLOutputter();
      xmlOut.setFormat(Format.getPrettyFormat());
      xmlOut.output(doc, stream);
      stream.close();
      
    }// try
    catch (IOException ioe) {
      System.err.println("ECHEC!");
    }
  }
  
}//XMLParser
