package uk.ac.glasgow.etparser.handlers;

import org.apache.log4j.Logger;


public class StatisticsLogger {


	private final Logger logger;


	public StatisticsLogger() {
		logger = Logger.getLogger("stats");
	}


	public Logger getLogger() {
		return logger;
	}

}
