import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ErrorLogger implements EventHandler {
	private final Logger logger = Logger.getLogger(ErrorLogger.class.getName());
	private FileHandler fh = null;
	private Logger logger1;

	public ErrorLogger() {
		try {

			fh = new FileHandler("logger.log");

		}

		catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		Logger l = Logger.getLogger("");
		fh.setFormatter(new SimpleFormatter());
		l.addHandler(fh);
		l.setLevel(Level.SEVERE);

	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	public void handle(Event e) {
		String currentEventID = e.getID();
		if (e.getCheck().equalsIgnoreCase("dead")) {
			logger.log(Level.SEVERE, "The object with id " + currentEventID
					+ " is dead so you cannot update it!");

		} else if (e.getCheck().equalsIgnoreCase("not born")) {
			logger.log(Level.SEVERE, "The object with id " + currentEventID
					+ " is not born so you cannot update it!");
		} else if (e.getCheck().equalsIgnoreCase("created")) {
			logger.log(Level.SEVERE, "The object with id " + currentEventID
					+ " is already created so you cannot create it again!");

		}

	}

	@Override
	public int getNumObjects() {
		// TODO Auto-generated method stub
		return 0;
	}

}
