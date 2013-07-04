import java.util.ArrayList;

/**
 * All classes which respond to events must implement this interface.
 * 
 * @author Emi
 * 
 */
public interface EventHandler {
	/**
	 * 
	 * @param e
	 *            event to be handled
	 */
	public void handle(Event e);

	public int getNumObjects();

}
