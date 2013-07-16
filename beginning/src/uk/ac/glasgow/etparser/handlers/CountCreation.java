package uk.ac.glasgow.etparser.handlers;
import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.etparser.events.Event;

public class CountCreation implements EventHandler, EventReport {
	private Set<String> created;

	public CountCreation() {
		created = new HashSet<String>();
	}

	public Set<String> getCreated() {
		return created;
	}

	@Override
	public void handle(Event e) {

		if (e.getCheck().equalsIgnoreCase("creation")) {
			created.add(e.getObjectID());
			System.out.println("Object with id " + e.getObjectID()
					+ " has been created.");
		}

	}

	@Override
	public String finalReport() {
		return (float) created.size()
				/ SimulatedHeap.getTheHeap().getNumObjects() * PERCENTAGE
				+ " % objects were created successfully";
	}

}
