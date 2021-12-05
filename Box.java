// Box.java
// represents a pickup-able item

import ansi_terminal.*;
/**
 * The box class extends the entity class, and it represents a pickup-able item.
 * @author Group9
 */
public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    /**
     * The box constructor creates a box with a given item in it.
     * @param row is the row number for this box.
     * @param col is column number for this box.
     * @param item is the item in the box.
     */
    public Box(int row, int col, Item item) { 
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }
    /**
     * The method Item returns an item.
     * @return item is the item that gets returned.
     */
    public Item getItem() {
        return item;
    }
}

