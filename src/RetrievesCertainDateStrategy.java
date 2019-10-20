
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//package application;
/**
 * Strategy that gets events from database given a set date.
 * @author Irene, Aymeric, Surbhi
 *
 */
public class RetrievesCertainDateStrategy implements FilterStrategy {

	private static final String PORT_NUMBER = "8889";
	private  String textdate;


	public ObservableList<Event> showDisplay(String dataBase, String table,String datetext) {

		ObservableList<Event> events = FXCollections.observableArrayList();

		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+dataBase +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
			// Step 2 - create our table events
			if (connection2 != null) {
				String strSelect = "select * from "+table;
				ResultSet rset = stmt.executeQuery(strSelect);
				String textdate = CalendarController.getDatetext();

				// Process the result set by stepping through using next().
				// Add each id into the list of id
				while (rset.next()) {
					
					String date = rset.getString("Date");
				
					
					//textdate= CalendarController.getDatetext();
					
					if (date .equals(datetext)) {
						
						int prori = rset.getInt("Priority");
						String title = rset.getString("Title");
						String startTime = rset.getString("StartTime");
						String endTime = rset.getString("EndTime");
						String description = rset.getString("Description");

						String dateInfo =rset.getString("Date");


						Event event = new Event(title, startTime, endTime, prori);
						event.setInfo(description);
						event.setDate(dateInfo);

						events.add(event);
						
					}
						
				
				}
			}
			return events;
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		return events;

	}

	
}
