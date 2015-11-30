package northeasternScheduleCrawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	private final static String connString = "jdbc:derby:codejava/ClassData;create=true";
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn =  DriverManager.getConnection(connString);
        Statement statement = conn.createStatement();
		String query = "CREATE TABLE COURSE (" +
			"crn VARCHAR(32) NOT NULL, " +
			"credits FLOAT, " +
			"endtimeHour INTEGER, " +
			"endtimeMinute INTEGER, " +
			"endtimeIsAM BOOLEAN, " +
			"id VARCHAR(32), " +
			"location VARCHAR(32), " +
			"name VARCHAR(256), " +
			"professor VARCHAR(32), " +
			"starttimeHour INTEGER, " +
			"starttimeMinute INTEGER, " +
			"starttimeIsAM BOOLEAN, " +
			"term VARCHAR(32), " +
			"monday BOOLEAN NOT NULL, " +
			"tuesday BOOLEAN NOT NULL, " +
			"wednesday BOOLEAN NOT NULL, " +
			"thursday BOOLEAN NOT NULL, " +
			"friday BOOLEAN NOT NULL, " +
			"saturday BOOLEAN NOT NULL, " +
			"PRIMARY KEY(crn))";
		statement.executeUpdate(query);
		System.out.println("Successfully Created Table");

	}
}