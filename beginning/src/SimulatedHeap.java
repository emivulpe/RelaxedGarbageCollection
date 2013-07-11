import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Emi This class simulates the heap and keeps track of the object ids
 *         and the last event that happened to them as well as the total objects
 *         tried to be accessed ever
 * 
 */

public class SimulatedHeap implements EventHandler {

	private static SimulatedHeap theHeap = null;
	private int numEvents; // do I actually need it????
	private HashMap<String, ObjectEventRecord> objectStates;
	private Set<String> processedObjects;

	private SimulatedHeap() {
		numEvents = 0;
		objectStates = new HashMap<String, ObjectEventRecord>();
		processedObjects = new HashSet<String>();
	}

	public static SimulatedHeap getTheHeap() {
		if (theHeap == null) {
			theHeap = new SimulatedHeap();

		}
		return theHeap;
	}

	@Override
	public void handle(Event e) {
		processedObjects.add(e.getID());
		numEvents++;

		boolean existsInHeap = objectStates.get(e.getID()) != null;
		String currentEventID = e.getID();
		String currentEventStatus = e.getStatus();

		// free and status "A"
		if (!existsInHeap && currentEventStatus.equalsIgnoreCase("A")) {

			ObjectEventRecord record = new ObjectEventRecord(e);
			objectStates.put(currentEventID, record);
			e.setCheck("creation");

		}

		// free but not status "A"
		else if (!existsInHeap && !currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("not born");

		} else if (existsInHeap && !objectStates.get(currentEventID).isAlive()) {

			e.setCheck("dead");

		}

		// occupied and alive
		else if (existsInHeap && objectStates.get(currentEventID).isAlive()) {
			ObjectEventRecord record = objectStates.get(currentEventID);
			record.updateRecord(e);
			objectStates.put(currentEventID, record);
			e.setCheck("legal");
			System.out.println(objectStates.get(currentEventID).isAlive());

		}

		else if (existsInHeap && currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("created");

		}

	}

	public int getNumObjects() {
		return processedObjects.size();
	}

	public ObjectEventRecord getRecord(String oid) {
		return objectStates.get(oid);
	}

}
