import java.util.HashSet;
import java.util.Set;

public class CountNotBorn implements EventHandler {
	private Set<String> notBorn;
	private int numNotBorns;

	public CountNotBorn() {
		notBorn = new HashSet<String>();
		numNotBorns = 0;
	}

	public int getNumNotBorns() {
		return numNotBorns;
	}

	public Set getNotBorns() {
		return notBorn;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("not born")) {
			notBorn.add(e.getID());
			numNotBorns++;
			System.out.println("Object with id " + e.getID()
					+ " is not born yet.");
		}

	}

	@Override
	public int getNumObjects() {
		return notBorn.size();
	}

	@Override
	public String finalReport(float total) {
		return  (float)notBorn.size() / total * 100 + " % objects cause not born error";
	}

	@Override
	public int getTotalObjects() {
		return notBorn.size();
	}

}
