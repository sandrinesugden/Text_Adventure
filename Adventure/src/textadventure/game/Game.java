package textadventure.game;
public class Game {
    private Parser parser;
    private Room currentRoom; 
    private Player player;
    private CLS cls_var;
    private boolean lockPinkRoom = false;
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
        System.out.println(currentRoom.setRoom());
        System.out.println(currentRoom.getInventory());
        System.out.println(player.getInventory());
    }
    
    public void setupGame() {
        Room tower = new Room("tower", "You awaken in a small circular tower nothing inside of it. You feel very weak and sick. ", "You are in a circular tower with nothing inside of it. You feel very weak and ill. Your health is low and the only way to raise it is by drinking potions. Search the castle and the different rooms to find the blue, green and pink potions.");
        Room hallway = new Room("hallway", "you walk out the small room and enter a hallway with 3 doors.", "you leave the room and enter a skinny and long hallway. there are three doors along the walls. a green door, a pink door and a blue door.");
        Room pinkRoom = new Room("pink room", "You enter the Pink door and you enter onto a balcony. ", "You enter though the pink door and enter onto a small balcony. On the balcony there is a table with two jars on it. One Jar contains a pink drink and the other one has a black one.");
        Room greenRoom = new Room("green room", "you open the green door and enter a small closet.", "you enter through the green door into a small closet. there is a in the closet is a singular self. on the shelf is a small jar of a green drink.");
        Room blueRoom = new Room("blue room", "You walk into the room and there is a small table in the center of the room.", "You walk into the room and there is a small table in the center of the room.");
        
        Item itemKey = new Item("key", "description");
        
        Item blueDrink =  new Item ("blue drink", "description");
        Item greenDrink =  new Item ("green drink", "description");
        Item pinkDrink =  new Item ("pink drink", "description");
        Item blackDrink = new Item ("black drink", "description");
        
        tower.setRoom("hallway", hallway);
        
        hallway.setRoom("tower", tower);
        hallway.setRoom("blue room", blueRoom);
        hallway.setRoom("pink room", pinkRoom);
        hallway.setRoom("green room", greenRoom);
        
        blueRoom.setRoom("hallway", hallway);
        greenRoom.setRoom("hallway", hallway);
        pinkRoom.setRoom("hallway", hallway);
        
        greenRoom.setItem("key", itemKey);
        
        blueRoom.setItem("blueDrink", blueDrink);
        greenRoom.setItem("greenDrink", greenDrink);
        pinkRoom.setItem("pinkDrink", pinkDrink);
        pinkRoom.setItem("blackDrink", blackDrink);
   
        
        currentRoom = tower;
        
        try {
           cls_var.main(); 
        }
        catch (Exception e) {
           System.out.println(e); 
        }
        
        printInformation();
        
    }
    
    public boolean play() {
    while(true) {            
        Command command = parser.getCommand(); 
        
    while(player.getHealth()<=0 && player.getHealth() >=100) {
        return false;
        }
    
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
                 help (command);
                 break;
        }
    }
    
        public void help(Command command) {
        	if (!command.hasSecondWord()) {
        		System.out.println("The command words in this game are go, grab, drop, look, speak, drink and help.The goal of the game is to drink the pink, green and blue potions.");
        	}
        	if(command.getSecondWord().equals("go")) {
        		System.out.println("to travel type go and then where you want to go. You can only enter to the rooms listed under Rooms. ex: if under Rooms it says ‘hallway’ type go hallway.");
        	}
        	if(command.getSecondWord().equals("grab")) {
        		System.out.println("to pick up items type grab and then what you want to grab. you can only grab items in the room inventory. copy down their names exactly except without quotations. ex: to grab green drink type grab greenDrink.");
        	}
        	if(command.getSecondWord().equals("drop")) {
        		System.out.println("to drop items type drop and then what you want to grab. you can only drop items in the player inventory. copy down their names exactly except without quotations. ex: to drop green drink type drop greenDrink.");
        	}
        	if(command.getSecondWord().equals("look")) {
        		System.out.println("use look to inspect items further. if you want to look at something type look and then what you want to look at.you can look at the room you are in and items in the room.");
        	}
        	if(command.getSecondWord().equals("drink")) {
        		System.out.println("to drink things type in drink and then what you want to drink. you can only drink items that have already been grabbed and are in the player inventory and end in drink. ex: to drink greenDrink make sure it is in your player inventory and then type drink greenDrink.");
        	}
        }
    public void drink(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("drink what?");
            return;
        }
        String stringToDrink ="";
       
		if (!command.hasSecondWord()) {
        	stringToDrink=command.getSecondWord();
         }
		
         else if (command.hasSecondWord()) {
        	 stringToDrink=command.getSecondWord()+command.getLine();
         }
  
        	 if (stringToDrink == null) {
            System.out.println("you cannot drink that");
            return;
        }
                      
        if(command.getSecondWord().equals("blackDrink")){
            player.adjustHealth(-100);
            player.removeItem("blackDrink");
            System.out.println ("oh no! you drank the black drink and your health tanked and you died!");
            System.out.println("                                                   _ \r\n"
            		+ "                                                  | |\r\n"
            		+ "   __ _  __ _ _ __ ___   ___    _____   _____ _ __| |\r\n"
            		+ "  / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__| |\r\n"
            		+ " | (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |  |_|\r\n"
            		+ "  \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|  (_)\r\n"
            		+ "   __/ |                                             \r\n"
            		+ "  |___/                                              ");
        }
        if (command.getSecondWord().equals("blueDrink")){
            player.adjustHealth(30);    
            player.removeItem("blueDrink");
        }
        if (command.getSecondWord().equals("greenDrink")){
            player.adjustHealth(30);  
            player.removeItem("greenDrink");
        }
        if (command.getSecondWord().equals("pinkDrink")){
            player.adjustHealth(30);  
            player.removeItem("pinkDrink");
        }
		if (player.getHealth() == 100) {
			System.out.println("congrats, you survived! you were able to drink the green, blue and pink drink and restored your health to 100!");
			System.out.println("                               _       _ \r\n"
					+ "                              (_)     | |\r\n"
					+ "  _   _  ___  _   _  __      ___ _ __ | |\r\n"
					+ " | | | |/ _ \\| | | | \\ \\ /\\ / / | '_ \\| |\r\n"
					+ " | |_| | (_) | |_| |  \\ V  V /| | | | |_|\r\n"
					+ "  \\__, |\\___/ \\__,_|   \\_/\\_/ |_|_| |_(_)\r\n"
					+ "   __/ |                                 \r\n"
					+ "  |___/                                  ");
		}
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
        if(item.equals("key") ) {
        	boolean lockPinkRoom = true;
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
            System.out.println ("you cannot drop that");
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
        if(direction.equals("pink room") && player.getItem("key") == null) {
        	System.out.println("That room is locked");
        	nextRoom = null;
        }
        if(nextRoom == null) {
            System.out.println("you cannot go there");
            return;
        }
        else {
           currentRoom = nextRoom; 
        }
    }   
}