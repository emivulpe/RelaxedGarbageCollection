import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ETParser {
	private HashMap<String, ObjectEventRecord> hash;
	private List<EventHandler> handlers;
	private int lines;

	public ETParser(InputStream gzipStream) {
		handlers = new ArrayList();
		hash = new HashMap<String, ObjectEventRecord>();
		lines=0;

		initialiseHandlers();
		Scanner inputScanner = new Scanner(gzipStream);

		while (inputScanner.hasNextLine()) {

			String nextLine = inputScanner.nextLine();
			lines++;
			//System.out.println(nextLine + " nextline");

			Event event = new Event(nextLine);
			String currentEventID = event.getID();
			String currentEventStatus = event.getStatus();
			boolean existsInHashtable = hash.get(event.getID()) != null;

			// free and status "A"
			if (!existsInHashtable && currentEventStatus.equalsIgnoreCase("A")) {

				ObjectEventRecord record = new ObjectEventRecord(event);
				hash.put(currentEventID, record);
				event.setCheck("creation");

			}

			// free but not status "A"
			else if (!existsInHashtable
					&& !currentEventStatus.equalsIgnoreCase("A")) {

				event.setCheck("not born");

			} else if (existsInHashtable && !hash.get(currentEventID).isAlive()) {

				event.setCheck("dead");

			}

			// occupied and alive
			else if (existsInHashtable && hash.get(currentEventID).isAlive()) {
				ObjectEventRecord record = hash.get(currentEventID);
				record.updateRecord(event);
				hash.put(currentEventID, record);
				event.setCheck("legal");

			}

			else if (existsInHashtable
					&& currentEventStatus.equalsIgnoreCase("A")) {

				event.setCheck("created");

			}

			// occupied but dead

			notifyHandlers(event);

		}

	}
	
	public int getLines(){
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

	public float getTotalProcessedObjects() {
		float total=0;
		for (EventHandler eh:handlers){
			total+=eh.getTotalObjects();
		}
		return total;

	}
	
	
	public void printReport(){
		for (EventHandler eh:handlers) {
			System.out.println(eh.finalReport(getTotalProcessedObjects()));
		}
	}

	
	public void initialiseHandlers() {
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

	}

}
