// this class will save for right now but maybe add a load methods
//

import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.IOException;
import ansi_terminal.*;


public class Save {

	private String name;
	private int hp;
	private int pCol;
	private int pPos;
	private static int SaveCounter;
	private String SaveName;
	private boolean Pass = true;
	private int Room;

	
	public Save(String name, int hp, int pCol, int pPos, String SaveName, int Room){
		this.hp = hp;
		this.name = name;
		this.pCol = pCol;
		this.pPos = pPos;
		this.SaveName = SaveName;
		this.Room = room;

		
	}
	
	public void SaveMaker(){
		try{
			if(Pass == true){
				SaveCounter++;
				File savef = new File(this.SaveName + ".txt");
								
				

				if(savef.createNewFile()){
					System.out.print("File created " + savef.getName());
				}
				else{
					System.out.print("File Already Created ");
				
				}
			}
		
		}catch (IOException e){
			System.out.print("An Error Occured");
		
		}
		
	
	}
	public void Restore(){
		
	
	
	}
	

}
