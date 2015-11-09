package northeasternScheduleCrawler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Uploader {

	private final String connString = "jdbc:derby:codejava/ClassData;create=true";
	private final Connection conn;
	private final Statement statement;
	private String query;
	
	Uploader() throws SQLException {
		this.conn = DriverManager.getConnection(connString);
        if (this.conn == null) {
        	throw new SQLException("Failed to connect to database");
        }
        this.statement = this.conn.createStatement();
        this.query = "";
	}
	
	public void disconnect() throws SQLException {
		this.conn.close();
	}
	
	public void createTabels() throws SQLException {
		this.query = "CREATE TABLE COURSE (" +
			"crn INTEGER NOT NULL, " +
			"credits FLOAT, " +
			"endtime TIME, " +
			"id VARCHAR(32), " +
			"loctaion VARCHAR(32), " +
			"name VARCHAR(32), " +
			"professor VARCHAR(32), " +
			"starttime TIME, " +
			"term VARCHAR(32), " +
			"PRIMARY KEY(crn))";
		this.statement.executeUpdate(query);
		System.out.println("Successfully Created Tables");
	}
	
	public void deleteTables() throws SQLException {
		this.query = "DROP TABLE COURSE";
		statement.executeUpdate(query);
		System.out.println("Successfully Deleted Tables");
	}
	
	public void addCourse(Course course) throws SQLException {
		this.query = "INSERT INTO " +
						"COURSE (crn, name) " +
						"VALUES ( " + 
						course.crn + ", \'" +
						course.name + "\')";
		statement.executeUpdate(query);
		System.out.println("Successfully Added Course To Table");
	}
	
	public void removeCourse(Course course) throws SQLException {
		this.query = "DELETE FROM COURSE " +
					"WHERE COURSE.crn = " + course.crn; 
		statement.executeUpdate(query);
		System.out.println("Successfully Deleted Course From Table");
	}
	
	public List<String> getData() throws SQLException {
		this.query = "SELECT * FROM COURSE";
		this.statement.executeQuery(this.query);
        ResultSet resultSet = statement.getResultSet();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
        	names.add(resultSet.getString("Name"));
        }
		return names;
	}
}