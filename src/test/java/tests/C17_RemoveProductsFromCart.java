package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.awt.*;
import java.time.Duration;

public class C17_RemoveProductsFromCart {
    @Test
    public void test17() throws InterruptedException {

        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());
        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Verify that home page is visible successfully
        wait.until(ExpectedConditions.visibilityOf(locates.featuresItemsText));
        softAssert.assertTrue(locates.featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedTitle, actualTitle);

        // Add products to cart
        locates.homePageProductsButton.click();
        actions.moveToElement(locates.brandBıba).perform();
        js.executeScript("arguments[0].click();", locates.firstProductAddToCartButton);
        Thread.sleep(1500);

        // Click 'Cart' button
        locates.viewCartButton.click();

        // Verify that cart page is displayed
        softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed());

        // Click 'X' button corresponding to particular product
        locates.xButtonInCart.click();

        // Verify that product is removed from the cart
        wait.until(ExpectedConditions.invisibilityOf(locates.cartPageHeader));
        String expectedCartEmptyText = "Cart is empty! Click here to buy products.";
        String actualCartEmptyText = locates.cartIsEmptyText.getText();
        softAssert.assertEquals(actualCartEmptyText,expectedCartEmptyText);
        softAssert.assertTrue(locates.cartIsEmptyText.isDisplayed());


        softAssert.assertAll();
    }
}
