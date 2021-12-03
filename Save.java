// this class will save for right now but maybe add a load methods
//

import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.IOException;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Save {
	private int hp;
	private int pCol;
	private int pRow;
	public	String SaveName;
	private boolean Pass = true;
	private int Room;
	private Inventory items;
	
	private Player player;	

	
	public Save(int hp, int pCol, int pRow, String SaveName, int Room, Player player){
		this.hp = hp;
		this.pCol = pCol;
		this.pRow = pRow;
		this.SaveName = SaveName;
		this.Room = Room;
		this.player = player;
		
	}
	
	public void SaveMaker(){
		try{
			if(Pass == true){
				File savef = new File(this.SaveName + ".txt");
				
				PrintWriter out = new PrintWriter(this.SaveName + ".txt");
				
						
				out.println(this.hp);
				out.println(this.pCol);
				out.println(this.pRow);
				out.println(this.Room);

				for(int i = 0; i < this.player.getInventory().Size(); i++){
				Item item = this.player.getInventory().getinv(i);
					ItemType type = item.getType();
					String itemName = item.getName();
					int Weight = item.getWeight();
					int Value = item.getValue();
					int Strength = item.getStrength();

					out.println(type + "," + itemName + "," + Weight + "," + Value + "," + Strength);
				}

				out.close();

				
			}
		
		}catch (IOException e){
			System.out.print("An Error Occured");
		
		}
		
	
	}
		


}
