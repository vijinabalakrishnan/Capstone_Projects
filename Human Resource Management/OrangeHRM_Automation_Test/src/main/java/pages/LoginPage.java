package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.java.utils.WaitUtils;

public class LoginPage {
	private WebDriver driver;	
    private WaitUtils waitUtil;  
    
    By username = By.name("username");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[@type='submit']");
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By errorMessage = By.className("api-error");  
    
    public LoginPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public boolean isDashboardDisplayed() {
        return driver.findElements(dashboardHeader).size() > 0;
    }

    public void login(String user, String pass) {
    	driver.findElement(username).sendKeys(user);
    	driver.findElement(password).sendKeys(pass);
    	waitUtil.waitForClickable(loginBtn);
    	driver.findElement(loginBtn).click();
    }
}
