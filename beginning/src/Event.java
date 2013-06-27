import java.util.Scanner;

public class Event {
	
	//make them private and create getters and setters
	public String id;
	//public enum type {allocation,update,death,other};
	public String allocationTime;
	public Time deathTime;
	public int size;
	public String status;
	
	
	public Event (String id, String status,int size){
		this.id=id;
		this.status=status;
		//this.allocationTime=at;
		this.size=size;
		
	}
	
	public Event (Scanner scanner){
		
		status=scanner.next();
		//determineStatus(type);
		//these integers are hexadecimal so translate into integers?
		allocationTime=scanner.next();
		if (status.equalsIgnoreCase("A")||status.equalsIgnoreCase("R")||status.equalsIgnoreCase("D")){
		id=scanner.next();
		}
		else{
			scanner.next();
			id=scanner.next();
		}
		//size=scanner.next();
		
		
	}
	
	public void determineStatus(String type){
		if (type.equalsIgnoreCase("A")){
			status="allocation";
		}
		else if (type.equalsIgnoreCase("U")){
			status="update";
		}
		else if (type.equalsIgnoreCase("D")){
			status="death";
		}
		else {
			status="other";
		}
	}
	
	
	//public void determineId(String id)
	
	public String toString(){
		return "status "+status+"id "+id+"size "+size+"time "+allocationTime;
	}

}
