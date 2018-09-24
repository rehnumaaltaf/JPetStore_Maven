package Fish;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Selenium_Sample_Test {
  private WebDriver driver;

  @Test
  public void testUntitled() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/idpslave/chromedriver");
	
//	DesiredCapabilities dc= DesiredCapabilities.internetExplorer();

//		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver= new ChromeDriver();
    
    
    driver.manage().window().maximize();
            
    driver.get("http://10.82.13.83:8080");
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    
  }
  }
  

  
