import java.util.HashSet;
import java.util.Set;

public class CountNotBorn implements EventHandler, EventReport {
	private Set<String> notBorn;

	public CountNotBorn() {
		notBorn = new HashSet<String>();
	}

	public Set<String> getNotBorns() {
		return notBorn;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("not born")) {
			notBorn.add(e.getID());
			System.out.println("Object with id " + e.getID()
					+ " is not born yet.");
		}

	}

	@Override
	public String finalReport() {
		return (float) notBorn.size()
				/ SimulatedHeap.getTheHeap().getNumObjects() * PERCENTAGE
				+ " % objects cause not born error";
	}

}
