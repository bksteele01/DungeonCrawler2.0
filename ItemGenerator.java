// ItemGenerator.java
// this class contains a static method for creating items randomly

import java.util.Random;

public class ItemGenerator {
	public static Item generate() {
		// TODO: replace this with your own code!
		//	if(Player.Position.getRow() == "i" && (Player.Position.getCol  == "i")) { 
		return new Item(ItemType.Other, "Vase", 10, 2, 0);
	}
	//	else {
	//	return new Item(ItemType.Weapon, "Steel Sword", 10, 10, 10);}

	}

