package textadventure.game;
public class Item {
    private String name;
    private String description;
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
    
}
