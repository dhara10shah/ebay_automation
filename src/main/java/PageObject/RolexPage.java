package PageObject;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author shahs
 *
 */
public class RolexPage {
	
	public WebDriver driver;
	public RolexPage(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}

	By watch_url = By.xpath("//*[@id='vi_notification_new']/span");
	
	
	 /**
     *Click on the rolex watch link
     * @param link_index of the webelement item no id.
     */

	public void ClickOnWatchLink(int link_index) {

		 driver.findElement(By.xpath("//*[@id='w7-items["+link_index+"]' or @id='w11-items["+link_index+"]'  ]/div/div[2]/a/h3")).click();
	}
	
	/**
    *check whether link is exist 
    *if link exist return value return value greater than 0
    * @param link_index of the webelement id.
    */

	
	public int WatchLinkExist(int link_index) {
		
		return driver.findElements(By.xpath("//*[@id='w7-items["+link_index+"]' or @id='w11-items["+link_index+"]'  ]/div/div[2]/a/h3")).size();
	}
	
	    /**
	    *click on next page 
	    * @param page_no page no.
	    */
	public void ClickOnNextPage(int page_no) {
		driver.findElement(By.linkText(String.valueOf(page_no))).click();
	}
	
	   /**
	    *check if page is available 
	    * @param page_no page no.
	    */    
	public int PageExist(int page_no) {
		
		return driver.findElements(By.linkText(String.valueOf(page_no))).size();
		
	}
	
	
	    /**
	    *get views of the watch in last 1 hr
	    */ 
	public String WatchViews() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(watch_url));
		return driver.findElement(watch_url).getText();
	}
}
