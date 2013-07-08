import java.util.HashSet;
import java.util.Set;

public class CountDead implements EventHandler {

	private Set<String> dead;
	private int numDead;

	public CountDead() {
		dead = new HashSet<String> ();
		numDead = 0;
	}

	public int getNumDead() {
		return numDead;
	}

	public Set getDead() {
		return dead;
	}

	@Override
	public void handle(Event e) {
		if (e.getCheck().equalsIgnoreCase("dead")) {
			dead.add(e.getID());
			numDead++;
			System.out.println("Object with id " + e.getID() + " is dead.");
		}

	}

	@Override
	public int getNumObjects() {

		return dead.size();
	}

	@Override
	public String finalReport(float total) {

		return dead.size() / total * 100 + " % objects cause dead error";
	}
	
	@Override
	public int getTotalObjects() {
		return 0;
	}

}
