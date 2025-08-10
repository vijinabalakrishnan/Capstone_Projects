package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.java.utils.WaitUtils;

import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WaitUtils waitUtil;
    
    private By products = By.cssSelector(".shelf-item");
    private By addToCartButton = By.cssSelector(".shelf-item__buy-btn");
    private By cartCount = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[1]/span[1]/span");
    private By cartIcon = By.className("bag--float-cart-closed");

    public ProductPage(WebDriver driver, WaitUtils waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
    }

    public void addItemToCart(int index) {
        List<WebElement> items = driver.findElements(products);
        if (index < items.size()) {
            items.get(index).findElement(addToCartButton).click();
        }
    }

    public int getCartCount() {
        waitUtil.waitForVisibility(cartCount);
        String countText = driver.findElement(cartCount).getText();
        try {
        	return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            return 0;
        }        
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
    
    public String getLoggedInUserName() {
        waitUtil.waitForVisibility(By.className("username"));
    	return driver.findElement(By.className("username")).getText();
    }
}
