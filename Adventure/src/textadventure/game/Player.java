package textadventure.game;
import java.util.HashMap;
import java.util.Set;

public class Player {
    HashMap<String, Item> inventory; 
    int health;
    
    public Player() {
        inventory = new HashMap <>();
        health = 10;
    }
    
    public int getHealth () {
        return health;
    }
    
    public void adjustHealth(int value) {
    	health += value;
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

    public String getInventory() {
        String returnString = "Player Inventory: ";
        Set<String> keys = inventory.keySet();
        
        for(String item: keys) {
            returnString += " " + item;
        }
        System.out.println ("players health is " + health);
        return returnString;
    }
    
}