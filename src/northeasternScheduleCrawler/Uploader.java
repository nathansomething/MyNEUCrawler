package northeasternScheduleCrawler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
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
	
	private Boolean getTimeBool(Course course, Day day) {
		return course.days.contains(day);
	}
	
	/*private java.sql.Time getSqlTime(LocalTime time) {
		if (time == null) {
			return null;
		}
		return java.sql.Time.valueOf(time);
	}*/
	
	public void addCourse(Course course) throws SQLException {
			this.query = "INSERT INTO COURSE VALUES ( ";
			this.query += "\'" + course.classroom + "\' , \n";
			this.query += "\'" + course.courseNum + "\' , \n";
			this.query += course.credits + ", \n";
			this.query += "\'" + course.crn + "\' , \n";
			this.query += "\'" + course.depHeader + "\' , \n";
			this.query += "\'" + course.description + "\', \n";
			this.query += "\'" + course.endTime + "\', \n";
			this.query += course.isHonors + ", \n";
			this.query += "\'" + course.location + "\' , \n";
			this.query += "\'" + course.professor + "\' , \n";
			this.query += "\'" + course.semester + "\' , \n";
			this.query += "\'" + course.startTime + "\' , \n";
			this.query += "\'" + course.title + "\' , \n";
			this.query += this.getTimeBool(course, Day.MONDAY) + ", \n";
			this.query += this.getTimeBool(course, Day.TUESDAY) + ", \n";
			this.query += this.getTimeBool(course, Day.WEDNESDAY) + ", \n";
			this.query += this.getTimeBool(course, Day.THURSDAY) + ", \n";
			this.query += this.getTimeBool(course, Day.FRIDAY) + ", \n";
			this.query += this.getTimeBool(course, Day.SATURDAY) + ")";
			//System.out.println(this.query);
			statement.executeUpdate(query);
			System.out.println("Successfully Added Course To Table");
	}
	
	public void removeCourse(Course course) throws SQLException {
		this.query = "DELETE FROM COURSE " +
					"WHERE COURSE.crn = " + course.crn; 
		statement.executeUpdate(query);
		System.out.println("Successfully Deleted Course From Table");
	}
	
	public void removeCourses(String id) throws SQLException {
		this.query = "DELETE FROM COURSE " +
				"WHERE COURSE.id LIKE \'" + id + "\'";
	statement.executeUpdate(query);
	System.out.println("Successfully Deleted Course From Table");
	}
	
	public List<Course> getData() throws SQLException {
		this.query = "SELECT * FROM COURSE";
		this.statement.executeQuery(this.query);
        ResultSet resultSet = statement.getResultSet();
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
        	Course course = new Course();
        	course.classroom = resultSet.getString("classroom");
			course.courseNum = resultSet.getString("courseNum");
			course.credits = resultSet.getInt("credits");
			course.crn = resultSet.getString("crn");
			course.depHeader = resultSet.getString("depHeader");
			course.description = resultSet.getString("description");
			course.endTime = resultSet.getString("endtime");
			course.isHonors = resultSet.getBoolean("isHonors");
			course.location = resultSet.getString("location");
			course.professor = resultSet.getString("professor");
			course.semester = resultSet.getString("semester");
			course.startTime = resultSet.getString("starttime");
			course.title = resultSet.getString("title");
        	if (resultSet.getBoolean("monday")) {
        		course.days.add(Day.MONDAY);
        	}
        	if (resultSet.getBoolean("tuesday")) {
        		course.days.add(Day.TUESDAY);
        	}
        	if (resultSet.getBoolean("wednesday")) {
        		course.days.add(Day.WEDNESDAY);
        	}
        	if (resultSet.getBoolean("thursday")) {
        		course.days.add(Day.THURSDAY);
        	}
        	if (resultSet.getBoolean("friday")) {
        		course.days.add(Day.FRIDAY);
        	}
        	if (resultSet.getBoolean("saturday")) {
        		course.days.add(Day.SATURDAY);
        	}
        	courses.add(course);
        }
		return courses;
	}
}