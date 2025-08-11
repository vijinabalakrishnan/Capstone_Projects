package main.java.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.java.utils.WaitUtils;

public class DashboardPage {
	private WebDriver driver;
    private WaitUtils waitUtil;  
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    By navMenu = By.className("oxd-userdropdown-tab");
    By logoutBtn = By.cssSelector("a[href='/web/index.php/auth/logout']");
    By leftNavMenuBtn = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/div/div/button");
    By leftNavMenu = By.className("oxd-main-menu");
    By navMenuItem = By.className("oxd-main-menu-item-wrapper");
    By navMenuItemSpan = By.className("oxd-main-menu-item--name");
    
    public DashboardPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public boolean isDashboardDisplayed() {
        return driver.findElements(dashboardHeader).size() > 0;
    }
    
    public boolean logoutUser() {
	     driver.findElement(navMenu).click();
	     waitUtil.sleep();
	     waitUtil.waitForVisibility(logoutBtn);
	     driver.findElement(logoutBtn).click();
	     waitUtil.sleep();
	     return true;
    }
    
    public int getLeftNavMenuCount() {
	     driver.findElement(leftNavMenuBtn).click();
	     waitUtil.sleep();
	     waitUtil.waitForVisibility(leftNavMenu);
	     waitUtil.sleep();
	     return driver.findElements(navMenuItem).size();
   }
    
    public void openAdminMenu() {
	     List<WebElement> elems = driver.findElements(navMenuItem);
	     for (WebElement elem : elems) {
	    	 if (elem.getText().equals("Admin")) {
	    	    	elem.click();
	    	    	waitUtil.sleep();
	    	    	return;
	    	    }
	    	}
  }
}

