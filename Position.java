// Position.java
// represents a simple row/col position in the world
/**
 * The position class is used for representing a position in the world.
 */
public class Position {
    private int row;
    private int col;
    /**
     * The Position constructor is the default constructor for the position class.
     */
    public Position() {
        row = 0;
        col = 0;
    }
    /**
     * The Position constructor that sets the location.
     * @param row is the row of the location.
     * @param col is the column of the location.
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    /**
     * The equals method is used to check for if coordinates match.
     * @return the row and column that matches.
     */
    @Override
    public boolean equals(Object other) {
        Position op = (Position) other;

        // they are equal when both coordinates match
        return this.row == op.row && this.col == op.col;
    }

    // returns whether a position is adjacent to another (or equal)
    /**
     * The isAdjacent method returns whether a position is adjacent to another, or equal.
     * @return true or false.
     */
    public boolean isAdjacent(Position other) {
        int rowdiff = Math.abs(this.row - other.row);
        int coldiff = Math.abs(this.col - other.col);

        if (rowdiff + coldiff < 2) {
            return true;
        } else {
            return false;
        }
    }
    /** The getRow method returns the row.
     * @return the row.
     */
    public int getRow() {
        return row;
    }
    /** The getCol methord returns the column.
     * @return the column.
     */
    public int getCol() {
        return col;
    }
}

