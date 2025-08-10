package test.java.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import main.java.utils.ConfigReader;
import main.java.utils.WaitUtils;
import main.java.utils.WebDriverFactory;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WaitUtils waitUtil;
    
    @BeforeSuite
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }

    @BeforeMethod
    public void setupDriver() {    	
    	String browser = ConfigReader.getOrDefault("browser", "chrome");    	
    	System.setProperty(ConfigReader.get("driverClass"), 
    			ConfigReader.get("driverPath"));
		driver = WebDriverFactory.createDriver(browser);
		driver.get(ConfigReader.get("baseUrl"));
		
		waitUtil = new WaitUtils(driver,5);
    }

    @AfterMethod
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
}
