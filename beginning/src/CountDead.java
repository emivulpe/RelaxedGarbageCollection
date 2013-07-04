import java.util.HashSet;
import java.util.Set;

public class CountDead implements EventHandler {

	private Set dead;
	private int numDead;

	public CountDead() {
		dead = new HashSet();
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

}
