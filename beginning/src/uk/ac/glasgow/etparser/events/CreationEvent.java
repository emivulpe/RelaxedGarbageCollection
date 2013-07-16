package uk.ac.glasgow.etparser.events;
import java.util.Scanner;


public class CreationEvent extends Event{
	
	private int size;
	
	public CreationEvent(String line){
		super (line);
		Scanner scan=new Scanner(line);
		scan.next();
		scan.next();
		scan.next();
		String s=scan.next();
		size = Integer.parseInt(s.trim(), 16); 
		
		status = "A";
	}
	
	public int getSize(){
		return size;
	}

}
