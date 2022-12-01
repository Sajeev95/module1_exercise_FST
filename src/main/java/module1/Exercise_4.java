package module1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Exercise_4 {

	WebDriver driver;
	@Before
	public void browser_launch() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@After
	public void browser_close() {
		driver.quit();
	}
	
	@Test
	public void test() {
		//Launch Website
		driver.get("https://computer-database.gatling.io/computers");
		
		//Verify the title as Computers database
		String act_title = driver.getTitle();
		String exp_title = "Computers database";
		try
		{
			Assert.assertEquals(exp_title,act_title);
			System.out.println("Titled Verified");
		}
		catch(Exception e) {
			 Log error;
		}
		
		//Verify the page header is the same as the page title
		String title = "Computers database";
		String act_pageheader = driver.findElement(By.linkText("Computer database")).getText();
		
		if(exp_title.equals(act_pageheader))
		{
			System.out.println("Page header is the same as the page title");
		}
		else{
			System.out.println("Page header is the not same as the page title, Expected :"+title+"but "+act_pageheader+"is Displayed");
		}
		
		//User must see the filter by computer name text box
		if(driver.findElement(By.id("searchbox")).isDisplayed()) {
			System.out.println("Filter by computer name text box is Displayed");
		}
		else {
			System.out.println("Filter by computer name text box is Not Displayed");
		}
		
		//User able to see add a new computer button
		if(driver.findElement(By.id("add")).isDisplayed()) {
			System.out.println("Add a new computer button is Displayed");
		}
		else {
			System.out.println("Add a new computer button is Not Displayed");
		}
		
		//User able to see the filter by name button
		if(driver.findElement(By.id("searchsubmit")).isDisplayed()) {
			System.out.println("filter by name button is Displayed");
		}
		else {
			System.out.println("filter by name button is Not Displayed");
		}
		
		//User able to see the table and the headers as follows
		List<WebElement> webElementList = new ArrayList<WebElement>();
		List<WebElement> allHeadersOfTable= driver.findElements(By.xpath("//table/thead/tr[1]/th"));
		System.out.println("Headers in table are below:");
		System.out.println("Total headers found: "+allHeadersOfTable.size());
		for(WebElement header:allHeadersOfTable)
		{
			System.out.println(header.getText());
			webElementList.add(header);
		}
		Assert.assertEquals("Computer name",webElementList.get(0).getText());
		Assert.assertEquals("Introduced",webElementList.get(1).getText());
		Assert.assertEquals("Discontinued",webElementList.get(2).getText());
		Assert.assertEquals("Company",webElementList.get(3).getText());
		
		System.out.println("Table header is available as displayed");
		
		//Add new computer
		
		driver.findElement(By.xpath("//a[@id='add']")).click();
		
		//Create new Computer
		
		driver.findElement(By.xpath("//input[@class='btn primary']")).click();
		driver.findElement(By.xpath("//div[@class='clearfix error']")).isDisplayed();
		String key = "comp1";
		driver.findElement(By.id("name")).sendKeys(key);
		
		Select drp = new Select(driver.findElement(By.xpath("//div[@class='input']//select[1]")));
		drp.selectByVisibleText("Nokia");
		
		driver.findElement(By.xpath("//input[@class='btn primary']")).click();
		String exp_success = "Done ! Computer "+key+" has been created";
		
		String success = driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText();
		
		Assert.assertEquals(exp_success, success);
		
		
	}
	
}
