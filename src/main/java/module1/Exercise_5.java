package module1;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Exercise_5 {
	
	WebDriver driver;
	@Before
	public void browser_launch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
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
		driver.get("https://www.saucedemo.com/");
		WebElement drop=driver.findElement(By.id("login-button"));
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(drop));
		
		//Verify Page title
		String Expected_title = "Swag Labs";
		String page_title = driver.getTitle();
		
		if(Expected_title.equals(page_title)) {
			System.out.println("Title Verified");
		}
		else {
			System.out.println("Title Verification Failed");
		}
		Assert.assertEquals(Expected_title,page_title);
		
		
		//Verify the login button text is capitalized
		
		String login_text = driver.findElement(By.id("login-button")).getAttribute("value");
		if(login_text.matches("Login")) {
			System.out.println("Text is Capitalized");
		}
		//Login with standard_user & secret_sauce
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Verify default filter dropdown is A-Z
		
		Select drp = new Select((driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		WebElement opt=drp.getFirstSelectedOption();
		String act_value = opt.getText();
		System.out.println(act_value);
		String exp_value="Name (A to Z)";
		Assert.assertEquals(exp_value,act_value);
		
		//Add the first product to the cart
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
		
		//Verify the cart badge has 1 product
		String act_cart = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).getText();
		String exp_cart = "1";
		try {
			Assert.assertEquals(exp_cart,act_cart);
		}
		catch (Exception e) {
			 Log error;
		}
		
		//Add the last product to the cart
		driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		String sec_act_cart = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).getText();
		String sec_exp_cart = "2";
		Assert.assertEquals(sec_exp_cart,sec_act_cart);
		
		//Remove the first product from the cart
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		driver.findElement(By.name("continue-shopping")).click();
		
		//Verify the cart badge has 1 product
		String After_removal = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")).getText();
		Assert.assertEquals(exp_cart,After_removal);
		
		//Click on the cart
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
		
		//Verify the added product is available
		String cart_product = driver.findElement(By.cssSelector(".cart_quantity")).getText();
		if(cart_product.contains("1")) {
			System.out.println("Added Product is available");
		}
		else {
			System.out.println("Added Product is not available");
		}
		
		//Click on the continue shopping
		driver.findElement(By.name("continue-shopping")).click();
		
		//Change the price filter from low to high
		Select drp2 = new Select((driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		drp2.selectByVisibleText("Price (low to high)");
		
		//Verify the price sorted properly
		Select drp3 = new Select((driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		WebElement opt2 = drp3.getFirstSelectedOption();
		String price_low_exp = "Price (low to high)";
		String price_low_act = opt2.getText();
		System.out.println(price_low_act);
		Assert.assertEquals(price_low_exp,price_low_act);		
		
	}
}

	
