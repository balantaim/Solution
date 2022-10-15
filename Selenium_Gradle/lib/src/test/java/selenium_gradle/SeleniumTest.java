package selenium_gradle;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.sym.Name;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SeleniumTest {
	
	//Gradle build with TestNG plug-in
	
	//Global variables declaration
	public static WebDriver driver;
	public static final String URL = "https://testpages.herokuapp.com/styled/basic-html-form-test.html";
	
	@BeforeTest
	public void initStart() {
		//I use io.github.bonigarcia to setup the chromedriver automatically
		WebDriverManager.chromedriver().setup();
	
		//Set up driver to work with Chrome web browser
		driver = new ChromeDriver();
		//Set startup parameters -> window full screen, time out, delete all cookies...
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
	}

	@Test
	public void testAutomate() throws InterruptedException{
		final String uName = "Ivan Ivanov", uPass = "Password9876543210", 
				uComment = "Ivan Kamenov Ivanov is a Bulgarian former professional "
						+ "footballer who played as a centre-back and now a manager of Slavia Sofia II "
						+ "and assistant to Bulgaria national team.";
		
		//Load the website from the url
		driver.get(URL);
		Thread.sleep(2000);
		
		//Find the element by css selector
		WebElement userNameElement = driver.findElement(By.cssSelector("#HTMLFormElements > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(2)"));
		//send data to the field
		userNameElement.sendKeys(uName);
		//Find the element by xPath
		WebElement userPassElement = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[2]/td/input"));
		userPassElement.sendKeys(uPass);
		//Find the element with css path
		WebElement userCommentElement = driver.findElement(By.cssSelector("html body div.page-body div.centered form#HTMLFormElements table tbody tr td textarea"));
		//Clear the old string
		userCommentElement.clear();
		userCommentElement.sendKeys(uComment);
		//manage new checkbox, radio button, multiple choice selection
		WebElement checkBox1 = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[5]/td/input[1]"));
		checkBox1.click();
		WebElement checkBox3 = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[5]/td/input[3]"));
		checkBox3.click();
		WebElement radioItem3 = driver.findElement(By.cssSelector("#HTMLFormElements > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(1) > input:nth-child(4)"));
		radioItem3.click();
		WebElement multySelectValue1 = driver.findElement(By.xpath("/html/body/div/div[3]/form/table/tbody/tr[7]/td/select/option[1]"));
		multySelectValue1.click();
		//Create new select element
		Select dropdownSelect = new Select(driver.findElement(By.name("dropdown")));
		//Select from items by visible text marker
		dropdownSelect.selectByVisibleText("Drop Down Item 4");
		//This delay is set for debugging purpose
		Thread.sleep(3000);
		//Submit the data
		WebElement btnSubmit = driver.findElement(By.cssSelector("input.styled-click-button:nth-child(2)"));
		btnSubmit.click();
		//This delay is set for debugging purpose
		Thread.sleep(2000);
		
		//Select element by ID
		WebElement checkNameElement= driver.findElement(By.id("_valueusername"));
		String checkNameData= checkNameElement.getText();
		//This is example how to check if the input is correct 
		Assert.assertEquals(uName, checkNameData);
	}

	
	@AfterTest
	public void endTest() {
		//stop the browser
		driver.close();
	}
}
