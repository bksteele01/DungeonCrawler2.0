import ansi_terminal.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
/**
 * The restore class is used to load a save file.
 */
public class Restore{
	private int hp;
	private int rCol;
	private int rRow;
	private int Room;
	private Inventory items;
	private Player player;
	public String SaveName;
	private int[] data2 = new int[4];
	public int i = 0;
	public String itemConv ="";

	public void Restore(String SaveName, Player player){
		this.player = player;
		
		Scanner input = null;
		try{
			FileInputStream file = new FileInputStream(SaveName + ".txt");
			input = new Scanner(file);
								
				while(input.hasNextLine()){
					
					if(!input.hasNextInt()){
						
						itemConv = input.nextLine();
						Item newitem = player.getInventory().ItemConverter(itemConv);
						this.player.getInventory().add(newitem);

					}
					else{
					
						data2[i] = input.nextInt();	
						
					}
					i++;
				}	
			
			input.close();
			

		}catch(FileNotFoundException e){
			System.out.print("File not found");
			
		
		}
		
	}
	
	
        /**
	 * The RoomRestore method returns the last room from the save.
	 * @return the last room from the save.
	 */
	public int RoomRestore(){
		return data2[3];	
	}
	
	/**
	 * The HpRestore methord returns the hp of the player.
	 * @return the hp of the player.
	 */
	public int HpRestore(){
		
		return data2[0];
	}
	/**
	 * The colRestore method returns the column of the player.
	 * @return the column of the player.
	 */
	public int colRestore(){
	
		return data2[1];
	}
        /**
	 * The rowRestore method returns the row of the player.
	 * @return the row of the player.
	 */
	public int rowRestore(){
	
		return data2[2];
	}

	

}
