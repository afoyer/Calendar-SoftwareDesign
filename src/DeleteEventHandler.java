

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Handles deleting by updating SQL database
 * @author Irene, Aymeric, Surbhi
 *
 */
public class DeleteEventHandler {

	
	private static final String PORT_NUMBER = "8889";
	private static final String DATABASENAME = "EventData2";
	private static final String TABLENAME = "Events";

	/**
	 * Takes in id then finds id in database to remove it.
	 * @param id - event id
	 */
	public void deleteEvent(int id) {
		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+DATABASENAME +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
			// Step 2 - delete the event 
		

			String sql2 = "DELETE FROM " + TABLENAME+" WHERE "+ "id = "+ id + ";";

			stmt.execute(sql2);
			
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	

}
