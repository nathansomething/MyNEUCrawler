package northeasternScheduleCrawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveTable {

	private final static String connString = "jdbc:derby:codejava/ClassData;create=true";
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn =  DriverManager.getConnection(connString);
        Statement statement = conn.createStatement();
		String query = "DROP TABLE COURSE";
		statement.executeUpdate(query);
		System.out.println("Successfully Deleted Table");
	}
}