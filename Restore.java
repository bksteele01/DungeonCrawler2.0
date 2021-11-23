import ansi_terminal.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Restore{
	private int hp;
	private int rCol;
	private int rRow;
	private int Room;
	private Inventory items;
	private Player player;
	public String SaveName;

	private String[] data = new String[2];
	private int[] data2 = new int[4];
	

	
	public void Restore(String SaveName){
		Scanner input = null;
		try{
			FileInputStream file = new FileInputStream(SaveName + ".txt");
			input = new Scanner(file);
		
			for(int i =0; i < 4; i++){
				
				if(!input.hasNextInt()){
					data[i] = input.nextLine();
				
				}
				else{
					data2[i] = input.nextInt();	
						
				}
				
			
			}
			input.close();
			

		}catch(FileNotFoundException e){
			System.out.print("File not found");

		
		}
		
	}
	public int RoomRestore(){
	
		return data2[3];

	
	}
	
	public String SetInventory(){
	
		return data[0];
	}

	public int HpRestore(){
		
		return data2[0];
	}

	public int colRestore(){
	
		return data2[1];
	}

	public int rowRestore(){
	
		return data2[2];
	}

	

}
