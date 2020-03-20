package RolexTestCase;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import PageObject.RolexPage;
import java.util.LinkedHashMap;
import resources.Base;


public class Rolex extends Base {
	
	public static Logger log =LogManager.getLogger(Base.class.getName());
	
	RolexPage rolexPage;
	
	@BeforeTest
	public void driverInitialize() throws IOException
	{
		   	driver=initializedriver();
			rolexPage=new RolexPage(this.driver);
			this.driver.get(prop.getProperty("url"));
			log.info("initialized driver");
			
	}
	
	
	@Test
	public void WatchViewed() {
		 
		   Map<String,Integer> map=new HashMap<String,Integer>();
		   int link_count=0; 
		   int page_counter=1;
		
		   int watch_viewed=0;
		   
		   Instant start = Instant.now();
		   log.info("Start time "+start);
		   while(watch_viewed<250) {
        	  
        	  //Check watch link exist
        	  if(rolexPage.WatchLinkExist(link_count)>0) 
        	  {
        	        rolexPage.ClickOnWatchLink(link_count);
        	         
        	        link_count++;
        	  }
        	  else
        	  {
        		    page_counter++;
        		    //page exist to click on next page
        		    if(rolexPage.PageExist(page_counter)>0)
        		    {
       	                    rolexPage.ClickOnNextPage(page_counter);
       	                    link_count=0;
       			             
       			             continue;
       			    }  
        		    else 
        		    {
       			     log.info("No more watch");
       			     break;
       		        }
        	}
        	
        	
        	try
        	{
        		/*if there is view available on the current page
        		 *get the string  
        		 *parse the no views from the string and 
        		 *convert it in to digit
        		 * 
        		 */
        		 String s=rolexPage.WatchViews();
        		 int j=0;
        		 int digit=0;
        		 while(s.charAt(j)!=' ')
        		 {
        		     digit=digit*10+(s.charAt(j)-'0') ;
        			 j++; 
        		 }
        		
        		
        		// add no of views and url into map
                
        		 map.put(driver.getCurrentUrl(), digit);
        		
        		 log.info("views"+" "+digit+" "+driver.getCurrentUrl());
        		
        	}
        	
        	//if there is no view available it will throw an exception
        	catch(Exception e)
        	{
        		map.put(driver.getCurrentUrl(), 0);
        		log.info("Views 0 "+driver.getCurrentUrl());
        	
        	}
        	
        	//will navigate back to the main page 
        	finally
        	{
        		
        		watch_viewed++;
        	   driver.navigate().back();
        	   
        	}
        	
        }
		   
		   Instant finish = Instant.now();
	       
		   long timeElapsed = Duration.between(start, finish).toMinutes();
		   log.info("End Time  "+finish);
	       System.out.println() ;
	       System.out.println() ;
	       System.out.println() ;
	       System.out.println("--------------------------------------------------------------------------------------------------") ;
	       System.out.println("                                   REPORT                                                         ") ;
	       System.out.println("---------------------------------------------------------------------------------------------------") ;
	       System.out.println("*****URL VISTITED FROM"+start+" to"+finish+"*********" );
	       
	       System.out.println("Total time in minutes:" +timeElapsed);
	       System.out.println("---------------------------------------------------------------------------------------------------") ;
		   System.out.println("No of views"+"\t"+"Url");
		   for(Map.Entry<String, Integer> output: map.entrySet())
	          {
	      	   
	      	       System.out.println(output.getValue()+"\t"+"\t"+output.getKey());
	      	      
	          
	  	      }
	
		   System.out.println() ;
	       System.out.println() ;
	       System.out.println() ;
	 
          int watch_tracker=0;
          System.out.println("--------------------------------------------------------------------------------------------------");
          System.out.println("                           higest viewed watches in last 1 hr" );
	      System.out.println("--------------------------------------------------------------------------------------------------");
     
          System.out.println("Most Viewed watches"+"\t"+"Url");
           
           //Sort map in decending order by map values
           Map<String, Integer> reverse = map.entrySet()
                   .stream()
                   .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
           
        
           //Print first five most viewed watches in 1 hr  
          for(Map.Entry<String, Integer> output: reverse.entrySet())
          {
      	   
      	       /**
      	        * if watch_tracker is greater than 5 or there is no watches with more than 0 review it
        	    *it will break the loop
        	   */
      	       if(watch_tracker==5 || output.getValue()==0) 
      	       {
      	             break;
               }
      	       else {
      	    	   System.out.println(output.getValue()+"\t"+"\t"+output.getKey()); 
      	           watch_tracker++;
               }
          
  	      }
           System.out.println("--------------------------------------------------------------------------------------------------");
           System.out.println() ;
	       System.out.println() ;
	       System.out.println() ;
           
      }
	
      @AfterMethod
      public void BrowserClose() {
    	  driver.close();
    	  driver=null;
      }
      


}
	
	
 

