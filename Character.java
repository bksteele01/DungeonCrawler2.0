// Character.java

import java.util.ArrayList;

import ansi_terminal.*;
/**
 * The Character class extends entity and allows you to instantiate a character object
 * and set its personal stats.
 * @param hp is the health points of the character.
 */
public abstract class Character extends Entity {
    // the characters health points
    private int hp;
    /**
    * The character constructor creates a new character object.
    * @param row sets the row of the character.
    * @param col sets the column of the character.
    * @param display sets the display of the character.
    * @param color sets the color of the chracter.
    * @param hp sets the health points of the character.
    */
    public Character(int row, int col, char display, Color color, int hp) {
        super(row, col, display, color);
        this.hp = hp;
    }

    // get the hp, damage, protection and name of character
    /**
     * The getHealth method returns the hp of the character.
     * @return hp is the health points of the character.
     */
    public int getHealth() {
        return hp;
    }
    /**
     * The setHealth method sets the health of the character.
     * @param hp is the health points of the character.
     */
    public void setHealth(int hp){
	    this.hp = hp;
    }
    /**
     * The abstract method for getDamage.
     */
    public abstract int getDamage();
    /**
     * The abstract method for getProtection.
     */
    public abstract int getProtection();
    /**
     * The abstract method for getName.
     */
    public abstract String getName();
	
    // do damage to another player, returns if they died
    /**
     * The dealDamage method allows a character to deal damage to another character.
     * @param other identifies the other character being damaged.
     * @param room indentifies the room the character is in.
     */
    private boolean dealDamage(Character other, Room room) {
        // this character does damage to the other character
        int damageDone = getDamage() - other.getProtection();

        // prevent negative damage
        if (damageDone < 0) {
            damageDone = 0;
        }

        // actually damage them
        other.hp -= damageDone;

        // prevent negative hp
        if (other.hp < 0) {
            other.hp = 0;
        }

        // print the info on this
        Terminal.warpCursor(room.getRows(), 0);
        if (other.hp > 0) {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", leaving " + other.hp + " health.\n\r");
            return false;
        } else {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", killing them.\n\r");
            return true;
        }
    }

    // this method performs one round of battle between two characters
    // return false if the player has died aas a result
    /**
     * The fight method is used to initiate and continue a fight.
     * @param other is the other character in the fight.
     * @param room is the room that the fight is taking place in.
     * @param enemies are the enemies that are taking place in the fight.
     */
    public boolean fight(Character other, Room room, ArrayList<Enemy> enemies) {
        // do damage to them first
        boolean killed = dealDamage(other, room);
        if (killed) {
            enemies.remove(other);
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();

        // don't allow dead enemies to fight back
        if (killed) {
            return true;
        }

        // now take damage from them
        if (other.dealDamage(this, room)) {
            return false;
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();
        return true;
    }
}

