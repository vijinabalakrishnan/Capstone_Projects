package test.java.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.java.pages.*;

public class AddToCartTests extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver, waitUtil);
        loginPage.login("demouser", "testingisfun99");
    }

    @Test
    public void addSingleItemToCart() {
        test = extent.createTest("Add single item to cart");
        ProductPage homePage = new ProductPage(driver, waitUtil);

        homePage.addItemToCart(0);
        int cartCount = homePage.getCartCount();
        test.log(Status.INFO, "Cart count after adding one item: " + cartCount);

        Assert.assertEquals(cartCount, 1);
        test.log(Status.PASS, "Add single item to cart test passed");
    }

    @Test
    public void addMultipleItemsToCartAndVerifyCount() {
        test = extent.createTest("Add multiple items to cart and verify count");
        ProductPage homePage = new ProductPage(driver, waitUtil);

        homePage.addItemToCart(0);
        homePage.addItemToCart(1);
        int cartCount = homePage.getCartCount();
        test.log(Status.INFO, "Cart count after adding two items: " + cartCount);

        Assert.assertEquals(cartCount, 2);
        test.log(Status.PASS, "Add multiple items to cart and verify count test passed");
    }

    @Test
    public void removeItemFromCart() {
        test = extent.createTest("Remove item from cart");
        ProductPage homePage = new ProductPage(driver, waitUtil);

        homePage.addItemToCart(0);
        homePage.addItemToCart(1);

        CartPage cartPage = new CartPage(driver, waitUtil);
        int beforeRemoveCount = cartPage.getCartItemCount();
        test.log(Status.INFO, "Cart items before removal: " + beforeRemoveCount);

        cartPage.removeItem(0);

        int afterRemoveCount = cartPage.getCartItemCount();
        test.log(Status.INFO, "Cart items after removal: " + afterRemoveCount);

        Assert.assertEquals(afterRemoveCount, beforeRemoveCount - 1);
        test.log(Status.PASS, "Remove item from cart test passed");
    }
}
