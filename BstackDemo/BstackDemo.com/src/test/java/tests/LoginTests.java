package test.java.tests;

import com.aventstack.extentreports.Status;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.pages.*;

public class LoginTests extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        test = extent.createTest("Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver, waitUtil);

        loginPage.login("demouser", "testingisfun99");

        // Check for successful login: dashboard displays logged in username
        ProductPage homePage = new ProductPage(driver, waitUtil);
        String loggedInUserName = homePage.getLoggedInUserName();
        test.log(Status.INFO, "Logged in UserName: " + loggedInUserName);
        Assert.assertEquals(loggedInUserName,"demouser", "Login probably failed, cart count is invalid");
        test.log(Status.PASS, "Login with valid credentials passed");
    }

    @Test
    public void loginWithInvalidCredentials() {
        test = extent.createTest("Login with invalid credentials");
        LoginPage loginPage = new LoginPage(driver, waitUtil);

        loginPage.login("wronguser", "wrongpassword");

        String error = loginPage.getErrorMessage();
        test.log(Status.INFO, "Error message displayed: " + error);
        Assert.assertTrue(error.contains("Invalid") || !error.isEmpty(), "Error message should be displayed");
        test.log(Status.PASS, "Login with invalid credentials test passed");
    }

    @Test
    public void loginWithEmptyUsernamePassword() {
        test = extent.createTest("Login with empty username and password");
        LoginPage loginPage = new LoginPage(driver, waitUtil);

        loginPage.login("", "");

        String error = loginPage.getErrorMessage();
        test.log(Status.INFO, "Error message displayed: " + error);
        Assert.assertTrue(error.contains("required") || !error.isEmpty(), "Error message should be displayed for empty input");
        test.log(Status.PASS, "Login with empty credentials test passed");
    }
}
