import java.util.HashSet;
import java.util.Set;

public class CountMultipleCreations implements EventHandler {
	private Set<String> multiples;
	private int numMultiples;

	public CountMultipleCreations() {
		multiples = new HashSet<String>();
		numMultiples = 0;
	}

	public int getNumMultiples() {
		return numMultiples;
	}

	public Set getMultiples() {
		return multiples;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("created")) {
			multiples.add(e.getID());
			numMultiples++;
			System.out.println("Object with id " + e.getID()
					+ " has been created more than once.");
		}

	}

	@Override
	public int getNumObjects() {

		return multiples.size();
	}

	@Override
	public String finalReport(float total) {
		// TODO Auto-generated method stub
		return  (float)multiples.size() / total * 100 + " % objects were created more than once";
	}
	@Override
	public int getTotalObjects() {
		return 0;
	}

}
