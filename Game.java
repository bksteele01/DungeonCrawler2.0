// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;

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
        player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();
	boxes2 = room2.getBoxes();
	enemies2 = room2.getEnemies();
	whichRoom = 1;
    }

    // prints a help menu to the left of the map
    private void showHelp() {
        String[] cmds = {"Commands:",
                         "---------",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
                         "Drop an item: d",
                         "List items: l",
                         "Equip weapon: w",
                         "Equip armor: a",
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
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
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
	    	if(player.getCol() == 15 && player.getRow() == 1){
			whichRoom = 2;
			player.setPosition(2, 2);
			break;
		}else{
			break;
		}

		// key for saving the game
	case s:
		Save save = new Save(player.getHealth());
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
            case RIGHT:
	    	if(whichRoom == 1){
                        player.move(0, 1, room);
                        break;
                }
                if(whichRoom == 2){
                        player.move(0, 1, room2);
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

            case DOWN:
	    	if(whichRoom == 1){
                        player.move(1, 0, room);
                        break;
                }
                if(whichRoom == 2){
                        player.move(1, 0, room2);
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
    private void redrawMapAndHelp() {
       	if(whichRoom == 1){
	room.draw();
	}
	if(whichRoom == 2){
	room2.draw();
	}
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
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
        return null;
    }

    // check for battles and return false if player has died
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
	return true;
    }	
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
           	}
	    }
	    i++;
	    
            }
         }
      }
}

