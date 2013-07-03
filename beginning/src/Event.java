import java.util.Scanner;

public class Event {

	private String id;
	private String allocationTime;
	private String status;

	public enum Status {
		A, U, M, E, R, D
	};

	public Event(String line) {

		Scanner scanner = new Scanner(line);
		status = scanner.next();
		allocationTime = scanner.next();
		if (status.equalsIgnoreCase("A") || status.equalsIgnoreCase("R")
				|| status.equalsIgnoreCase("D")) {
			id = scanner.next();
		} else {
			scanner.next();
			id = scanner.next();
		}

	}

	public String getID() {
		return id;
	}

	public String getAllocationTime() {
		return allocationTime;
	}

	public String getStatus() {
		return status;
	}

	public String toString() {
		return "status " + status + " id " + id;
	}

}
