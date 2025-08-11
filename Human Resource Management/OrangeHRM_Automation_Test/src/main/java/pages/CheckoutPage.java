package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.java.utils.WaitUtils;

public class CheckoutPage {
    private WebDriver driver;
    private WaitUtils waitUtil;

    private By firstNameInput = By.id("firstNameInput");
    private By lastNameInput = By.id("lastNameInput");
    private By addressLine1Input = By.id("addressLine1Input");
    private By provinceInput = By.id("provinceInput");
    private By postCodeInput = By.id("postCodeInput");
    private By submitBtn = By.id("checkout-shipping-continue");
    private By confirmationMessage = By.id("confirmation-message");

    public CheckoutPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public void clickPurchase() {
    	driver.findElement(submitBtn).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public void fillCheckoutForm(String firstName, String lastName, String addressLine1,
    		String province, String postCode) {
    	driver.findElement(firstNameInput).sendKeys(firstName);
    	driver.findElement(lastNameInput).sendKeys(lastName);
    	driver.findElement(addressLine1Input).sendKeys(addressLine1);
    	driver.findElement(provinceInput).sendKeys(province);
    	driver.findElement(postCodeInput).sendKeys(postCode);    	
    }
}
