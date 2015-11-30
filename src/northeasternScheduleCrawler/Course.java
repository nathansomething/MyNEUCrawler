package northeasternScheduleCrawler;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Course {

	public String classroom;
	public Double credits;
	public String crn;
	public List<Day> days;
	public String description;
	public LocalTime endTime;
	public String id;
	public Boolean isHonors;
	public String location;
	public String name;
	public String professor;
	public LocalTime startTime;
	public String term;
	
	Course() {
		this.classroom = "";
		this.credits = 0.0;
		this.crn = "";
		this.days = new ArrayList<>();
		this.endTime = null;
		this.id = "";
		this.isHonors = false;
		this.location = "";
		this.name = "";
		this.professor = "";
		this.startTime = null;
		this.term = "";
	}
	
	@Override
	public String toString() {
		return "----------------------------------" +
				"\nClassroom: " + this.classroom +
				"\nCredits: " + this.credits +
				"\nCRN: " + this.crn +
				"\nDays: " + this.days +
				"\nDescription: " + this.description +
				"\nEnd Time: " + this.endTime +
				"\nHonors: " + this.isHonors.toString() +
				"\nID: " + this.id +
				"\nLocation: " + this.location +
				"\nName: " + this.name +
				"\nProfessor: " + this.professor +
				"\nStart Time: " + this.startTime +
				"\nTerm: " + this.term +
				"\n----------------------------------";
	}
}