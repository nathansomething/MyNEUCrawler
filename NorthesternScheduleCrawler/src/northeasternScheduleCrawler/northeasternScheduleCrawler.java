package northeasternScheduleCrawler;

import java.sql.SQLException;
import java.util.List;

public class northeasternScheduleCrawler {

	public static void main(String[] args) {
		String username;
		String password;
		String season;
		String year;
		
		if (args.length == 4) {
			username = args[0];
			password = args[1];
			season = args[2];
			year = args[3];
		}
		else {
			throw new RuntimeException("Expects 4 arguments: Username, Password, Season, Year");
		}
		
		/*Crawler crawler = new Crawler();
		crawler.login(username, password);
		crawler.goToSelfService();
		List<Course> courses = crawler.getCourses(season, year);
		
		for (Course course: courses) {
			System.out.println(course.toString());
		}*/
		
		Course course01 = new Course();
		course01.crn = "02215"; 
		course01.name = "Hi";
		
		Course course02 = new Course();
		course02.crn = "02216"; 
		course02.name = "Bye";
		
		try {
			Uploader uploader = new Uploader();
			//uploader.createTabels();
			//uploader.deleteTables();
			uploader.addCourse(course01);
			uploader.addCourse(course02);
			//uploader.removeCourse(myCourse);
			List<String> names = uploader.getData();
			for (String name : names) {
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}