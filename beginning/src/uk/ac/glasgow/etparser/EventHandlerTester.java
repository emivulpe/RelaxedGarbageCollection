package uk.ac.glasgow.etparser;

import uk.ac.glasgow.etparser.events.Event;
import uk.ac.glasgow.etparser.handlers.EventHandler;
import uk.ac.glasgow.etparser.handlers.EventReport;

public class EventHandlerTester implements EventHandler, EventReport {
	public boolean handled;
	public String report;

	public EventHandlerTester() {
		handled = false;
		report = "";
	}

	@Override
	public void handle(Event e) {
		handled = true;

	}

	public boolean getHandled() {
		return handled;
	}

	public String getReport() {
		return report;
	}

	@Override
	public String finalReport() {
		report = "well done!";
		return report;
	}

}
