public class ObjectEventRecord {
	private Event lastEvent;
	private int numEvents, numUpdates, numMethodCalls;
	private boolean isAlive = false;

	public ObjectEventRecord(final Event e) {
		isAlive = true;
		lastEvent = e;
		numEvents = 1;
		numUpdates = 0;
		numMethodCalls = 0;

	}

	public final int getNumEvents() {
		return numEvents;
	}

	public final int getNumUpdates() {
		return numUpdates;
	}

	public final int getNumMethodCalls() {
		return numMethodCalls;
	}

	public final Event getLastEvent() {
		return lastEvent;
	}

	public final void updateRecord(final Event e) {
		lastEvent = e;
		numEvents++;
		String currentEventStatus = e.getStatus();
		if (currentEventStatus.equalsIgnoreCase("U")) {
			numUpdates++;
		}
		if (currentEventStatus.equalsIgnoreCase("M")) {
			numMethodCalls++;
		}
		if (currentEventStatus.equalsIgnoreCase("D")) {
			isAlive = false;
		}

	}

	public final boolean isAlive() {
		return isAlive;
	}

	public final String toString() {
		return "Event: " + lastEvent;
	}
}
