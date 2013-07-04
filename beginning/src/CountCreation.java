import java.util.HashSet;
import java.util.Set;

public class CountCreation implements EventHandler {
	private int numCreations;
	private Set created;

	public CountCreation() {
		created = new HashSet();
		numCreations = 0;
	}

	public int getNumCreations() {
		return numCreations;
	}

	public Set getCreated() {
		return created;
	}

	@Override
	public void handle(Event e) {

		if (e.getCheck().equalsIgnoreCase("creation")) {
			created.add(e.getID());
			numCreations++;
			System.out.println("Object with id " + e.getID()
					+ " has been created.");
		}

	}

	@Override
	public int getNumObjects() {

		return created.size();
	}

}
