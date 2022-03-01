package textadventure.game;
public class Game {
    private Parser parser;
    private Room currentRoom; 
    private Player player;
    private CLS cls_var;
    private boolean locked;
    public Game() {
        parser = new Parser();
        player = new Player();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.setupGame();
        game.play();
    }
    
    public void printInformation() {
        System.out.println(currentRoom.getShortDescription());
        System.out.println(currentRoom.getExit());
        System.out.println(currentRoom.getInventory());
        System.out.println(player.getInventory());
    }
    
    public void setupGame() {
        Room tower = new Room("tower", "You awaken in a small circular tower nothing inside of it. You feel very weak and sick. ", "You are in a circular tower with nothing inside of it. You feel very weak and ill. Your health is low and the only way to raise it is by drinking potions. Search the castle and the different rooms to find the blue, green and pink potions.");
        Room hallway = new Room("hallway", "you walk out the small room and enter a hallway with 3 doors.", "you leave the room and enter a skinny and long hallway. there are three doors along the walls. a green door, a pink door and a blue door.");
        Room pinkRoom = new Room("pnk room", "short", "long");
        Room greenRoom = new Room("green room", "you open the green door and enter a small closet.", "you enter through the green door into a small closet. there is a in the closet is a singular self. on the shelf is a small jar of a green drink.");
        Room blueRoom = new Room("blue room", "You walk into the room and there is a small table in the center of the room.", "You walk into the room and there is a small table in the center of the room.");
        
        Item itemKey = new Item("key", "description");
        
        Item blueDrink =  new Item ("blue drink", "description");
        Item greenDrink =  new Item ("green drink", "description");
        Item pinkDrink =  new Item ("pink drink", "description");
        Item blackDrink = new Item ("black drink", "description");
        
        tower.setExit("hallway", hallway);
        
        hallway.setExit("tower", tower);
        hallway.setExit("blue room", blueRoom);
        hallway.setExit("pink room", pinkRoom);
        hallway.setExit("green room", greenRoom);
        
        blueRoom.setExit("hallway", hallway);
        greenRoom.setExit("hallway", hallway);
        pinkRoom.setExit("hallway", hallway);
        
        greenRoom.setItem("key", itemKey);
        
        blueRoom.setItem("blueDrink", blueDrink);
        greenRoom.setItem("greenDrink", greenDrink);
        pinkRoom.setItem("pinkDrink", pinkDrink);
        pinkRoom.setItem("blackDrink", blackDrink);
        
        //blackDrink.setDamage (100);
        //blueDrink.setHealth(30);
        
        currentRoom = tower;
        
        try {
            cls_var.main(); 
        }
        catch (Exception e) {
            System.out.println(e); 
        }
        
        printInformation();
        
    }
    
    public void play() {
    while(true) {            
        Command command = parser.getCommand(); 
        try {
            cls_var.main(); 
        }
        catch (Exception e) {
            System.out.println(e); 
        }
        processCommand(command);
        printInformation();   
        }
    }
    
        public void processCommand(Command command) {
            String commandWord = command.getCommandWord().toLowerCase();
        switch(commandWord) {
            case "speak" :
                System.out.println ("you wanted me to speak this word, " + command.getSecondWord());
                break;
            case "go" :
                goRoom(command);
                break;
            case "grab":
                grab(command);
                break;
            case "drop":
                drop(command);
                break;
            case "look":
                look(command);
                break;
            case "drink":
                drink(command);
                break;
            case "help":
                System.out.println("The command words in this game are go, grab, drop, look, speak, drink and help.The goal of the game is to drink the pink, green and blue potions.");
        }
    }
    
    public void drink(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("drink what?");
            return;
        }
        
        String itemToDrink;
		if (!command.hasSecondWord()) {
        	itemToDrink=command.getSecondWord();
         }
         else if (command.hasSecondWord()) {
        	 itemToDrink=command.getSecondWord()+command.getLine();
      
        	 if (itemToDrink == null) {
            System.out.println("you cannot drink that");
            return;
        }
        
        if(command.getSecondWord().equals("blackDrink")){
            player.adjustHealth(-100);
        }
        if (command.getSecondWord().equals("blueDrink")){
            player.adjustHealth(30);
        }
        if (command.getSecondWord().equals("greenDrink")){
            player.adjustHealth(30);
        }
        if (command.getSecondWord().equals("pinkDrink")){
            player.adjustHealth(30);
        }
        player.adjustHealth(itemToDrink.getDamage()); {
   
        }

        public void look(Command command) {
        String printString = "looking at ";
        String thingToLook;
        if (!command.hasSecondWord()) {
            System.out.println("look at what?");
            return;
        }
        
        if (!command.hasSecondWord()) {
           thingToLook=command.getSecondWord();
        }
        else if (command.hasSecondWord()) {
           thingToLook=command.getSecondWord()+command.getLine();
        }
        
        thingToLook=command.getSecondWord()+command.getLine();
        
        if (thingToLook.equals(currentRoom.getName())) {
            printString += "the room: " + currentRoom.getName() + "\n" + currentRoom.getLongDescription();
        }
        else if (currentRoom.getItem (thingToLook) != null) {
            printString += "the item: " + currentRoom.getItem(thingToLook).getName() + "\n" + currentRoom.getItem(thingToLook).getDescription();
        }
        else if (currentRoom.getItem (thingToLook) != null) {
            printString += "the item: " + player.getItem(thingToLook).getName() + "\n" + player.getItem(thingToLook).getDescription();
        }
        else {
            printString += "\nyou can't look at that"; 
        }
        
        System.out.println(printString);
    }
    
    public void grab(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("grab what?");
            return;
        }
        
        String item = command.getSecondWord();
        Item itemToGrab = currentRoom.removeItem(item);
        
        if (itemToGrab == null) {
            System.out.println("you cannot grab that");
            return;
        }
        else {
           player.setItem (item, itemToGrab);
        }
    }
    
    public void drop(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println ("drop what?");
            return;
        }
        
        String item = command.getSecondWord();
        Item itemToDrop = player.removeItem(item);
        
        if (itemToDrop == null) {
            System.out.println ("you cannot grab that");
            return;
        }
        else {
           currentRoom.setItem(item, itemToDrop);
        }
    }
    
    public void goRoom(Command command) {
        String direction = "";
        if (!command.hasSecondWord()) {
            System.out.println("go where?");
            return;
        }
        if (!command.hasLine()) {
            direction = command.getSecondWord();
        }
        else if (command.hasLine()) {
            direction = command.getSecondWord() + command.getLine();
        }
        
        Room nextRoom = currentRoom.getExit(direction);
        
        if(nextRoom == null) {
            System.out.println("you cannot go there");
            return;
        }
        else {
           currentRoom = nextRoom; 
        }
    }   
    public void lockRoom(boolean lock) {
        locked =  lock;

    }
}