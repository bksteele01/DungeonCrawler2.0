// ItemGenerator.java
// this class contains a static method for creating items randomly

import java.util.Random;
/**
 * The ItemGenerator class generates an item randomly.
 */
public class ItemGenerator {
	/**
	 * The generate method instantiates a new item.
	 */
	public static Item generate() {
		// TODO: replace this with your own code! 
		//can add it to the inventory.
		
		ItemType weapon = ItemType.Weapon;
		Item IronSword = new Item(weapon, "Iron Sword", 8, 50, 10);
		Item GoldenSword = new Item(weapon, "Golden Sword", 10, 75, 15);
		Item AllumSword = new Item(weapon, "Alluminum Sword", 6, 35, 8);
		Item BowandQuiver = new Item(weapon, "Bow & Quiver", 4, 45, 7);
		Item BattleAxe = new Item(weapon, "Battle Axe", 12, 55, 13);

		ItemType armor = ItemType.Armor;
		Item ChainChest = new Item(armor, "Chain Mail Chestplate", 8, 40, 30);
		Item IronChest = new Item(armor, "Iron Chestplate", 10, 50, 40);
		Item GoldenChest = new Item(armor, "Golden Chestplate", 12, 65, 50);
		Item ChainPants = new Item(armor, "Chain Mail Pants", 7, 40, 30);
		Item IronPants = new Item(armor, "Iron Pants", 9, 50, 40);
		Item GoldenPants = new Item(armor, "Golden Pants", 11, 65, 50);
		Item Helmet = new Item(armor, "Helmet", 5, 40, 20);

		ItemType other = ItemType.Other;
		Item Bandage = new Item(other, "Bandage", 1, 2, 0);
		Item Wrench = new Item(other, "Wrench", 3, 10, 5);
		Item Bread = new Item(other, "Bread Loaf", 2, 4, 0);
		Item Water = new Item(other, "Water Flask", 3, 2, 0);

		Item Initializer = new Item(other, "init", 0, 0, 0);
		int ItemPick = RandomNum();
		Item chosen = Initializer;

		switch (ItemPick){
			case 1:
				chosen = IronSword;
				break;
			case 2:
				chosen = GoldenSword;
				break;
			case 3:
				chosen = AllumSword;
				break;
			case 4:
				chosen = BowandQuiver;
				break;
			case 5:
				chosen = BattleAxe;
				break;
			case 6:
				chosen = ChainChest;
				break;
			case 7:
				chosen = IronChest;
				break;
			case 8:
				chosen = GoldenChest;
				break;
			case 9:
				chosen = ChainPants;
				break;
			case 10:
				chosen = IronPants ;
				break;
			case 11:
				chosen = GoldenPants;
				break;
			case 12:
				chosen = Helmet;
				break;
			case 13:
				chosen = Bandage;
				break;
			case 14:
				chosen = Wrench;
				break;
			case 15:
				chosen = Bread;
				break;
			case 16:
				chosen = Water;
				break;	

		}
		return chosen;
	}
        /**
	 * The RandomNum method generates a random number between one and sixteen to pick an item.
	 */
	public static int RandomNum(){

		//this method generates a random number between one and sixteen, and is used
		//to pick the object out of the list.

		Random random = new Random();
		int ItemPicker = random.nextInt(16);
		return(ItemPicker);
	}
}
