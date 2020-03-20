package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	public WebDriver driver;
	public  Properties prop=new Properties();
	
	 public String currentDirectory = System.getProperty("user.dir");
	 public String browserName=System.getProperty("BrowserName");
	 public String headless=System.getProperty("Headless");
	
	
	
		
	
	public WebDriver initializedriver() throws IOException {
		
		 FileInputStream fos=new FileInputStream(currentDirectory+"\\src\\main\\java\\resources\\ebay.properties");
		 prop.load(fos);
		
		 //chrome browser with headless mode
		 if(browserName.equals("chrome") && headless.equals("headless")) {
				System.setProperty("webdriver.chrome.driver", currentDirectory+"\\WebDriver\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                
                
				driver =new ChromeDriver(options);
		        
		        
			}
		 //Firefox browser with headlessmode
		 else if(browserName.equals("firefox") && headless.equals("headless")) {
			     FirefoxOptions options = new FirefoxOptions();
				 options.setHeadless(true);
				 System.setProperty("webdriver.gecko.driver",currentDirectory+"\\WebDriver\\geckodriver.exe");
				//Instantiate Web Driver
				 driver = new FirefoxDriver(options);
		        
		}
		//chrome browser normal mode
		 else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentDirectory+"\\WebDriver\\chromedriver.exe");
			driver =new ChromeDriver();
	        
	        
		}
	 //firefox browser normal mode
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",currentDirectory+"\\WebDriver\\geckodriver.exe");
			driver =new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(currentDirectory+"\\ScreenShot\\"+result+"screenshot.png"));
		
	}

}
