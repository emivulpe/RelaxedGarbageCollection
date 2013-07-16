package uk.ac.glasgow.etparser.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.etparser.ObjectEventRecord;
import uk.ac.glasgow.etparser.events.CreationEvent;
import uk.ac.glasgow.etparser.events.Event;

/**
 * This class simulates the heap and keeps track of the object ids and the last
 * event that happened to them as well as the total objects tried to be accessed
 * ever.
 * 
 * @author Emi
 * @version 1.0
 * 
 * 
 */

public class SimulatedHeap implements EventHandler {
	/**
	 * This is analogous to the real heap memory.
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
	 * Measures time in terms of method entry and method exit.
	 */
	private int timeMethod;
	/**
	 * A hash map that keeps the object id and the last event that happened to
	 * that object.
	 */
	private HashMap<String, ObjectEventRecord> objectStates;
	/**
	 * A set of all the objects tried to be accessed ever including the once
	 * that were not born.
	 */
	private Set<String> processedObjects;

	/**
	 * Initializes the class variables. Private because of Singleton design
	 * pattern.
	 */
	private SimulatedHeap() {
		timeSequence = 0;
		timeSize = 0;
		timeMethod = 0;
		objectStates = new HashMap<String, ObjectEventRecord>();
		processedObjects = new HashSet<String>();
	}

	/**
	 * 
	 * @return an instance of the heap. It is global.
	 */
	public static SimulatedHeap getTheHeap() {
		if (theHeap == null) {
			theHeap = new SimulatedHeap();

		}
		return theHeap;
	}

	/**
	 * Checks whether the event is legal and if it is, it updates the event
	 * record in the heap and updates the time.
	 * @param e
	 *            instance of the Event super class
	 */
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
			CreationEvent ce = (CreationEvent) e;
			timeSize += ce.getSize();

		}

		// free but not status "A"
		else if (!existsInHeap && !currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("not born");

			// dead
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
			if (currentEventStatus.equalsIgnoreCase("M")
					|| currentEventStatus.equalsIgnoreCase("E")) {
				timeMethod++;
			}

		}

		else if (existsInHeap && currentEventStatus.equalsIgnoreCase("A")) {

			e.setCheck("created");

		}
		System.out.println(getNumObjects() + " total objects in heap"
				+ processedObjects);
	}

	/**
	 * 
	 * @return the nmber of objects tried to be accessed ever.
	 */
	public int getNumObjects() {
		return processedObjects.size();
	}

	/**
	 * 
	 * @param oid the id of the object we want to access in the heap.
	 * @return the last event record for the given object.
	 */
	public ObjectEventRecord getRecord(String oid) {
		return objectStates.get(oid);
	}
	
	/**
	 * 
	 * @return the current time expressed as sequence.
	 */
	public int getTimeSequence() {
		return timeSequence;
	}

	/**
	 * 
	 * @return the current time expressed as allocated object size.
	 */
	public int getTimeSize() {
		return timeSize;
	}

	/**
	 * 
	 * @return  the current time expressed in term of method entry and exit.
	 */
	public int getTimeMethod() {
		return timeMethod;
	}
}
