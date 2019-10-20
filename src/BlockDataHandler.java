import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * This class is separated from the rest. It is meant to be run once. It starts the block table and populates it
 * with block number and week and the dates associated with them. Only does block 1 and 2 for now (have to 
 * manually add more blocks)
 * @author Irene, Aymeric, Surbhi
 *
 */
public class BlockDataHandler {
	private static final int PORT_NUMBER = 8889;
	private static final String DATABASENAME = "EventData2";
	private static final String TABLENAME = "BlockDataFinal";
	private Connection connection;
	private Statement stmt;
	public BlockDataHandler() {
		
	}
	/**
	 * Creates block database
	 */

	public void createConnection() {
		try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?user=root&password=root&serverTimezone=UTC", "root", "root");
            stmt = connection.createStatement();
            String sql = "create database if not exists " + DATABASENAME;
            stmt.execute(sql);
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + DATABASENAME + "?user=root&password=root&serverTimezone=UTC"); // MySQL
            stmt = connection.createStatement();
            connection.close();
       
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
		
	}
	/**
	 * 
	 * Creates block table on SQL
	 */
	public void createBlockTable() {
		
		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+DATABASENAME +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
			// Step 2 - create our table events
			String sql = "create table if not exists " + TABLENAME + " (Date varchar(150),"
					+ "BlockName varchar(150)," +  "BlockNumber int," + "Week int);";
			
			// Step 3 - insert event into events
			stmt.execute(sql);
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
     
	}
	/**
	 * Populates block table with info.
	 */
	public void initializeBlockTable() {
		String BlockNameInput = "Unset Block";
		try ( // Step 1: Allocate a database "Connection" object
				Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER
						+ "/"+ DATABASENAME +"?user=root&password=root&serverTimezone=UTC", "root", "root"); // MySQL
				Statement stmt = connection2.createStatement();) {
		
			
			// Step 3 - insert event into events
			//Block1 Week1
			for (int i=26; i<32; i++) {
				String sql0 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week)" + 
						" values ('" + "2019-08-"+i+"','"+BlockNameInput+"','"+1+"','"+1+"');";

						stmt.execute(sql0);
				
			}
			
			String sql3 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
					" values ('" + "2019-09-0"+1+"','"+BlockNameInput+"','"+1+"','"+1+"');\n";

					stmt.execute(sql3);
			
			
					//Block1 Week2
			for (int i=2; i<9; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-09-0"+i+"','"+BlockNameInput+"','"+1+"','"+2+"');\n";

						stmt.execute(sql2);
				
			}
			
			String sql6 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
					" values ('" + "2019-09-0"+9+"','"+BlockNameInput+"','"+1+"','"+3+"');\n";

					stmt.execute(sql6);
			//Block1 Week3
			for (int i=10; i<16; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-09-"+i+"','"+BlockNameInput+"','"+1+"','"+3+"');\n";

						stmt.execute(sql2);
				
			}
			//Block1 Week4
			for (int i=15; i<19; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-09-"+i+"','"+BlockNameInput+"','"+1+"','"+4+"');\n";
						stmt.execute(sql2);
				
			}
			//Block1 BlockBreak
			for (int i=19; i<23; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-09-"+i+"','"+"BlockBreak"+"','"+1+"','"+0+"');\n";
						stmt.execute(sql2);
				
			}
			//Block2 Week1
			for (int i=23; i<30; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-09-"+i+"','"+BlockNameInput+"','"+2+"','"+1+"');\n";
						stmt.execute(sql2);

				
			}
			//Block2 Week2
			String sql4 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
					" values ('" + "2019-09-"+30+"','"+BlockNameInput+"','"+2+"','"+2+"');\n";

					stmt.execute(sql4);
					
		
			for (int i=1; i<7; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-10-0"+i+"','"+BlockNameInput+"','"+2+"','"+2+"');\n";
						stmt.execute(sql2);
				
			}
			//Block2 Week3
			for (int i=7; i<10; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-10-0"+i+"','"+BlockNameInput+"','"+2+"','"+3+"');\n";
						stmt.execute(sql2);
				
			}
			//Block2 Week3 (again)
			for (int i=10; i<14; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-10-"+i+"','"+BlockNameInput+"','"+2+"','"+3+"');\n";
						stmt.execute(sql2);
				
			}
			//Block 2 Week 4
			for (int i=14; i<17; i++) {
				String sql2 = "insert into "+TABLENAME+" (Date,BlockName,BlockNumber,Week) " + 
						" values ('" + "2019-10-"+i+"','"+BlockNameInput+"','"+2+"','"+4+"');\n";
						stmt.execute(sql2);
				
			}
				
			
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	/**
	 * Main
	 * @param args
	 */
	 public static void main(String[] args) {
		 BlockDataHandler handler =new BlockDataHandler();
		 handler.createConnection();
		 handler.createBlockTable();
		 handler.initializeBlockTable();
	        
	    }
	
	
}

