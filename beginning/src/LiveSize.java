import java.util.HashMap;

public class LiveSize implements EventHandler {

	private long liveSize, allocatedMemSize;
	private int numCreationsOrDeaths; // why do we need this???
	private HashMap<String, Integer> objectSizes;
	private StatisticsLogger logger; // doesn't work- how to output in a file???
	public static final int OUTPUT_INTERVAL = 1000; //output to logfile after this number of events

	public LiveSize() {
		objectSizes = new HashMap<String, Integer>();
		logger = new StatisticsLogger();
		liveSize = 0;
		allocatedMemSize = 0;
		numCreationsOrDeaths = 0;
	}

	// getters

	public long getLiveSize() {
		return liveSize;
	}

	public long getAllocatedMemSize() {
		return allocatedMemSize;
	}

	public int getNumCreationsOrDeaths() {
		return numCreationsOrDeaths;
	}

	// methods inherited by EventHandler

	@Override
	public void handle(Event e) {
		if (e.status.equalsIgnoreCase("A")) {
			CreationEvent ce = (CreationEvent) e;
			objectSizes.put(e.getID(), ce.getSize());
			liveSize += ce.getSize();
			allocatedMemSize += ce.getSize();
			numCreationsOrDeaths++;

		}
		if (e.getStatus().equalsIgnoreCase("D")&&objectSizes.get(e.getID())!=null) {
			numCreationsOrDeaths++;
			liveSize -= objectSizes.get(e.getID());
			assert (liveSize >= 0);

		}

		report();

	}

	public void report() {
		if (numCreationsOrDeaths % OUTPUT_INTERVAL == 0) {
			// how to output to a logfile??????

			logger.getLogger().info(
					liveSize + ", "
							+ allocatedMemSize);
			System.out.println("Live size: " + liveSize + " Allocated memory: "
					+ allocatedMemSize);

		}
	}

}
