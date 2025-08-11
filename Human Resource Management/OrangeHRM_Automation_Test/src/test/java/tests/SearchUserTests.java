package test.java.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.java.pages.*;

public class SearchUserTests extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver, waitUtil);
        loginPage.login("Admin", "admin123");
    }

    @Test
    public void searchByUserName() {
    	String keyword = "Admin";
        test = extent.createTest("Search for admin user using SearchByUserName()");
        DashboardPage dashBoardPage = new DashboardPage(driver, waitUtil);

        dashBoardPage.openAdminMenu();
        
        SearchUserPage searchUserPage = new SearchUserPage(driver, waitUtil);
        int cntResults = searchUserPage.SearchByUserName(keyword);
        String screenshotPath = captureScreenshot("Search_Username_"+keyword);

        Assert.assertTrue(cntResults>0,"Failed: searchByUserName() test failed. No of users found for '"+keyword+"' is "+cntResults);
        if(cntResults > 0)
        {
        	test.log(Status.PASS, "searchByUserName() test passed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        else
        {
        	test.log(Status.FAIL, "Failed: searchByUserName() test failed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        searchUserPage.refreshPage();
        test.log(Status.PASS, "Page refreshed");
    }
    
    @Test
    public void searchByUserRoleDropdown() {
    	String keyword = "Admin";
        test = extent.createTest("Search for admin user using SearchByRole() dropdown");
        DashboardPage dashBoardPage = new DashboardPage(driver, waitUtil);

        dashBoardPage.openAdminMenu();
        
        SearchUserPage searchUserPage = new SearchUserPage(driver, waitUtil);
        int cntResults = searchUserPage.SearchByUserRole(keyword);
        String screenshotPath = captureScreenshot("Search_User_Role");

        Assert.assertTrue(cntResults>0,"Failed: SearchByRole() dropdown test failed. No of users found for '"+keyword+"' is "+cntResults);
        if(cntResults > 0)
        {
        	test.log(Status.PASS, "SearchByRole() dropdown test passed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        else
        {
        	test.log(Status.FAIL, "Failed: SearchByRole() dropdown test failed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        searchUserPage.refreshPage();
        test.log(Status.PASS, "Page refreshed");
    }
    @Test
    public void searchByUserStatusDropdown() {
    	String keyword = "Enabled";//Enabled status
        test = extent.createTest("Search for admin user using SearchByUserStatus() dropdown");
        DashboardPage dashBoardPage = new DashboardPage(driver, waitUtil);

        dashBoardPage.openAdminMenu();
        
        SearchUserPage searchUserPage = new SearchUserPage(driver, waitUtil);
        int cntResults = searchUserPage.SearchByUserStatus(keyword);
        String screenshotPath = captureScreenshot("Search_User_Status");

        Assert.assertTrue(cntResults>0,"Failed: SearchByUserStatus() dropdown test failed. No of users found for '"+keyword+"' is "+cntResults);
        if(cntResults > 0)
        {
        	test.log(Status.PASS, "SearchByUserStatus() dropdown test passed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        else
        {
        	test.log(Status.FAIL, "Failed: SearchByUserStatus() dropdown test failed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        searchUserPage.refreshPage();
        test.log(Status.PASS, "Page refreshed");
    }
    
    @Test
    public void searchByUserStatusDropdownDisabled() {
    	String keyword = "Disabled";//Disabled status
        test = extent.createTest("Search for admin user using SearchByUserStatus() dropdown");
        DashboardPage dashBoardPage = new DashboardPage(driver, waitUtil);

        dashBoardPage.openAdminMenu();
        
        SearchUserPage searchUserPage = new SearchUserPage(driver, waitUtil);
        int cntResults = searchUserPage.SearchByUserStatus(keyword);
        String screenshotPath = captureScreenshot("Search_User_Status");

        Assert.assertTrue(cntResults>0,"Failed: SearchByUserStatus() dropdown test failed. No of users found for '"+keyword+"' is "+cntResults);
        if(cntResults > 0)
        {
        	test.log(Status.PASS, "SearchByUserStatus() dropdown test passed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        else
        {
        	test.log(Status.FAIL, "Failed: SearchByUserStatus() dropdown test failed. No of users found  for '"+keyword+"' is "+cntResults).addScreenCaptureFromPath(screenshotPath);
        }
        searchUserPage.refreshPage();
        test.log(Status.PASS, "Page refreshed");
    }
}
