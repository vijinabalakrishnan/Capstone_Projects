package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        test = extent.createTest("Login Test with user: " + username);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver);
        String screenshotPath = captureScreenshot(username);

        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(dashboardPage.isDashboardDisplayed());
            test.pass("Valid login successful").addScreenCaptureFromPath(screenshotPath);
        } else {
            Assert.assertFalse(dashboardPage.isDashboardDisplayed());
            test.fail("Invalid login as expected").addScreenCaptureFromPath(screenshotPath);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        return ExcelUtils.getData("data/loginData.xlsx", "Sheet1");
    }
}
