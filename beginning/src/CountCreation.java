import java.util.HashSet;
import java.util.Set;

public class CountCreation implements EventHandler {
	private int numCreations;
	private Set<String> created;
	//add a new variable int totalObjects in every count class for producing the report as percentage. is it a good idea?
	private int totalObjects;

	public CountCreation() {
		created = new HashSet<String>();
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
	public String finalReport(float total) {
		// TODO Auto-generated method stub
		return (float)created.size() / total * 100 + " % objects were created successfully";
	}
	
	@Override
	public int getTotalObjects() {
		return totalObjects;
	}

	@Override
	public int getNumObjects() {
		// TODO Auto-generated method stub
		return created.size();
	}

}
