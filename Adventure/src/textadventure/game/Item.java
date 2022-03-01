package textadventure.game;
public class Item {
    private String name;
    private String description;
    int damage;
    int health;
    
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int setHealth() {
        return health;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int setDamage() {
        return damage;
    }
    
    public int getDamage() {
        return damage;
    }
}
