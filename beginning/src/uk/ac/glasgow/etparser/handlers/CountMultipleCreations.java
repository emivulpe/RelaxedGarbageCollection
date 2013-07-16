package uk.ac.glasgow.etparser.handlers;
import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.etparser.events.Event;

public class CountMultipleCreations implements EventHandler, EventReport {
	private Set<String> multiples;

	public CountMultipleCreations() {
		multiples = new HashSet<String>();
	}

	public Set<String> getMultiples() {
		return multiples;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("created")) {
			multiples.add(e.getObjectID());
			System.out.println("Object with id " + e.getObjectID()
					+ " has been created more than once.");
		}

	}

	@Override
	public String finalReport() {
		// TODO Auto-generated method stub
		return (float) multiples.size()
				/ SimulatedHeap.getTheHeap().getNumObjects() * PERCENTAGE
				+ " % objects were created more than once";
	}

}
