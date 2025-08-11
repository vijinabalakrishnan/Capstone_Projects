package test.java.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.java.pages.*;
import main.java.utils.WaitUtils;

public class DashboardTests extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver, waitUtil);
        loginPage.login("Admin", "admin123");
    }

    @Test
    public void checkLeftMenuItemsCount() {
        test = extent.createTest("Check left menu items count");
        DashboardPage dashBoardPage = new DashboardPage(driver, waitUtil);

        int cnt = dashBoardPage.getLeftNavMenuCount();
        String screenshotPath = captureScreenshot("Dashboard_Menu_Count");

        Assert.assertEquals(cnt,12,"Left menu item count was not 12. It was "+cnt+" instead.");
        if(cnt == 12)
        {
        	test.log(Status.PASS, "Left menu item count test passed. "+cnt+" menu items found").addScreenCaptureFromPath(screenshotPath);;
        }
        else
        {
        	test.log(Status.FAIL, "Left menu item count test is not passed. Menu count is: "+cnt+" instead of 12").addScreenCaptureFromPath(screenshotPath);;
        }
    }
}
