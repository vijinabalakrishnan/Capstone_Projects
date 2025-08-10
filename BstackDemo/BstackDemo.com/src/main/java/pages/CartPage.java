package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import main.java.utils.WaitUtils;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WaitUtils waitUtil;
    
    private By cartCount = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[1]/span[1]/span");
    private By removeButtons = By.cssSelector(".shelf-item__del");
    private By checkoutButton = By.className("buy-btn");
    

    public CartPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public int getCartItemCount() {
        waitUtil.waitForVisibility(cartCount);
    	String countText = driver.findElement(cartCount).getText();
        try {
        	return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            return 0;
        }  
    }

    public void removeItem(int index) {
        List<WebElement> removes = driver.findElements(removeButtons);
        if (index < removes.size()) {
            removes.get(index).click();
        }
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
