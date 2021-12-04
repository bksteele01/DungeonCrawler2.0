// Player.java

import ansi_terminal.*;
/**
 * The Player class extends character and is used for instantiating a new player.
 */
public class Player extends Character {
    private Inventory items;
    private String name;
    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 100);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // Basic and simple equipment that the player starts with 
        items.addAndEquip(new Item(ItemType.Weapon, "Table Knife", 5, 12, 7));
        items.addAndEquip(new Item(ItemType.Armor, "Dirty T-Shirt", 15, 20, 3));
        items.addAndEquip(new Item(ItemType.Armor, "Leather Helmet", 10, 20, 3));
    }
    /**
     * The method getDamage is used to retrieve the damage.
     * @return the player's updated strength.
     */
    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }
    /**
     * The getName method returns the player's name.
     * @return the player's name.
     */
    @Override
    public String getName() {
        return "Player";
    }
    /**
     * The getProtection method returns the protection of the player.
     * @return the protection of the player.
     */
    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }
    /**
     * The methord getInventory returns the inventory.
     * @return the inventory of the player.
     */
    public Inventory getInventory() {
        return items;
    }
}

