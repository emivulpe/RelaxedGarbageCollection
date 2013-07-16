package uk.ac.glasgow.etparser.handlers;
import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.etparser.events.Event;

public class CountLegal implements EventHandler, EventReport {
	private Set<String> legals;

	public CountLegal() {
		legals = new HashSet<String>();
	}

	public Set<String> getLegal() {
		return legals;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("legal")) {
			legals.add(e.getObjectID());
			System.out.println("Object with id " + e.getObjectID()
					+ " has been updated.");
		}

	}



	@Override
	public String finalReport() {

		return  (float)legals.size() /SimulatedHeap.getTheHeap().getNumObjects() * PERCENTAGE + " % objects were updated successfully";
	}
	

}
