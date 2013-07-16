package uk.ac.glasgow.etparser.handlers;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.etparser.ObjectEventRecord;
import uk.ac.glasgow.etparser.events.CreationEvent;
import uk.ac.glasgow.etparser.events.Event;

/**
 * 
 * @author Emi This class simulates the heap and keeps track of the object ids
 *         and the last event that happened to them as well as the total objects
 *         tried to be accessed ever
 * 
 */

public class SimulatedHeap implements EventHandler {
/**
 * This is analogous to the real heap.
 */
	private static SimulatedHeap theHeap = null;
	/**
	 * Measures time sequentially (1, 2, 3...).
	 */
	private int timeSequence; 
	/**
	 * Measures time as function of the allocated objects' sizes.
	 */
	private int timeSize;
	/**
	 * A hash map that keeps the object id and the last event 
	 * that happened to that object.
	 */
	private HashMap<String, ObjectEventRecord> objectStates;
	/**
	 * A set of all the objects tried to be accessed ever
	 * including the once that were not born.
	 */
	private Set<String> processedObjects;
	
    /**
     * Initializes the class variables. Private because of Singleton design pattern.
     */
	private SimulatedHeap() {
		timeSequence = 0;
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
		processedObjects.add(e.getObjectID());
		timeSequence++;

		boolean existsInHeap = objectStates.get(e.getObjectID()) != null;
		String currentObjectID = e.getObjectID();
		String currentEventStatus = e.getStatus();

		// free and status "A"
		if (!existsInHeap && currentEventStatus.equalsIgnoreCase("A")) {

			ObjectEventRecord record = new ObjectEventRecord(e);
			objectStates.put(currentObjectID, record);
			e.setCheck("creation");
			CreationEvent ce=(CreationEvent) e;
			timeSize+=ce.getSize();

		}

		// free but not status "A"
		else if (!existsInHeap && !currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("not born");

		} else if (existsInHeap && !objectStates.get(currentObjectID).isAlive()) {

			e.setCheck("dead");

		}

		// occupied and alive
		else if (existsInHeap && objectStates.get(currentObjectID).isAlive()) {
			ObjectEventRecord record = objectStates.get(currentObjectID);
			record.updateRecord(e);
			objectStates.put(currentObjectID, record);
			e.setCheck("legal");
			System.out.println(objectStates.get(currentObjectID).isAlive());

		}

		else if (existsInHeap && currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("created");

		}
		System.out.println(getNumObjects()+" total objects in heap"+processedObjects);
	}

	public int getNumObjects() {
		return processedObjects.size();
	}

	public ObjectEventRecord getRecord(String oid) {
		return objectStates.get(oid);
	}

	public int getTimeSequence(){
		return timeSequence;
	}
	
	public int getTimeSize(){
		return timeSize;
	}
}
