package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import main.java.utils.WaitUtils;

import java.util.List;

public class SearchUserPage {
    private WebDriver driver;
    private WaitUtils waitUtil;

    private By usrenameInput = By.className("oxd-input");    
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/../following-sibling::div//div[contains(@class,'select-text')]");
    private By dropdownOptions = By.xpath("//div[@role='listbox']//span");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By resultRows = By.cssSelector(".oxd-table-body .oxd-table-card");
    private By statusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'select-text')]");
    private By statusOptions = By.xpath("//div[@role='listbox']//span");

    
    public SearchUserPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public int SearchByUserName(String username) {
    	List<WebElement> allInputs = driver.findElements(usrenameInput);
    	WebElement[] arrayInputs = new WebElement[allInputs.size()];
    	WebElement elem  = allInputs.toArray(arrayInputs)[1];//username input textbox
    	
    	elem.click();
		elem.sendKeys(username);//enter input
		driver.findElement(searchButton).click();//search
		waitUtil.sleep();
		return driver.findElements(resultRows).size();
    }
    public int SearchByUserRole(String roleName) {
        driver.findElement(userRoleDropdown).click();
        waitUtil.waitForVisibility(dropdownOptions);
        for (WebElement option : driver.findElements(dropdownOptions)) {
            if (option.getText().equalsIgnoreCase(roleName)) {
                option.click();
                driver.findElement(searchButton).click();//search
        		waitUtil.sleep();
        		return driver.findElements(resultRows).size();
            }
        }

    	return 0;
    }

    public int SearchByUserStatus(String statusText) {
        driver.findElement(statusDropdown).click();
        waitUtil.waitForVisibility(statusOptions);

        for (WebElement option : driver.findElements(statusOptions)) {
            if (option.getText().equalsIgnoreCase(statusText)) {
                option.click();
                driver.findElement(searchButton).click();//search
        		waitUtil.sleep();
        		return driver.findElements(resultRows).size();
            }
        }

    	return 0;
    }


    public void refreshPage() {
        driver.navigate().refresh();
    }

}
