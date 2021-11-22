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

	
		
	
	public void Restore(String SaveName){
		Scanner input = null;
		try{
			FileInputStream file = new FileInputStream(SaveName + ".txt");
			input = new Scanner(file);
		
			while(input.hasNextLine()){
				
				
			
			}

		
		}catch(FileNotFoundException e){
			System.out.print("File not found");

		
		}
		
	

	}









}
