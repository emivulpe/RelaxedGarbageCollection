import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ETParser {
	/**
	 * List of registered objects to handle events
	 */
	private List<EventHandler> handlers;
	/**
	 * This variable counts the lines read so far.
	 */
	private int lines;
	/**
	 * TODO
	 */
	private SimulatedHeap heap;

	public ETParser(InputStream input) {

		handlers = new ArrayList<EventHandler>();
		lines = 0;
		heap = SimulatedHeap.getTheHeap();
		EventFactory factory = new EventFactory();

		initialiseHandlers();
		Scanner inputScanner = new Scanner(input);

		while (inputScanner.hasNextLine()) {

			String nextLine = inputScanner.nextLine();
			lines++;
			System.out.println(nextLine + " nextline");
			Event event = factory.createEvent(nextLine);
			notifyHandlers(event);

		}

	}

	public int getLines() {
		return lines;
	}

	public void notifyHandlers(Event e) {
		for (EventHandler eh : handlers) {
			eh.handle(e);

		}
	}

	public void registerHandler(EventHandler eh) {
		handlers.add(eh);
	}


	public void printReport() {
		for (EventHandler eh : handlers) {
			if (eh instanceof EventReport) {
				System.out.println(((EventReport) eh).finalReport());

			}

		}
	}

	public void initialiseHandlers() {
		registerHandler(heap);
		EventHandler creation = new CountCreation();
		registerHandler(creation);
		EventHandler legal = new CountLegal();
		registerHandler(legal);
		EventHandler dead = new CountDead();
		registerHandler(dead);
		EventHandler multiple = new CountMultipleCreations();
		registerHandler(multiple);
		EventHandler notBorns = new CountNotBorn();
		registerHandler(notBorns);
		EventHandler logger = new ErrorLogger();
		registerHandler(logger);
		EventHandler livesize = new LiveSize();
		registerHandler(livesize);

	}

}
