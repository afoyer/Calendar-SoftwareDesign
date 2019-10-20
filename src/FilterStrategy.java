import java.util.ArrayList;

import javafx.collections.ObservableList;
/**
 * Interface for display strategies
 * @author Aymeric, Irene, Surbhi
 *
 */
public interface FilterStrategy {

	public ObservableList<Event> showDisplay(String database, String table, String date);



}
