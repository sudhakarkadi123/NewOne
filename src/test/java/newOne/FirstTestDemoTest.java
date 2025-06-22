package newOne;


import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class FirstTestDemoTest {
	
		ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
		@Test @Parameters({"browser","URL","FFURL"})
		public void openBrowserPage(String browser,String URL,String FFURL) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		WebDriver driver = null;
		//String browser = System.getProperty("browser");
		System.out.println("BrowerName   " + browser);
		System.out.println("URL Name    " + URL);
		System.out.println("URL Name for FireFox   " + FFURL);
		ChromeOptions options = new ChromeOptions();
		if ("chrome".equalsIgnoreCase(browser)) {
		    driver = new ChromeDriver(options);
		    HasAuthentication authentication = (HasAuthentication) driver; 
			authentication.register(()-> new UsernameAndPassword("gr33n", "y3ll0w"));
		} else {
			 
		    driver = new FirefoxDriver();

		    }
		   
		   tdriver.set(driver);
			   driver = tdriver.get();
			   if ("chrome".equalsIgnoreCase(browser)) {
				   driver.get(URL);
			   } else {
				   driver.get(FFURL);
			   }
			   
			   driver.manage().window().maximize();
			  		
			// Set up WebDriverWait with timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		
		// Wait until the element is visible
		wait.until(
		    
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Search']"))
		);
		System.out.println("Browsers Titile is   " + driver.getTitle());//.contentEquals("Google");
		int number_pages = driver.findElements(By.cssSelector("ul.NPagination-items > li")).size();
		
		System.out.println("Number of Pages are  : " + number_pages);
		
		for (int p=2;p<=number_pages;p++) {
			driver.findElement(By.cssSelector("ul.NPagination-items >li:nth-child("+p+")")).click();
		}
		    
		//driver.close();
		tdriver.get().quit();
		tdriver.remove(); // Important to remove the driver from ThreadLocal
	}
	

}	
