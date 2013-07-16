package uk.ac.glasgow.etparser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;

import uk.ac.glasgow.etparser.events.Event;
import uk.ac.glasgow.etparser.handlers.ErrorLogger;

public class Main {

	public static void main(final String[] args) {

		ErrorLogger logger = new ErrorLogger();
		long startOfProcess = System.currentTimeMillis();
		try {
			InputStream fileStream = new FileInputStream(args[0]);
			//InputStream gzipStream = new GZIPInputStream(fileStream);
			ETParser parser = new ETParser(fileStream);
			parser.processFile();
			parser.printReport();
			fileStream.close();
			long endOfProcess = System.currentTimeMillis();
			long timeTakenInMillisecs = endOfProcess - startOfProcess;
			long timeTakenInSeconds = timeTakenInMillisecs / 1000;
			long linesPerSecond = parser.getLines() / timeTakenInSeconds;
			

			System.out.println("The program reads " + linesPerSecond
					+ " lines per second");
		}

		catch (IOException io) {
			System.out.println("IOException"+io);
			System.exit(0);
		}

	}

	
	

	public static boolean testing(final String path) {
		Scanner inputScanner = null;

		try {
			InputStream fileStream = new FileInputStream(path);
			InputStream gzipStream = new GZIPInputStream(fileStream);
			inputScanner = new Scanner(gzipStream);
		}

		catch (IOException io) {
			System.out.println("IOException");
			System.exit(0);
		}

		HashMap<String, ObjectEventRecord> hash = new HashMap();

		while (inputScanner.hasNextLine()) {

			String nextLine = inputScanner.nextLine();

			System.out.println(nextLine + " nextline");
			EventFactory factory=new EventFactory();

			Event event = factory.createEvent(nextLine);
			String currentEventID = event.getObjectID();
			String currentEventStatus = event.getStatus();

			boolean existsInHashtable = hash.get(currentEventID) != null;

			// free and status "A"
			if (!existsInHashtable && currentEventStatus.equalsIgnoreCase("A")) {

				ObjectEventRecord record = new ObjectEventRecord(event);
				hash.put(currentEventID, record);

			}
			// occupied but dead
			else if (existsInHashtable && !hash.get(currentEventID).isAlive()) {
				System.out.println("dead");
				return false;

			}

			// free but not status "A"
			else if (!existsInHashtable
					&& !currentEventStatus.equalsIgnoreCase("A")) {
				System.out.println("beforeborn");
				return false;

			}

			// occupied and alive
			else if (existsInHashtable && hash.get(currentEventID).isAlive()) {

				ObjectEventRecord record = hash.get(currentEventID);
				record.updateRecord(event);
				hash.put(currentEventID, record);

			}

			else if (existsInHashtable && currentEventStatus.equalsIgnoreCase("A")) {
				System.out.println("multiple");
				return false;

			}



		}
		return true;
	}

}
