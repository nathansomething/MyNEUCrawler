package northeasternScheduleCrawler;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.thoughtworks.selenium.SeleniumException;

public class Crawler {

	private final WebDriver driver;
	
	Crawler() { 
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void close() {
		this.driver.quit();
	}
	
	void login(String username, String password) {
		this.driver.get("http://myneu.neu.edu/cp/home/displaylogin");
		
		WebElement usernameTextBox = this.driver.findElement(By.id("user"));
		WebElement passwordTextBox = this.driver.findElement(By.id("pass"));
		WebElement loginButton = this.driver.findElement(By.cssSelector("input[value=Login]"));
		
		usernameTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);
		loginButton.click();
	}
	
	void goToSelfService() {
		WebElement selfServiceTab = this.driver.findElement(By.linkText("Self-Service"));
		selfServiceTab.click();
	}
	
	void goToClassSchedule() {
		WebElement classSchedule = this.driver.findElement(By.linkText("Schedule of Classes"));
		classSchedule.click();
		this.driver.switchTo().window("EntireSched");
	}
	
	void selectTerm(String season, String year) {
		WebElement searchByTermDropdown = this.driver.findElement(By.id("term_input_id"));
		List<WebElement> options = searchByTermDropdown.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().contains(season) && option.getText().contains(year)) {
				option.click();
				break;
			}
		}
		WebElement submitButton = this.driver.findElement(By.cssSelector("input[value=Submit]"));
		submitButton.click();
	}
	
	/*Integer getNumberSubjects(String season, String year) {
		this.goToClassSchedule();
		this.selectTerm(season, year);
		WebElement subjectMenu = this.driver.findElement(By.id("subjct_id"));
		List<WebElement> subjects = subjectMenu.findElements(By.tagName("option"));
		subjects.remove(0); //Remove "All" from list
		this.driver.close();
		this.driver.switchTo().window("");
		return subjects.size();
	}*/
	
	String getDescription(WebElement header) {
		
		String oldWindowHandle = this.driver.getWindowHandle();
		Actions action = new Actions(this.driver);
		action.keyDown(Keys.SHIFT).click(header).keyUp(Keys.SHIFT).build().perform();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		}
		
		String description = "";
		try {
			description = this.driver.findElement(By.cssSelector("div.pagebodydiv > table.datadisplaytable > tbody > tr:nth-child(2) > td.ntdefault")).getText().split("\n")[0];
		}
		catch(SeleniumException e) { } //If element cannot be found, don't set the description field
		
		this.driver.close();
		
		try {
			this.driver.switchTo().window(oldWindowHandle);
		}
		catch(SeleniumException e) {
			this.driver.switchTo().defaultContent();
		}
		
		return description;
	}
	
	Course getCourse(WebElement header, WebElement body) {
		Course course = new Course();
		String[] headerInfo = header.getText().split(" - ");
		course.name = headerInfo[0];
		course.crn = headerInfo[1];
		course.id = headerInfo[2];
		course.location = headerInfo[3].substring(headerInfo[3].indexOf("(") + 1, headerInfo[3].length() - 1);
		//System.out.println(headerInfo[4].substring(8));
		course.description = this.getDescription(header);
		try {
			course.credits = Double.parseDouble(headerInfo[4].substring(8));
		}
		catch (NumberFormatException e) { }
		
		
		String[] bodyText = body.getText().split("\n");
		for (String bodyTextLine : bodyText) {
			if (bodyTextLine.contains("Associated Term: ")) {
				course.term = bodyTextLine.substring(16);
			}
			if (bodyTextLine.contains("Attributes:")) {
				//System.out.println(Boolean.valueOf(bodyTextLine.substring(12).contains("Honors")));
				course.isHonors = Boolean.valueOf(bodyTextLine.substring(12).contains("Honors"));
				
			}
			if (bodyTextLine.contains("Instructors: ")) {
				course.professor = bodyTextLine.substring(13);
			}
			if (bodyTextLine.contains("Class")) {
				String[] classVars = bodyTextLine.split(" ");
				if (classVars.length > 8) {
					try {
						course.startTime = LocalTime.of(
								Integer.parseInt(classVars[1].split(":")[0]), 
								Integer.parseInt(classVars[1].split(":")[1]));
						if (classVars[2] == "pm") {
							course.startTime.plusHours(12);
						}
					}
					catch (NumberFormatException e) { }
					try {
						course.endTime = LocalTime.of(
								Integer.parseInt(classVars[4].split(":")[0]), 
								Integer.parseInt(classVars[4].split(":")[1]));
						if (classVars[5] == "pm") {
							course.endTime.plusHours(12);
						}
					} 
					catch (NumberFormatException e) { }
					
					String days = classVars[6];
					if (days.contains("M")) {
						course.days.add(Day.MONDAY);
					}
					if (days.contains("T")) {
						course.days.add(Day.TUESDAY);
					}
					if (days.contains("W")) {
						course.days.add(Day.WEDNESDAY);
					}
					if (days.contains("R")) {
						course.days.add(Day.THURSDAY);
					}
					if (days.contains("F")) {
						course.days.add(Day.FRIDAY);
					}
					if (days.contains("S")) {
						course.days.add(Day.SATURDAY);
					}
					try {
						int index;
						for (index = 7; classVars[index + 1] != null && Character.isAlphabetic(classVars[index].charAt(0)); index++) {
							course.classroom += ' ' + classVars[index];
						}
						course.classroom += ' ' + classVars[index];
					}
					catch (ArrayIndexOutOfBoundsException e) { }
				}
			}
		}
		return course;
	}
	
	List<Course> getCoursesFromPage() {
		List<Course> courses = new ArrayList<>();
		List<WebElement> classRows = this.driver.findElements(By.cssSelector("div.pagebodydiv > table.datadisplaytable > tbody > tr"));
		classRows.remove(classRows.size() - 1);
		
		/*for (WebElement classRow : classRows) {
			System.out.println(classRow.getText() + '\n');
		}*/
		
		for (Integer row = 0; row < classRows.size(); row += 2) {
			courses.add(this.getCourse(classRows.get(row).findElement(By.cssSelector("a")), classRows.get(row + 1)));
		}
		return courses;
	}
	
	List<Course> getCourses(String season, String year, String subjectCode) {
		List<Course> courses = new ArrayList<>();
		this.goToClassSchedule();
		this.driver.manage().window().maximize();
		this.selectTerm(season, year);
		WebElement submitButton = this.driver.findElement(By.cssSelector("input[value=Submit]"));
		WebElement subjectMenu = this.driver.findElement(By.id("subjct_id"));
		WebElement all = subjectMenu.findElement(By.cssSelector("option:nth-child(1)"));
		WebElement subject = subjectMenu.findElement(By.cssSelector("option[value=" + subjectCode + "]"));
		all.click();
		subject.click();
		submitButton.click();
		courses.addAll(this.getCoursesFromPage());
		this.driver.close();
		this.driver.switchTo().window("");
		return courses;
	}
}