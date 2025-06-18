package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
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

public class C22_AddToCartFromRecommendedItems {
    @Test
    public void test22() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Scroll to bottom of page
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1500);

        // Verify 'RECOMMENDED ITEMS' are visible
        js.executeScript("arguments[0].scrollIntoView(true);", locates.recommendedItems);
        Thread.sleep(1500);
        softAssert.assertTrue(locates.recommendedItems.isDisplayed());

        String expectedRecommendedItemsText = "RECOMMENDED ITEMS";
        String actualRecommendedItemsText = locates.recommendedItems.getText();
        softAssert.assertEquals(actualRecommendedItemsText, expectedRecommendedItemsText);

        // Click on 'Add To Cart' on Recommended product
        if (!locates.recommendedItemsBlueTopAddToCartButton.isDisplayed()) {
            locates.recommendedItemsNextPage.click();
            Thread.sleep(1500);
            locates.recommendedItemsBlueTopAddToCartButton.click();
        } else {
            locates.recommendedItemsBlueTopAddToCartButton.click();
        }

        // Click on 'View Cart' button
        locates.viewCartButton.click();

        // Verify that product is displayed in cart page
        softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed());

        String expectedItemName = "Blue Top";
        String actualItemName = locates.productInCart.getText();
        softAssert.assertEquals(actualItemName, expectedItemName);


        softAssert.assertAll();
    }
}
