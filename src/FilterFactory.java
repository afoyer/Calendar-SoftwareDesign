/**
 * Factory for display strategies
 * @author Aymeric, Irene, Surbhi
 *
 */
public class FilterFactory {
	private Filter filter;
	public FilterFactory() {
		
	}
/**
 * display creator takes in boolean
 * @param displayallevents
 * @return
 */
	public Filter createFilter(boolean displayallevents,String database, String table,String date){
	
			FilterStrategy strategy;
	    	if (displayallevents == false) {
	    		strategy = new RetrievesAllEventStrategy();
	    		return new Filter(strategy,database,table,date);
	    	
			
			}
	    	if (displayallevents == true) {
	    		strategy = new RetrievesCertainDateStrategy();
	    		return new Filter(strategy,database,table,date);
	    		
	    	}
	    	
		
		
		return filter;
	}
}
