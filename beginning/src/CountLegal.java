import java.util.HashSet;
import java.util.Set;

public class CountLegal implements EventHandler {
	private Set<String> legals;
	private int numLegals;

	public CountLegal() {
		legals = new HashSet<String>();
		numLegals = 0;
	}

	public int getNumLegals() {
		return numLegals;
	}

	public Set getLegal() {
		return legals;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("legal")) {
			legals.add(e.getID());
			numLegals++;
			System.out.println("Object with id " + e.getID()
					+ " has been updated.");
		}

	}

	@Override
	public int getNumObjects() {

		return legals.size();
	}

	@Override
	public String finalReport(float total) {

		return legals.size() / total * 100 + " % objects were updated successfully";
	}
	
	@Override
	public int getTotalObjects() {
		return 0;
	}

}
