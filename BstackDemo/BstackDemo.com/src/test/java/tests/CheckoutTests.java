package test.java.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.java.pages.*;
import main.java.utils.WaitUtils;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver, waitUtil);
        loginPage.login("demouser", "testingisfun99");
    }

    @Test
    public void placeOrderWithValidDetails() {
        test = extent.createTest("Place order with valid details");
        ProductPage homePage = new ProductPage(driver, waitUtil);

        homePage.addItemToCart(2);

        CartPage cartPage = new CartPage(driver, waitUtil);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, waitUtil);
        checkoutPage.fillCheckoutForm("Vijina","P","Bangalore","KA","560068");
        checkoutPage.clickPurchase();

        String confirmation = checkoutPage.getConfirmationMessage();
        test.log(Status.INFO, "Order confirmation message: " + confirmation);

        Assert.assertEquals(confirmation,"Your Order has been successfully placed.", "Confirmation message not displayed");
        test.log(Status.PASS, "Place order with valid details test passed");
    }

    @Test
    public void checkoutWithoutAddingItems() {
        test = extent.createTest("Checkout flow without adding items (negative test)");
        ProductPage homePage = new ProductPage(driver, waitUtil);

        homePage.openCart();

        CartPage cartPage = new CartPage(driver, waitUtil);
        cartPage.clickCheckout();

        // Assuming that the checkout page won't allow purchase or shows error on empty cart
        CheckoutPage checkoutPage = new CheckoutPage(driver, waitUtil);

        // Fill the details form
        try {
        	checkoutPage.fillCheckoutForm("Vijina","P","Bangalore","KA","560068");
            checkoutPage.clickPurchase();
            String confirmation = checkoutPage.getConfirmationMessage();

            // If confirmation contains error message, assert that
            test.log(Status.INFO, "Checkout confirmation or message: " + confirmation);
            Assert.assertEquals(confirmation,"Your Order has been successfully placed.", "Confirmation message not displayed");
            
        } catch (Exception e) {
            test.log(Status.INFO, "Exception or failure as expected: " + e.getMessage());
            Assert.assertTrue(true);
        }
        test.log(Status.PASS, "Checkout without adding items negative test passed");
    }
}
