import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ETParser {
	private HashMap<String, ObjectEventRecord> hash;
	private List<EventHandler> handlers;
	private int lines;
	private GlobalCounter global;

	public ETParser(InputStream gzipStream) {
		handlers = new ArrayList();
		hash = new HashMap<String, ObjectEventRecord>();
		lines=0;
		global=GlobalCounter.getGlobalCounterInstance();
		EventFactory factory=new EventFactory();

		initialiseHandlers();
		Scanner inputScanner = new Scanner(gzipStream);

		while (inputScanner.hasNextLine()) {

			String nextLine = inputScanner.nextLine();
			lines++;
			System.out.println(nextLine + " nextline");
			Event event=factory.createEvent(nextLine);
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

	public int getTotalProcessedObjects() {

		return global.getNumObjects();

	}
	
	
	public void printReport(){
		for (EventHandler eh:handlers) {
			System.out.println(eh.finalReport(getTotalProcessedObjects()));
		}
	}

	
	public void initialiseHandlers() {
		registerHandler(global);
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
		EventHandler livesize=new LiveSize();
		registerHandler(livesize);
		

	}

}
