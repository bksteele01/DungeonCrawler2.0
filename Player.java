// Player.java

import ansi_terminal.*;

public class Player extends Character {
    private Inventory items;

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

    @Override
    public String getName() {
        return "Player";
    }

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

    public Inventory getInventory() {
        return items;
    }
}

