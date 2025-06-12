package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class C13_VerifyProductQuantityInCard {
    @Test
    public void test13() {

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

        // Click 'View Product' for any product on home page
        js.executeScript("arguments[0].scrollIntoView(true);", locates.firstProductsViewProductButton);
        locates.firstProductsViewProductButton.click();

        // Increase quantity to 4
        locates.quantityAreaInViewProduct.click();
        locates.quantityAreaInViewProduct.sendKeys(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP);

        // Click 'Add to cart' button
        locates.addToCartButtonInViewProduct.click();

        // Click 'View Cart' button
        wait.until(ExpectedConditions.visibilityOf(locates.viewCartButton));
        locates.viewCartButton.click();

        // Verify that product is displayed in cart page with exact quantity
        String actualQuantity = locates.firstProductQuantityInCart.getText();
        String expectedQuantity = "4";
        softAssert.assertEquals(expectedQuantity, actualQuantity);

        softAssert.assertAll();
    }
}
