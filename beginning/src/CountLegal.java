import java.util.HashSet;
import java.util.Set;

public class CountLegal implements EventHandler {
	private Set legals;
	private int numLegals;

	public CountLegal() {
		legals = new HashSet();
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

}
