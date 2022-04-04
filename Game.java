// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
/**
 * The Game class is responsible for iniating a new game and continuing it.
 */
public class Game {
    private Room room;
    private Room room2;
    private Room room3;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    private ArrayList<Box> boxes2;
    private ArrayList<Box> boxes3;
    private ArrayList<Enemy> enemies2;
    private ArrayList<Enemy> enemies3;
    private int whichRoom;
    private String saveName;
    private boolean healthused;
    /**
     * The Game constructor creates the game.
     */
    public Game() {

	Scanner input = new Scanner(System.in);
	System.out.print(" What is your name? \r \n");
	String name = input.next();
	System.out.print("\r \n");
	System.out.print("Welcome " + name +"!" + "\r \n");
	System.out.print("Loading. \r \n");
	Terminal.pause(1);
	System.out.println("Loading..\r \n ");
	Terminal.pause(1);
	System.out.println("Loading...\r \n");
	Terminal.pause(1);	

        room = new Room(1);
	room2 = new Room(2);
	room3 = new Room(3);
        player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();
	boxes2 = room2.getBoxes();
	boxes3 = room3.getBoxes();
	enemies2 = room2.getEnemies();
	enemies3 = room3.getEnemies();
	whichRoom = 1;
	saveName = "";
	healthused = false;

    }

    // prints a help menu to the right  of the map
    /**
     * The showHelp method prints the help menu to the right of the map.
     */
    private void showHelp() {
         String[] cmds = {"Commands:",
                         "---------",
                         "Player - @",
                         "Enemy - *",
                         "Portal - p",
                         "Item - i",
			 "Health Station - [ ]",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
                         "Drop an item: d",
                         "List items: l",
                         "Equip weapon: w",
                         "Equip armor: a",
                         "Save Game: s",
                         "Use portal: u",
                         "Restore a save: r",
                         "Interact with door or health station: u",
                         "Quit: q"


        };

        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, room.getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    /**
     * The setStatus method sets the line under the map for status messages.
     * @param mesg is the message it is setting.
     */
    private void setStatus(String mesg) {
        // clear anything old first
        if(whichRoom == 1){
	Terminal.warpCursor(room.getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(room.getRows(), 0);
        System.out.print(mesg);
	}
	if(whichRoom == 2){
        Terminal.warpCursor(room2.getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }
		
        // then print the message
        Terminal.warpCursor(room2.getRows(), 0);
        System.out.print(mesg);
        }
	if(whichRoom == 3){
	Terminal.warpCursor(room3.getRows(), 0);
	for (int i = 0; i < 100; i++) {
		System.out.print(" ");
	}
	// then print the message
	Terminal.warpCursor(room3.getRows(), 0);
	System.out.print(mesg);
	System.out.print(whichRoom);
	}
    }

    // code for when the player tries to pickup an item
    /**
     * The pickup method is used when the player tries to pickup an item.
     */
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                if(whichRoom == 1){
		boxes.remove(thing);
		}
		if(whichRoom == 2){
		boxes2.remove(thing);
		}
		if(whichRoom == 3){
		boxes3.remove(thing);
		}
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    /**
     * The drop method is for when a player tries to drop an item.
     */
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    // handle the key which was read - return false if we quit the game
    /**
     * The handleKey method takes the user input from the menu.
     * @param key is the key that was pressed.
     */
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

            case l:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;
	    case u:
		if(whichRoom == 1){
	    	if(player.getCol() == 46 && player.getRow() == 26){
			whichRoom = 2;
			player.setPosition(2, 2);
			break;
		}else{
			break;
		     }
		}

		if(whichRoom == 2){
		if(player.getCol() == 46 && player.getRow() == 1 || player.getCol() == 2 && player.getRow() == 1){
                        if(player.getCol() == 46 && player.getRow() == 1){
				whichRoom = 3;
				player.setPosition(2,2);
                        break;
                }else{
                        whichRoom = 1;
			player.setPosition(2,2);
			break;
                    }
		}else{
			break;
		}
                 }    
	             
		if(whichRoom == 3){
		if((player.getCol() == 2 && player.getRow() == 2) || (player.getCol() == 50 && player.getRow() == 9)){
                        if(player.getCol() == 2 && player.getRow() == 2){	
				whichRoom = 2;
                        	player.setPosition(2, 2);
                        	break;
                	}
			if(player.getCol() == 50 && player.getRow() == 9){
				if(healthused == false){
					player.setHealth(100);
					healthused = true;
					System.out.print("Your health has been regenerated, and current hp is 100");
					Terminal.pause(2);
					break;
				}
				if(healthused == true){
					System.out.print("This health station has been used already!");
					Terminal.pause(2);
					break;
				}

                     	}
		}else{
			break;

		}
		}
			
		// key for saving the game
	case s:
		// add message to save and file name like in player.getInventory()
		System.out.print("Save name: ");
		Scanner input = new Scanner(System.in);
		saveName = input.next();			
		Save save = new Save(player.getHealth(), player.getCol(), player.getRow(), saveName, whichRoom, player);
		//calls method to save data to file
		save.SaveMaker();
		redrawMapAndHelp();
				
		break;
		// key for restoring the save
	case r:
		// calls on the restore class and calls the method
		
		System.out.print("Enter save name to load: ");
		Scanner input2 = new Scanner(System.in);
		saveName = input2.next();
		// replaces the inventory and adds the loaded items
		player.getInventory().eraseArray();
		Restore load = new Restore();
		
		//converts the current player status into the save files
		
		load.Restore(saveName, player);
		whichRoom = load.RoomRestore();
		player.setHealth(load.HpRestore());
		player.setPosition(load.rowRestore(), load.colRestore());			
		redrawMapAndHelp();
		break;
		
            // handle movement
            case LEFT: 
		if(whichRoom == 1){
			player.move(0, -1, room);
                	break;
		}
		if(whichRoom == 2){
			player.move(0, -1, room2);
			break;
		}
		if(whichRoom == 3){
			player.move(0, - 1, room3);
			break;
		}
            case RIGHT:
	    	if(whichRoom == 1){
                        player.move(0, 1, room);
                        break;
                }
                if(whichRoom == 2){
                        player.move(0, 1, room2);
                        break;
                }
		if(whichRoom == 3){
			player.move(0, 1, room3);
			break;
		}
            case UP:
	    	if(whichRoom == 1){
                        player.move(-1, 0, room);
                        break;
                }
                if(whichRoom == 2){
                        player.move(-1, 0, room2);
                        break;
                }
		if(whichRoom == 3){
			player.move(-1, 0, room3);
			break;
		}
            case DOWN:
	    	if(whichRoom == 1){
                        player.move(1, 0, room);
                        break;
                }
                if(whichRoom == 2){
                        player.move(1, 0, room2);
                        break;
                }
		if(whichRoom == 3){
			player.move(1, 0, room3);
			break;
		}
            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    /**
     * The redrawMapAndHelp method is called when we need to redraw the room and help menu.
     */
    private void redrawMapAndHelp() {
       	if(whichRoom == 1){
	room.draw();
	showHelp();
	}
	if(whichRoom == 2){
	room2.draw();
	showHelp();
	}
	if(whichRoom == 3){
	room3.draw();	
        showHelp();
    }
    }
    // returns a Box if the player is on it -- otherwise null
    /**
     * The checkForBox method checks the position for a box and if a player is standing on it.
     */
     private Box checkForBox() {
		Position playerLocation = player.getPosition();
        if(whichRoom == 1){
        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
        }
        }}
        if(whichRoom == 2){
        for(Box box : boxes2){
                if(playerLocation.equals(box.getPosition())){
                        return box;
                }
        }}
        if(whichRoom == 3){
        for(Box box : boxes3){
                if(playerLocation.equals(box.getPosition())){
                        return box;
                }
        }}
        return null;
      }
    /**
     * The checkBattles method checks the position for a enemy, to initiate a battle.
     */  
    private boolean checkBattles() {
        if(whichRoom == 1){
		Position playerLocation = player.getPosition();
        	// look for an enemy that is close
       		Enemy opponent = null;
        	for (Enemy enemy : enemies) {
            		if (playerLocation.isAdjacent(enemy.getPosition())) {
                		opponent = enemy;
            		}	
        	}
		// now do the battle
        	if (opponent != null) {
            		opponent.setBattleActive();
            		return player.fight(opponent, room, enemies);
        	}
		return true;
    	}
	if(whichRoom == 2){
		Position playerLocation = player.getPosition();
                // look for an enemy that is close
                Enemy opponent = null;
                for (Enemy enemy : enemies2) {
                        if (playerLocation.isAdjacent(enemy.getPosition())) {
                                opponent = enemy;
                        }
                }
                // now do the battle
                if (opponent != null) {
                        opponent.setBattleActive();
                        return player.fight(opponent, room2, enemies2);
                }
                return true;
	}
	if(whichRoom == 3){
		Position playerLocation = player.getPosition();
		//look for an enemy that is close
		Enemy opponent = null;
		for (Enemy enemy : enemies3) {
			if (playerLocation.isAdjacent(enemy.getPosition())) {
				opponent = enemy;
			}
		}
		// now do the battle
		if (opponent != null) {
			opponent.setBattleActive();
			return player.fight(opponent, room3, enemies3);
		}
		return true;
	}
	return true;
    }
    /**
    * The run method runs and initiates the entire game.
    */
    public void run() {
        // draw these for the first time now
	int ii = 0;
	for (int i = 0;ii == i;i++){
	

        boolean playing = true;
        while (playing) {
	    redrawMapAndHelp();
            // draw the entities
            if(whichRoom == 1){

	    	for (Box box : boxes) {
                	box.draw();
            	}
            	for (Enemy enemy : enemies) {
                	enemy.draw();
            	}
           	player.draw();

            	// read a key from the user
            	Terminal.warpCursor(room.getRows() + 1, 0);
            	Key key = Terminal.getKey();
            	playing = handleKey(key);

            	// clear status by default
            	setStatus("");

            	// move the enemies
            	for (Enemy enemy : enemies) {
                	enemy.walk(room);
           	}

            	// check for battles
            	if (checkBattles() == false) {
                	setStatus("You have been killed :(\n\r");
                	playing = false;
            	}

            	// check if we are on a box and print what's in it
            	Box thingHere = checkForBox();
            	if (thingHere != null) {
                	setStatus("Here you find: " + thingHere.getItem().getName());
			Terminal.pause(2);
            
	    	}
	    }
	    if(whichRoom == 2){
	    	for (Box box : boxes2) {
                	box.draw();
            	}
            	for (Enemy enemy : enemies2) {
                	enemy.draw();
            	}
            	player.draw();

            	// read a key from the user
            	Terminal.warpCursor(room.getRows() + 1, 0);
            	Key key = Terminal.getKey();
            	playing = handleKey(key);
	
            	// clear status by default
            	setStatus("");

            	// move the enemies
            	for (Enemy enemy : enemies2) {
                	enemy.walk(room2);
            	}

            	// check for battles
            	if (checkBattles() == false) {
                	setStatus("You have been killed :(\n\r");
                	playing = false;
            	}

            	// check if we are on a box and print what's in it
		Box thingHere = checkForBox();
            	if (thingHere != null) {
			setStatus("Here you find: " + thingHere.getItem().getName());
			Terminal.pause(2);
           	}
		

	    }
	      if(whichRoom == 3){
                for (Box box : boxes3) {
                        box.draw();
                }
                for (Enemy enemy : enemies3) {
                        enemy.draw();
                }
                player.draw();

                // read a key from the user
                Terminal.warpCursor(room.getRows() + 1, 0);
                Key key = Terminal.getKey();
                playing = handleKey(key);

                // clear status by default
                setStatus("");

                // move the enemies
                for (Enemy enemy : enemies3) {
                        enemy.walk(room3);
                }

                // check for battles
                if (checkBattles() == false) {
                        setStatus("You have been killed :(\n\r");
                        playing = false;
                }

                // check if we are on a box and print what's in it
                Box thingHere = checkForBox();
                if (thingHere != null) {

                        setStatus("Here you find: " + thingHere.getItem().getName());
			Terminal.pause(2);
                }
            }
	    i++;
	    
            }
         }
      }
}

