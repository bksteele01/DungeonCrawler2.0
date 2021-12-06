// Inventory.java
// allows for storing some number of items for the player
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

import ansi_terminal.*;
/**
 * The inventory class is responsible for storing items for the player.
 */
public class Inventory {
    // the actual list of items
    private ArrayList<Item> items;

    // which item is equipped, if any
    private Item equippedArmor;
    private Item equippedWeapon;

    // the max weight limit for the player here
    private int maxWeight;
    /**
     * The Inventory constructor is responsible for creating the inventory.
     * @param maxWeight is the maximum weight the player can carry.
     */
    public Inventory(int maxWeight) {
        items = new ArrayList<Item>();
        this.maxWeight = maxWeight;
    }
	// how many items the inventory has
    /**
     * The Size method returns the items size.
     * @return the size of the items.
     */
    public int Size(){
    	return items.size();
    }
     
    // returns a individual specific item
    /**
     * The getinv method returns the player's inventory.
     * @param i is the inventory of the player.
     * @return the players inventory.
     */
    public Item getinv(int i) {
    	
    	return items.get(i);
    
    }
    // erase Array used for loading new items
    /**
     * The eraseArray method is used for loading new items.
     */
    public void eraseArray(){
    	items.clear();
    
    }

    //This will convert the items frmo the text file to an item object
    /**
     * The ItemConverter method will convert items from the text file to an item object.
     * @param itemConv is the parameters of the item.
     */
    public Item ItemConverter(String itemConv){
    //the string itemConv should be for example
    //Weapon,Golden Sword,10,75,15
    	ItemType type = null;
	String newName = "";
	int newWeight = 0;
	int newValue = 0;
	int newStrength = 0;
    	String[] output = itemConv.split(",");
	
	for(int x = 0; x < output.length-1; x++){
		
		if(x == 0){

    			if(output[0].contains("Weapon")){
    				type = ItemType.valueOf("Weapon");
    			}
			else if(output[0].contains("Armor")){
				type = ItemType.valueOf("Armor");
			}
			else if(output[0].contains("Other")){
				type = ItemType.valueOf("Other");
			}
		}
		else if(x == 1){
			newName = output[x];
		
		}
		else if(x == 2){
			newWeight = Integer.parseInt(output[x]);

		}
		else if(x == 3){
		
			newValue = Integer.parseInt(output[x]);

		}
		else if(x == 4){
		
			newStrength = Integer.parseInt(output[x]);
		}


	}

    	Item newItem = new Item(type, newName, newWeight, newValue, newStrength);
	
	return newItem;
    }

    // returns true on success, false when full
    /**
     * The add method returns true on success, and false when the player has a full inventory.
     * @param item is the item being added to the inventory.
     */
    public boolean add(Item item) {
        if ((item.getWeight() + totalWeight()) > maxWeight) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }
    
    // this method not only adds the item, but equips it into the correct slot
    // it is used for setting up the player's starting gear
   /**
    * The addAndEquip method adds the item and equips it into the correct slot, for the starting gear.
    * @param item is the item being equipped.
    */
    public void addAndEquip(Item item) {
        items.add(item);

        if (item.getType() == ItemType.Weapon) {
            equippedWeapon = item;
        } else if (item.getType() == ItemType.Armor) {
            equippedArmor = item;
        }
    }

    // get the equipped weapon and armor
    /**
     * The method getEquippedWeapon gets the equipped weapon.
     * @return the equipped weapon.
     */
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }
    /** 
     * The method getEquippedArmor gets the equipped armor.
     * @return the equipped armor.
     */
    public Item getEquippedArmor() {
        return equippedArmor;
    }

    // returns the total weight of all items stored
    /**
     * The totalWeight method stores the total weight of all the players items.
     * @return the total weight.
     */
    public int totalWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    // print all of the items in the list, that match they given type (can be null)
    // returns the number of items matching they type
    /**
     * The print method prints all of the items in the list, and if they match the given type, then
     * returns the number of items matching the type.
     * @param filter clears the terminal so we can print over all else.
     * @return num returns the number.
     */
    private int print(ItemType filter) {
        // clear the terminal so we print over all else
        Terminal.clear();

        // print a heading row
        // the numbers and junk are to make it print in nice columns
        Terminal.setForeground(Color.RED);
        System.out.printf("%-4s %-40s %-8s %-8s %-8s\n\r", "No.", "Name", "Weight", "Value", "Strength");
        Terminal.reset();

        // print each item out
        int num = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                System.out.printf("%-4d %-40s %-8s %-8s %-8s", num + 1, i.getName(), i.getWeight(), i.getValue(), i.getStrength());

                // tell them if this thing is equipped
                if (i == equippedArmor) {
                    System.out.print(" (equipped armor)");
                } else if (i == equippedWeapon) {
                    System.out.print(" (equipped weapon)");
                }
                System.out.print("\n\r");

                num++;
            }
        }

        return num;
    }

    // stay here until the user is ready to go back
    /**
     * The method pressAnyKey asks the user to press any key to go back.
     */
    public void pressAnyKey() {
        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    // print all of the items in the list
    /**
     * The print method prints all of the items in the list.
     */
    public void print() {
        print(null);
        pressAnyKey();
    }

    // drop an item from the inventory, return what was dropped
    /**
     * The drop method drops an item from the inventory and returns what was dropped.
     * @return toDrop returns the item to drop.
     */
    public Item drop() {
        Item toDrop = pickItem(null);
        if (toDrop != null) {
            // if we're dropping our equipped stuff, remove those too!
            if (equippedWeapon == toDrop) {
                equippedWeapon = null;
            } else if (equippedArmor == toDrop) {
                equippedArmor = null;
            }

            items.remove(toDrop);
        }

        if (toDrop != null) {
            System.out.print("You dropped the " + toDrop.getName() + "...\n\r");
        } else {
            System.out.print("You dropped nothing...\n\r");
        }

        pressAnyKey();
        return toDrop;
    }

    // equip something
    /**
     * The equip method equips something from the inventory.
     * @param type is the type of item.
     * @return returns the item type.
     */
    private Item equip(ItemType type) {
        Item thing = pickItem(type);
        if (thing != null) {
            System.out.print("You equipped the " + thing.getName() + "\n\r");
        } else {
            System.out.print("You equipped nothing...\n\r");
        }
        pressAnyKey();
        return thing;
    }

    // equip a weapon
    /**
     * The equipWeapon method equips a weapon.
     */
    public void equipWeapon() {
        equippedWeapon = equip(ItemType.Weapon);
    }

    // equip a piece of armor
    /**
     * The equipArmor method equips a piece of armor.
     */
    public void equipArmor() {
        equippedArmor = equip(ItemType.Armor);
    }

    // a method which allows users to choose an item
    // this is private - only called by drop and equip
    /**
     * The pickItem method allows a user to pick an item.
     * @param filter filters the item types.
     */
    private Item pickItem(ItemType filter) {
        // print all the matching items
        int options = print(filter);

        if (options == 0) {
            System.out.print("You have no appropriate items!\n\r");
            return null;
        }

        // give them a cancel option as well
        System.out.print((options + 1) + "    None\n\r");

        // get their choice, only allowing ints in the correct range
        int choice = 0;
        do {
            String entry = Terminal.getLine("Select an item: ");
            try {
                choice = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 0 || choice > options);

        // go through and skip items until we reach this one
        int realIndex = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                if (choice == 0) {
                    break;
                }
                choice--;
            }
            realIndex++;
        }

        // return the thing they chose
        if (realIndex < 0 || realIndex >= items.size()) {
            return null;
        } else {
            return items.get(realIndex);
        }
    }
}

