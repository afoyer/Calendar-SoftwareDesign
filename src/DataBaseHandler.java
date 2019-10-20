import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHandler {
	  private static final String PORT_NUMBER = "8889";
	  private Connection connection;
	  private Statement stmt;
	
	public DataBaseHandler() {
		
		
	}
	
	/**
     * Initialize a Connection to the database by take in one databaseName, if not exist create it
     * @param databaseName - the name of the database 
     */
    public void createConnection(String databaseName) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?user=root&password=root&serverTimezone=UTC", "root", "root");
            stmt = connection.createStatement();
            String sql = "create database if not exists " + databaseName;
            stmt.execute(sql);
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + databaseName + "?user=root&password=root&serverTimezone=UTC"); // MySQL
            stmt = connection.createStatement();
       
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }
    
    /**
     * Creates a table if the certain table was not created
     * @param tableName - the name of the table
     */
    public void createTable(String tableName) {
    	try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/EventData2?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
			// Step 2 - create our table events
			String sql = "create table if not exists " +tableName+ " (" + "id int NOT NULL AUTO_INCREMENT,"
					+ "Title varchar(50)," + "Date varchar(150),"+ "StartTime varchar(50)," + "EndTime varchar(50)," + "Priority int,"
					+ "Description varchar(500)," + "PRIMARY KEY (id));";
			
			// Step 3 - insert event into events
			stmt.execute(sql);
		
			
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
     
    }


}
