// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;
/**
 * EnemyGenerator is a constructor that generates enemies.
 */
public class EnemyGenerator {
	/**
	 * The generate method generates an enemy object.
	 * @param row is the row that the enemy is generated on.
	 * @param col is the column that the enemy is generated on.
	 */
	public static Enemy generate(int row, int col) {
		//hp damage protection
		//These are the enemies that will randomly generate  
		Enemy Goblin = new Enemy("Goblin", row, col, 20, 5, 3);
		Enemy Vampire = new Enemy("Vampire", row, col, 21, 8, 4);
		Enemy Giant = new Enemy("Giant", row, col, 23, 15, 6);
		Enemy Werewolf = new Enemy("Werewolf", row, col, 22, 10, 5);

		Enemy Initializer = new Enemy("Ghost", row, col, 0, 0, 0);
		int EnemyPick = RandomNum();
		Enemy chosen = Initializer;

		switch (EnemyPick){
			case 1:
				chosen = Goblin;
				break;
			case 2:
				chosen = Vampire;
				break;
			case 3:
				chosen = Giant;
				break;
			case 4:
				chosen = Werewolf;
				break;
		}
		return chosen;
	}
	/**
	 * This method generates a random number between one and sixteen and is used to pick
	 * the object out of the list.
	 */
	public static int RandomNum(){

		//this method generates a random number between one and sixteen, and is used
		//to pick the object out of the list.

		Random random = new Random();
		int EnemyPicker = random.nextInt(4);
		return(EnemyPicker);
	}


}

