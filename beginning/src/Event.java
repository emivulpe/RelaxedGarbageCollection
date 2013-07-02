import java.util.Scanner;


public class Event {
	

	public String id;
	public String allocationTime;
	public String status;
	
	
	
	public Event (String line){
		
		Scanner scanner=new Scanner (line);
		status=scanner.next();
		allocationTime=scanner.next();
		if (status.equalsIgnoreCase("A")||status.equalsIgnoreCase("R")||status.equalsIgnoreCase("D")){
		id=scanner.next();
		}
		else{
			scanner.next();
			id=scanner.next();
		}
		
		
	}
	
	

	
	public String toString(){
		return "status "+status+" id "+id;
	}

}
