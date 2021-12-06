// Enemy.java

import java.util.Random;
import ansi_terminal.*;
/**
 * The enemy class extends character, and instantiates an enemy object.
 * @param name is the name of the enemy.
 * @param damage is the current damage.
 * @param protection is the current level of protection.
 * @param rng is its randomness in the pathing.
 * @param battleActive is a bool if it is in a battle or not.
 */
public class Enemy extends Character {
    private String name;
    private int damage;
    private int protection;
    private static Random rng;
    private boolean battleActive;
    /**
     * The enemy constructor creates an enemy object.
     * @param name is the name of the enemy.
     * @param row is the row that the enemy is on.
     * @param col is the column that the enemy is on.
     * @param hp is the health points of the enemy.
     * @param damage is the current damage.
     * @param protection is the current level of protection.
     */
    public Enemy(String name, int row, int col, int hp, int damage, int protection) {
        super(row, col, '*', Color.RED, hp);
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.battleActive = false;
        rng = new Random();
    }
    /**
     * The method getDamage is an override and returns damage.
     * @return damage is the damage that is returned.
     */
    @Override
    public int getDamage() {
        return damage;
    }
    /**
     * The method getProtection is an override and returns the protection.
     * @return protection is the protection value that is returned.
     */
    @Override
    public int getProtection() {
        return protection;
    }
    /**
     * The method getName is an override and returns the name of the enemy.
     * @return name is the name of the enemy that is returned.
     */
    @Override
    public String getName() {
        return name;
    }
   /** The setBattleActive method sets the battle status of the enemy to active.
    */
    public void setBattleActive() {
        battleActive = true;
    }
    /**
     * The walk method moves the enemy around a room.
     * @param room is the room that the enemy is walking in.
     */
    // randomly move this enemy in the room
    public void walk(Room room) {
        // if a battle is active with this enemy, they DONT walk right after
        if (battleActive) {
            battleActive = false;
            return;
        }

        // loop forever until we move correctly
        while (true) {
            int choice = rng.nextInt(4);
            switch (choice) {
                case 0:
                    if (move(0, 1, room)) return;
                    break;
                case 1:
                    if (move(0, -1, room)) return;
                    break;
                case 2:
                    if (move(1, 0, room)) return;
                    break;
                case 3:
                    if (move(-1, 0, room)) return;
                    break;
            }
        }
    }
}


