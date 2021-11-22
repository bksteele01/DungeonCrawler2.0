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


public class Save {
	private int hp;
	private int pCol;
	private int pRow;
	public	String SaveName;
	private boolean Pass = true;
	private int Room;
	private Inventory items;
	
	private Player player;	

	
	public Save(int hp, int pCol, int pRow, String SaveName, int Room, Inventory items){
		this.hp = hp;
		this.pCol = pCol;
		this.pRow = pRow;
		this.SaveName = SaveName;
		this.Room = Room;
		this.items = items;
		
			
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
				out.println(this.items.toString());
				out.close();

				
			}
		
		}catch (IOException e){
			System.out.print("An Error Occured");
		
		}
		
	
	}


	public void Restore(){
		player.setPosition(this.pRow, this.pCol);
		player.setHealth(this.hp);
			
	}
		
	public int RoomRestore(){
		
		return this.Room;
	}

}
