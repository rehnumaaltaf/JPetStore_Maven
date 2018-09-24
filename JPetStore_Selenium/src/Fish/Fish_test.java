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
import org.openqa.selenium.remote.DesiredCapabilities;

public class Fish_test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
  //  driver = new FirefoxDriver();
   // System.setProperty("webdriver.ie.driver", "D:\\Downloads\\IEDriverServer_Win32_2.31.0\\IEDriverServer.exe");
	// System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shruthi_Mathews\\Downloads\\chromedriver_win32_2.1\\chromedriver.exe");
	DesiredCapabilities dc= DesiredCapabilities.internetExplorer();

		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver= new  InternetExplorerDriver(dc);
    
    
    driver.manage().window().maximize();
    
    
    
    //driver.get("http://blrkec328010d:8013/JPetStore/");
	driver.get("http://blrkec103517l:8088/JPetStore/");
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled() throws Exception {
    
    assertEquals("JPetStore Demo", driver.getTitle());
    driver.findElement(By.name("keyword")).clear();
    driver.findElement(By.name("keyword")).sendKeys("FISH");
    driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
    driver.findElement(By.linkText("Salt Water fish from Australia")).click();
    driver.findElement(By.linkText("EST-1")).click();
    driver.findElement(By.xpath("//tr[7]/td/a/img")).click();
    driver.findElement(By.name("update")).click();
    driver.findElement(By.name("EST-1")).clear();
    driver.findElement(By.name("EST-1")).sendKeys("4");
    driver.findElement(By.name("update")).click();
    driver.findElement(By.xpath("//td[2]/center/a/img")).click();
    driver.findElement(By.xpath("//center[2]/a/img")).click();
    driver.findElement(By.xpath("(//input[@type='image'])[2]")).click();
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
   /* String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }*/
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
