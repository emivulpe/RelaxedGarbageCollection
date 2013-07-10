import java.util.HashSet;
import java.util.Set;

public class CountDead implements EventHandler, EventReport {

	private Set<String> dead;

	public CountDead() {
		dead = new HashSet<String>();
	}

	public Set<String> getDead() {
		return dead;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("dead")) {
			dead.add(e.getID());
			System.out.println("Object with id " + e.getID() + " is dead.");
		}

	}

	@Override
	public String finalReport() {

		return (float) dead.size() / SimulatedHeap.getTheHeap().getNumObjects()
				* PERCENTAGE + " % objects cause dead error";
	}

}
