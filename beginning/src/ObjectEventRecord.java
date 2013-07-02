
public class ObjectEventRecord {
	private Event lastEvent;
	private int numEvents, numUpdates,numMethodCalls;
	boolean isAlive=false;
	
	
	
	public ObjectEventRecord(Event e){
		isAlive=true;
		lastEvent=e;
		numEvents=1;
		numUpdates=0;
		numMethodCalls=0;

		
	}
	
	
	public int getNumEvents(){
		return numEvents;
	}
	
	public int getNumUpdates(){
		return numUpdates;
	}
	
	public int getNumMethodCalls(){
		return numMethodCalls;
	}
	
	public Event getLastEvent(){
		return lastEvent;
	}
	
	public void updateRecord(Event e){
		lastEvent=e;
		numEvents++;
		if (e.status.equalsIgnoreCase("U")){
			numUpdates++;
		}
		if (e.status.equalsIgnoreCase("M")){
			numMethodCalls++;
		}
		if (e.status.equalsIgnoreCase("D")){
			isAlive=false;
		}
		
		
		
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public String toString(){
		return "Event: "+lastEvent;
	}
}
