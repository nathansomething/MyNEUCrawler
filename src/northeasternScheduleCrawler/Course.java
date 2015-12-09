package northeasternScheduleCrawler;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Course {

	public String classroom;
	public String courseNum;
	public Integer credits;
	public String crn;
	public List<Day> days;
	public String depHeader;
	public String description;
	public String endTime;
	public Boolean isHonors;
	public String location;
	public String professor;
	public String semester;
	public String startTime;
	public String title;
	
	Course() {
		this.classroom = "";
		this.courseNum = "";
		this.credits = 0;
		this.crn = "";
		this.days = new ArrayList<>();
		this.depHeader = "";
		this.description = "";
		this.endTime = "";
		this.isHonors = false;
		this.location = "";
		this.professor = "";
		this.semester = "";
		this.startTime = "";
		this.title = "";
	}
	
	@Override
	public String toString() {
		return "----------------------------------" +
			"\n Classroom: " + this.classroom +
			"\n Course Number: " + this.courseNum +
			"\n Credits: " + this.credits +
			"\n CRN: " + this.crn +
			"\n Days: " + this.days +
			"\n Department Header: " + this.depHeader +
			"\n Description: " + this.description +
			"\n End Time: " + this.endTime +
			"\n Is Honors: " + this.isHonors +
			"\n Location: " + this.location +
			"\n Professor: " + this.professor +
			"\n Semester: " + this.semester +
			"\n Start Time: " + this.startTime +
			"\n Title: " + this.title +
			"\n----------------------------------";
	}
}