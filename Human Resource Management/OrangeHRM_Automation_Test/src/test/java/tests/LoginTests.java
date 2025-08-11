package test.java.tests;

import com.aventstack.extentreports.Status;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import main.java.pages.*;
import main.java.utils.ExcelUtils;

public class LoginTests extends BaseTest {
    
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        test = extent.createTest("Login Test with user: " + username);
        LoginPage loginPage = new LoginPage(driver, waitUtil);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver, waitUtil);
        String screenshotPath = captureScreenshot("Login_" + username);

        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(dashboardPage.isDashboardDisplayed());
            test.pass("Valid login successful").addScreenCaptureFromPath(screenshotPath);
            
            //logout
            dashboardPage.logoutUser();
            screenshotPath = captureScreenshot("Logout_" + username);
            test.pass("Logout successful").addScreenCaptureFromPath(screenshotPath);
        } else {
            Assert.assertFalse(dashboardPage.isDashboardDisplayed());
            test.fail("Invalid login.").addScreenCaptureFromPath(screenshotPath);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        return ExcelUtils.getData("data/LoginData.xlsx", "Sheet1");
    }

}
