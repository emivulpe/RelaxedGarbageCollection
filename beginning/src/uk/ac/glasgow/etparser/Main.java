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
			InputStream gzipStream = new GZIPInputStream(fileStream);
			ETParser parser = new ETParser(gzipStream);
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
			System.out.println("IOException" + io);
			System.exit(0);
		}

	}

}