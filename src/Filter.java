import javafx.collections.ObservableList;
/**
 * Sets the strategy
 * @author Irene, Aymeric, Surbhi
 *
 */
public class Filter {
	

	private FilterStrategy strategy;
	/**
	 * Constructor for the displayer.
	 * @param date 
	 */
	public Filter(FilterStrategy strategy, String database,String table, String date) {
		this.strategy = strategy;
	}
	/**
	 * Uses strategy to filter through events.
	 * @param database
	 * @param table
	 * @return
	 */
	public ObservableList<Event> showDisplay(String database,String table,String date ){
		return strategy.showDisplay(database,table,date);
	}
	

}
