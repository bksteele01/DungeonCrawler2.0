// Entity.java
// this class represents one moveable, drawable thing in the game

import ansi_terminal.*;
/**
 * This class allows the ability to create an entity and represents one moveable, drawable
 * thing in the game.
 */
public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;
    /**
     * The entity constructor is used to instantiate an entity.
     * @param row is the row that the entity is created on.
     * @param col is the column that the entity is created on.
     * @param display is the display for the entity.
     * @param color is the color of the entity.
     */
    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    // move the entity to a new location
    /**
     * The setPosition method sets the position of an entity.
     * @param row is the row of the entity.
     * @param col is the column of the entity.
     */
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    // get the position of this entity
    /**
     * The getPosition method returns a position of the entity.
     * @return position returns the position of the entity.
     */
    public Position getPosition() {
        return position;
    }
    /**
     * The getRow method returns the row of the entity.
     * @return returns the row of the entity.
     */
    public int getRow() {
        return position.getRow();
    }
   /**
    * The getCol method returns the column of the entity.
    * @return returns the column of the entity.
    */
    public int getCol() {
        return position.getCol();
    }

    // translate the entity in space, unless it would hit a wall
    /**
     * The move method moves the entity to a new position.
     * @param rowChange is the changed row of the entity.
     * @param colChange is the changed column of the entity.
     * @param room is the room of the entity.
     * @return true or false if it is in a new position.
     */
    public boolean move(int rowChange, int colChange, Room room) {
        // find new position
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (room.canGo(newRow, newCol)) {
            // draw a space where it currently is
            Terminal.warpCursor(position.getRow(), position.getCol());
            System.out.print(" ");

            // and then move it
            position = new Position(newRow, newCol);
            return true;
        } else {
            return false;
        }
    }

    // draw this entity to the screen
    /**
     * The draw method draws the entity.
     */
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

