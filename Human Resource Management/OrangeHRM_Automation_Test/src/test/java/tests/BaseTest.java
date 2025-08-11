package test.java.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import main.java.utils.WaitUtils;
import main.java.utils.WebDriverFactory;
import com.aventstack.extentreports.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    	System.setProperty("webdriver.chrome.driver", 
    			"D:\\Learning\\Java\\Projects\\path\\to\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		driver = WebDriverFactory.createDriver("chrome");
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		waitUtil = new WaitUtils(driver,5);
    }

    @AfterMethod
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
    
    public String captureScreenshot(String testName) {
        String filePath = "screenshots/" + testName + "_" + timestamp() + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            fos.write(src);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private String timestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

}
