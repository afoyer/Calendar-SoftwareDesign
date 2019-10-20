import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Updates block name and can fetch block name with week and block number
 * @author Irene, Aymeric, Surbhi
 *
 */
public class UpdateBlockHandler {
	private static final int PORT_NUMBER = 8889;
	private static final String DATABASENAME = "EventData2";
	private static final String TABLENAME = "BlockDataFinal";
	public void UpdateBlockHandler() {
		
	}
	/**
	 * Updates block name in SQL table given block number
	 * @param number - block number
	 * @param name - block name to be set
	 */
	public void UpdateBlockName(int number, String name) {
		
		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+ DATABASENAME +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
		
			
			// Step 3 - insert event into events
			String sql ="UPDATE "+TABLENAME+"\n" + 
					"SET BlockName = '" + name + "'\n" + 
					"WHERE BlockNumber =" + number + ";";
			
			stmt.execute(sql);
			
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
	}
	/**
	 * Gets block name, week and number to return as a string for the label in the calendar.
	 * @param date - set date from JavaFX's datepicker that the user picks
	 * @return string of the block name, number and week
	 */
	public String getBlockName(String date) {
		String blockName = "";
		int blockNumber=0;
		int week=0;
	
		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+ DATABASENAME +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
		
			
			// Step 3 - insert event into events
			String strSelect = "select * from "+TABLENAME;
			ResultSet rset = stmt.executeQuery(strSelect);
			String textdate = CalendarController.getDatetext();

			// Process the result set by stepping through using next().
			// Add each id into the list of id
			while (rset.next()) {
				
				String date1 = rset.getString("Date");
			
				
				textdate= CalendarController.getDatetext();
				
				if (date1 .equals(date)) {
					
				
					
				
					blockName = rset.getString("BlockName");
					blockNumber = rset.getInt("BlockNumber");
					week = rset.getInt("Week");
					


					
					
				}
			}
			
		}
		
					
			
			
			
		

		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		if(blockName.equals("")) {
			return "Unset Block";
		}
		else if(week == 0) {
			return "Block Break";
		}
		return blockName + ", Block " + Integer.toString(blockNumber) + ", Week " + Integer.toString(week);
	}
	

}
