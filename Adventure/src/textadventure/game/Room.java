package textadventure.game;
import java.util.HashMap;
import java.util.Set;

public class Room {
    private String name;
    private String longDescription;
    private String shortDescription;
    private HashMap<String, Room> exits; 
    private HashMap<String, Item> inventory;
    
    public Room (String name, String longDescription, String shortDescription) {
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
       
        exits = new HashMap <>();        
        inventory = new HashMap <>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setItem(String name, Item item) {
        inventory.put(name, item);
    }
    
    public Item removeItem(String key) {
        return inventory.remove(key);
    }
    
    public Item getItem(String key) {
        return inventory.get(key);
    }
    
    public Item dropItem(String key) {
        return inventory.remove(key);    
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public String getLongDescription() {
        return longDescription;
    }
    
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public Item drinkItem(String key) {
        return inventory.remove(key);
    }
    
    public String getInventory() {
        String returnString = "Room Inventory: ";
        Set<String> keys = inventory.keySet();
        
        for(String item: keys) {
            returnString += "\"" + item + "\" ";
            //returnString += " " + exit;
        }
        
        return returnString;
    }
    
    public String getExit() {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        
        for(String exit: keys) {
            returnString += "\"" + exit + "\" ";
            //returnString += " " + exit;
        }
        
        return returnString;
    }
}