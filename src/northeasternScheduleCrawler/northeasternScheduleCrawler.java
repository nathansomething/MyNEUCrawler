package northeasternScheduleCrawler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
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
		
		Uploader uploader = null;
		
		try {
			uploader = new Uploader();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Crawler crawler = new Crawler();
		crawler.login(username, password);
		crawler.goToSelfService();
		List<Course> courses = new ArrayList<>();
		try {
			/*courses.addAll(crawler.getCourses(season, year, "ACCT"));
			courses.addAll(crawler.getCourses(season, year, "AFRS"));
			courses.addAll(crawler.getCourses(season, year, "AFAM"));
			courses.addAll(crawler.getCourses(season, year, "AMSL"));
			courses.addAll(crawler.getCourses(season, year, "ANTH"));
			courses.addAll(crawler.getCourses(season, year, "ARAB"));
			courses.addAll(crawler.getCourses(season, year, "ARCH"));
			courses.addAll(crawler.getCourses(season, year, "ARMY"));
			courses.addAll(crawler.getCourses(season, year, "ARTG"));
			courses.addAll(crawler.getCourses(season, year, "ARTF"));
			courses.addAll(crawler.getCourses(season, year, "ARTE"));
			courses.addAll(crawler.getCourses(season, year, "ARTH"));
			courses.addAll(crawler.getCourses(season, year, "ARTD"));
			courses.addAll(crawler.getCourses(season, year, "ARTS"));
			courses.addAll(crawler.getCourses(season, year, "ASNS"));
			courses.addAll(crawler.getCourses(season, year, "BNSC"));
			courses.addAll(crawler.getCourses(season, year, "BIOC"));
			courses.addAll(crawler.getCourses(season, year, "BIOE"));
			courses.addAll(crawler.getCourses(season, year, "BIOL"));
			courses.addAll(crawler.getCourses(season, year, "BIOT"));
			courses.addAll(crawler.getCourses(season, year, "BUSN"));
			courses.addAll(crawler.getCourses(season, year, "EXSC"));
			courses.addAll(crawler.getCourses(season, year, "CHME"));
			courses.addAll(crawler.getCourses(season, year, "CHEM"));
			courses.addAll(crawler.getCourses(season, year, "CHNS"));
			courses.addAll(crawler.getCourses(season, year, "CIVE"));
			courses.addAll(crawler.getCourses(season, year, "COMM"));
			courses.addAll(crawler.getCourses(season, year, "CMMN"));*/
			courses.addAll(crawler.getCourses(season, year, "CS"));
			/*courses.addAll(crawler.getCourses(season, year, "CSYE"));
			courses.addAll(crawler.getCourses(season, year, "EEAM"));
			courses.addAll(crawler.getCourses(season, year, "EESC"));
			courses.addAll(crawler.getCourses(season, year, "EESH"));
			courses.addAll(crawler.getCourses(season, year, "EXED"));
			courses.addAll(crawler.getCourses(season, year, "COOP"));
			courses.addAll(crawler.getCourses(season, year, "CAEP"));
			courses.addAll(crawler.getCourses(season, year, "CRIM"));
			courses.addAll(crawler.getCourses(season, year, "CLTR"));
			courses.addAll(crawler.getCourses(season, year, "LITR"));
			courses.addAll(crawler.getCourses(season, year, "DSCS"));
			courses.addAll(crawler.getCourses(season, year, "DSSH"));
			courses.addAll(crawler.getCourses(season, year, "DEAF"));
			courses.addAll(crawler.getCourses(season, year, "EVRN"));
			courses.addAll(crawler.getCourses(season, year, "ENVR"));
			courses.addAll(crawler.getCourses(season, year, "EEMB"));
			courses.addAll(crawler.getCourses(season, year, "ECON"));
			courses.addAll(crawler.getCourses(season, year, "ECNM"));
			courses.addAll(crawler.getCourses(season, year, "EDUC"));
			courses.addAll(crawler.getCourses(season, year, "EECE"));
			courses.addAll(crawler.getCourses(season, year, "ENSY"));
			courses.addAll(crawler.getCourses(season, year, "ENCP"));
			courses.addAll(crawler.getCourses(season, year, "ENGR"));
			courses.addAll(crawler.getCourses(season, year, "ENLR"));
			courses.addAll(crawler.getCourses(season, year, "EMGT"));
			courses.addAll(crawler.getCourses(season, year, "ENGL"));
			courses.addAll(crawler.getCourses(season, year, "ENGH"));
			courses.addAll(crawler.getCourses(season, year, "ENGW"));
			courses.addAll(crawler.getCourses(season, year, "ESLG"));
			courses.addAll(crawler.getCourses(season, year, "ENTR"));
			courses.addAll(crawler.getCourses(season, year, "TECE"));
			courses.addAll(crawler.getCourses(season, year, "ENVS"));
			courses.addAll(crawler.getCourses(season, year, "FINA"));
			courses.addAll(crawler.getCourses(season, year, "FSEM"));
			courses.addAll(crawler.getCourses(season, year, "FRNH"));
			courses.addAll(crawler.getCourses(season, year, "GAME"));
			courses.addAll(crawler.getCourses(season, year, "GSND"));
			courses.addAll(crawler.getCourses(season, year, "GE"));
			courses.addAll(crawler.getCourses(season, year, "GENS"));
			courses.addAll(crawler.getCourses(season, year, "GRMN"));
			courses.addAll(crawler.getCourses(season, year, "GBST"));
			courses.addAll(crawler.getCourses(season, year, "GREK"));
			courses.addAll(crawler.getCourses(season, year, "HINF"));
			courses.addAll(crawler.getCourses(season, year, "HLTH"));
			courses.addAll(crawler.getCourses(season, year, "HSCI"));
			courses.addAll(crawler.getCourses(season, year, "HBRW"));
			courses.addAll(crawler.getCourses(season, year, "HIST"));
			courses.addAll(crawler.getCourses(season, year, "HSTY"));
			courses.addAll(crawler.getCourses(season, year, "HONR"));
			courses.addAll(crawler.getCourses(season, year, "HRMG"));
			courses.addAll(crawler.getCourses(season, year, "HUSV"));
			courses.addAll(crawler.getCourses(season, year, "IE"));
			courses.addAll(crawler.getCourses(season, year, "IA"));
			courses.addAll(crawler.getCourses(season, year, "IS"));
			courses.addAll(crawler.getCourses(season, year, "INFO"));
			courses.addAll(crawler.getCourses(season, year, "INSH"));
			courses.addAll(crawler.getCourses(season, year, "INTL"));
			courses.addAll(crawler.getCourses(season, year, "INTB"));
			courses.addAll(crawler.getCourses(season, year, "INTP"));
			courses.addAll(crawler.getCourses(season, year, "ITLN"));
			courses.addAll(crawler.getCourses(season, year, "JPNS"));
			courses.addAll(crawler.getCourses(season, year, "JWSS"));
			courses.addAll(crawler.getCourses(season, year, "JRNL"));
			courses.addAll(crawler.getCourses(season, year, "LARC"));
			courses.addAll(crawler.getCourses(season, year, "LANG"));
			courses.addAll(crawler.getCourses(season, year, "LACS"));
			courses.addAll(crawler.getCourses(season, year, "LW"));
			courses.addAll(crawler.getCourses(season, year, "LPSC"));
			courses.addAll(crawler.getCourses(season, year, "LING"));
			courses.addAll(crawler.getCourses(season, year, "MGMT"));
			courses.addAll(crawler.getCourses(season, year, "MISM"));
			courses.addAll(crawler.getCourses(season, year, "MGSC"));
			courses.addAll(crawler.getCourses(season, year, "MECN"));
			courses.addAll(crawler.getCourses(season, year, "MARS"));
			courses.addAll(crawler.getCourses(season, year, "MKTG"));
			courses.addAll(crawler.getCourses(season, year, "MATL"));
			courses.addAll(crawler.getCourses(season, year, "MATH"));
			courses.addAll(crawler.getCourses(season, year, "MATM"));
			courses.addAll(crawler.getCourses(season, year, "MEIE"));
			courses.addAll(crawler.getCourses(season, year, "ME"));
			courses.addAll(crawler.getCourses(season, year, "CINE"));
			courses.addAll(crawler.getCourses(season, year, "MSCR"));
			courses.addAll(crawler.getCourses(season, year, "MUSC"));
			courses.addAll(crawler.getCourses(season, year, "MUSI"));
			courses.addAll(crawler.getCourses(season, year, "MPNC"));
			courses.addAll(crawler.getCourses(season, year, "MUST"));
			courses.addAll(crawler.getCourses(season, year, "NNMD"));
			courses.addAll(crawler.getCourses(season, year, "NAVY"));
			courses.addAll(crawler.getCourses(season, year, "NRSG"));
			courses.addAll(crawler.getCourses(season, year, "OR"));
			courses.addAll(crawler.getCourses(season, year, "ORGB"));
			courses.addAll(crawler.getCourses(season, year, "PHSC"));
			courses.addAll(crawler.getCourses(season, year, "PMST"));
			courses.addAll(crawler.getCourses(season, year, "PHMD"));
			courses.addAll(crawler.getCourses(season, year, "PHIL"));
			courses.addAll(crawler.getCourses(season, year, "PHLS"));
			courses.addAll(crawler.getCourses(season, year, "RELS"));
			courses.addAll(crawler.getCourses(season, year, "PT"));
			courses.addAll(crawler.getCourses(season, year, "PA"));
			courses.addAll(crawler.getCourses(season, year, "PHYS"));
			courses.addAll(crawler.getCourses(season, year, "POLS"));
			courses.addAll(crawler.getCourses(season, year, "PORT"));
			courses.addAll(crawler.getCourses(season, year, "PSYC"));
			courses.addAll(crawler.getCourses(season, year, "PPUA"));
			courses.addAll(crawler.getCourses(season, year, "PHTH"));
			courses.addAll(crawler.getCourses(season, year, "RSSN"));
			courses.addAll(crawler.getCourses(season, year, "SMFA"));
			courses.addAll(crawler.getCourses(season, year, "SOCL"));
			courses.addAll(crawler.getCourses(season, year, "SPNS"));
			courses.addAll(crawler.getCourses(season, year, "SLPA"));
			courses.addAll(crawler.getCourses(season, year, "STRT"));
			courses.addAll(crawler.getCourses(season, year, "ABRB"));
			courses.addAll(crawler.getCourses(season, year, "ABRS"));
			courses.addAll(crawler.getCourses(season, year, "SCHM"));
			courses.addAll(crawler.getCourses(season, year, "SBSY"));
			courses.addAll(crawler.getCourses(season, year, "SUEN"));
			courses.addAll(crawler.getCourses(season, year, "TELE"));
			courses.addAll(crawler.getCourses(season, year, "THTR"));
			courses.addAll(crawler.getCourses(season, year, "WMNS"));*/
		}
		catch(Exception e) { }
		
		crawler.close();
		
		// PRINT OUT ALL COURSES
		/*for (Course course: courses) {
			System.out.println(course.toString());
		}*/
		
		for (Course course : courses) {
			try {
				uploader.addCourse(course);
			}
			catch(SQLIntegrityConstraintViolationException e) {
				System.out.println("Ignoring Course Becasue it's Already in the Database");
			}
			catch (SQLException e) { 
				e.printStackTrace();
			}
		}
	
		List<Course> coursesAfterUpload = new ArrayList<>();
		try {
			coursesAfterUpload = uploader.getData();
			System.out.println("Number Courses Added: " + coursesAfterUpload.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Course course : coursesAfterUpload) {
			System.out.println(course);
		}
	}
}