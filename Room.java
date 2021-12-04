// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies

import java.util.ArrayList;
import ansi_terminal.*;
/**
 * The room class creates the display of each room, also providing a starting location for player,
 * boxes, and enemies.
 */
public class Room {
    // the grid holds the room geometry
    private String[] grid;

    // the size of the room
    private int rows;
    private int cols;
    /** 
     * The Room constructor creates the rooms.
     * @param gridnum is the number of the room.
     */
    public Room(int gridnum) {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;

        // the actual room geometry
        // the i cells refer to where an item should be placed at
	if(gridnum == 1){ 
        grid  = new String[] {
            "##################                ######################    ",
            "##              ##                ##      i           ##    ",
            "##  @           ###########       ##        *         ##    ",
            "##                       ##       ##                  ##    ",
            "##              #######  ##       ##################  ##    ",
            "##              ##   ##  ##                       ##  ##    ",
            "##################   ##  ##################       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##   *  i           ##       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##############  ######       ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                       ############  ###############  ######",
            "                       ##                                 ##",
            "                       ##                                 ##",
            "    #####################                  *              ##",
            "    ##                                                    ##",
            "    ##  #################                                 ##",
            "    ##  ##             ##                                 ##",
            "    ##  ##             #################  ##################",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                       #######  #######           ",
            "    ##  ##                       ##            ##           ",
            "######  ####                     ##  i  *      ##           ",
            "##        ##                     ##          p ##           ",
            "## i  *   ##                     ################           ",
            "##        ##                                                ",
            "############                                                "
        };
    }
	if(gridnum == 2){
	grid = new String[] {
        "#####################               ############            ",
        "#p  @               #               #        p #            ",
	"#                   #               #          #            ",
	"#                   #               #####      #            ",
	"#             i     ##############      #      #            ",
	"#                                #      #      #            ",
	"##############################   #      #      #            ",
	"                             #   #      #      #            ",
	"                             #   #      #      #            ",
	"                             #   #      #      #            ",
	"                             #   #      #      #            ",
	"   ###########################   ########      #            ",
	"   #                                           #            ",
	"   #                                      *    #            ",	
	"   #        *                                  #            ",
	"   #                                           ##########   ",
	"   #                                                    #   ",
	"   #                   i                                #   ",
	"   #                                                    #   ",
	"   #      ##########################         ############   ",
	"   #      #                        #         #              ",
	"   #      #                        #         #    ########  ",
	"   #      #                        #      *  #    #      #  ",
	"   #      #                        #         ######      #  ",
	"   #      #                        #                     #  ",
	"   #      #                        #                     #  ",
	"   #      #                        #          i          #  ",
	"   ########                        #                     #  ",
	"                                   #######################  ",
	"                                                            "
	};
	}
	if(gridnum == 3){
        grid = new String[] {
        "##################################                          ",
        "#                                #                          ",
        "#p                               #                          ",
        "#######################          #                          ",
        "                      #          #                          ",
        "                      #          #                          ",
        "                      #          #                          ",
        "      #################          ######################     ",
        "      #                                               #     ",
        "      #                                               #     ",
        "      #                                               #     ",
        "      #                                               #     ",
        "      #                                               #     ",
        "      #  i              *           *                 #     ",
        "      #                                               #     ",
        "      ################           ######################     ",
        "                     #           #                          ",
        "                     #           #                          ",
        "                     #           #                          ",
        "                     #           #                          ",
        "                     #           #                          ",
        "                     #           #                          ",
        "                     #           #                          ",
        "      ################           ######################     ",
        "      #                                               #     ",
        "      #                 *          *                  #     ",
        "      # i                                        i    #     ",
        "      #                                               #     ",
        "      #################################################     ",
        "                                                            "
        };

}

}
    // returns the player's starting location in this room
    /**
     * The method getPlayerStart returns the player's starting position.
     */
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }

        return null;
    }

    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    /**
     * The getBoxes method returns a set of item boxes for this map.
     * @return boxes on the map.
     */
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }
 
    // returns a set of enemies from this map, similarly to the boxes above
    /**
     * The getEnemies method returns a set of enemies from this map.
     * @return enemies from the map.
     */
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }
    /**
     * The getRows method returns the rows from the map.
     * @return the rows from the map.
     */
    public int getRows() {
        return rows;
    }
    /**
     * The getCols methord returns the columns from the map.
     * @return the columns from the map.
     */
    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    /**
     * The draw method draws the map to the screen.
     */
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DONT draw starting items from map)
		    if(cell == 'p'){
		    	System.out.print("p");
			}else{
                    System.out.print(' ');
			}
                }
            }

            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    /**
     * The canGo methord returns if a given cell in the map is walkable or not.
     * @return the cell's coordinates.
     */
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col-1) != '#';
    }
}



