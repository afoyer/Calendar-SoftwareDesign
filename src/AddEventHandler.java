import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * AddEventHandler deals with adding the event to SQL. Uses given information to create event.
 * @author Irene, Aymeric, Surbhi
 *
 */
public class AddEventHandler {
	int priorityInput;
	String titleInput;
	String startTimeInput;
	String endTimeInput;
	String descriptionInput;
	String dateInput;
	private static final String PORT_NUMBER = "8889";
	
	/**
	 * Constructor
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param priority
	 * @param description
	 * @param date
	 */
	public AddEventHandler(String title, String startTime, String endTime, 

		int priority, String description, String date) {
		this.titleInput = title;
		this.startTimeInput = startTime;
		this.endTimeInput = endTime;
		this.priorityInput = priority;
		this.dateInput= date;
		this.descriptionInput = description;


		
	}
	/** 
	 * Adds event to SQL database
	 */
	public void addtoDatabase(String dataBase, String table) {
    	
    	
    	DataBaseHandler handler = new DataBaseHandler();
    	handler.createConnection(dataBase);
    	handler.createTable(table);
    	
    	
    	try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+ dataBase +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
		
			
			// Step 3 - insert event into events
				String sql2 = "insert into "+table+"(Title,Date,StartTime,EndTime,Priority,Description) " + " values ('" + titleInput+"','"+dateInput+"','"+startTimeInput+"','"+ endTimeInput+"','"+priorityInput+"','"+descriptionInput+"');\n";

			stmt.execute(sql2);
			
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

   
    }
}
