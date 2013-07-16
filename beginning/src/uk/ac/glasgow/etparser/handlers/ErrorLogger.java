package uk.ac.glasgow.etparser.handlers;

import java.io.OutputStreamWriter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import uk.ac.glasgow.etparser.events.Event;

public class ErrorLogger implements EventHandler {
	private final Logger logger = Logger.getLogger(ErrorLogger.class.getName());

	public ErrorLogger() {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setName("My appender");
		ca.setWriter(new OutputStreamWriter(System.err));
		ca.setLayout(new PatternLayout("%-5p [%t]: %m%n"));
		logger.addAppender(ca);

	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public void handle(Event e) {
		String currentObjectID= e.getObjectID();
		if (e.getCheck().equalsIgnoreCase("dead")) {
			logger.warn("The object with id " + currentObjectID					+ " is dead so you cannot update it!");

		} else if (e.getCheck().equalsIgnoreCase("not born")) {
			logger.warn("The object with id " + currentObjectID				+ " is not born so you cannot update it!");
		} else if (e.getCheck().equalsIgnoreCase("created")) {
			logger.warn("The object with id " + currentObjectID			+ " is already created so you cannot create it again!");

		}

	}

}
