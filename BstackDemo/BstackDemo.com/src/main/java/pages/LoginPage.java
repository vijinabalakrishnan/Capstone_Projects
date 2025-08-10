package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import main.java.utils.WaitUtils;

public class LoginPage {
	private WebDriver driver;	
    private WaitUtils waitUtil;  
    private By usernameDropdown = By.id("username");
    private By passwordDropdown = By.id("password");
	private By usernameInput = By.cssSelector("#username input");
    private By passwordInput = By.cssSelector("#password input");
    private By loginButton = By.id("login-btn");
    private By errorMessage = By.className("api-error");  
    
    public LoginPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void login(String username, String password) {
    	if(username != "") 
    	{
	    	 driver.findElement(usernameDropdown).click();
			 driver.findElement(usernameInput).sendKeys(username + Keys.ENTER);
    	}
    	if(password != "") 
    	{
			 driver.findElement(passwordDropdown).click();
			 driver.findElement(passwordInput).sendKeys(password + Keys.ENTER);
    	}
		driver.findElement(loginButton).click();
    }
}
