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
			"classroom VARCHAR(32), " +
			"courseNum VARCHAR(6), " +
			"credits INTEGER, " +
			"crn VARCHAR(32) NOT NULL, " +
			"depHeader VARCHAR(32), " +
			"description VARCHAR(1024), " +
			"endtime VARCHAR(32), " +
			"isHonors BOOLEAN, " +
			"location VARCHAR(32), " +
			"professor VARCHAR(64), " +
			"semester VARCHAR(32), " +
			"starttime VARCHAR(32), " +
			"title VARCHAR(64), " +
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