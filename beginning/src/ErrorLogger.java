import java.io.IOException;

//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class ErrorLogger implements EventHandler {
	private final Logger logger = Logger.getLogger(ErrorLogger.class.getName());


	//private FileHandler fh = null;
	//private Logger logger1;

	public ErrorLogger() {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setName("My appender");
		ca.setWriter(new OutputStreamWriter(System.err));
		ca.setLayout(new PatternLayout("%-5p [%t]: %m%n"));
		logger.addAppender(ca);
		//		try {
//
//
//			//fh = new FileHandler("logger.log");
//
//		}
//
//		catch (SecurityException | IOException e) {
//			e.printStackTrace();
//		}
//
////		Logger l = Logger.getLogger("");
////		fh.setFormatter(new SimpleFormatter());
////		l.addHandler(fh);
////		l.setLevel(Level.SEVERE);

	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public void handle(Event e) {
		String currentEventID = e.getID();
		if (e.getCheck().equalsIgnoreCase("dead")) {
			logger.warn("The object with id " + currentEventID
					+ " is dead so you cannot update it!");

		} else if (e.getCheck().equalsIgnoreCase("not born")) {
			logger.warn( "The object with id " + currentEventID
					+ " is not born so you cannot update it!");
		} else if (e.getCheck().equalsIgnoreCase("created")) {
			logger.warn("The object with id " + currentEventID
					+ " is already created so you cannot create it again!");

		}


	}

	@Override
	public int getNumObjects() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getTotalObjects() {
		return 0;
	}

	@Override
	public String finalReport(float totalObjects) {
		// TODO Auto-generated method stub
		return "";
	}

}
