package tests;

import org.openqa.selenium.By;
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

public class C08_VerifyAllProductsAndProductDetailPage {
    @Test
    public void test08() {

        Locates locates = new Locates();
        Actions action = new Actions(Driver.getDriver());
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

        // Click on 'Products' button
        locates.homePageProductsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        String expectedAllProductsTitle = "Automation Exercise - All Products";
        String actualAllProductsTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedAllProductsTitle, actualAllProductsTitle);

        softAssert.assertTrue(locates.productsSearchArea.isDisplayed());

        // The products list is visible
        softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());

        // Click on 'View Product' of first product
        action.sendKeys(Keys.ARROW_DOWN);
        locates.firstProductsViewProductButton.click();

        // User is landed to product detail page
        String actualProductDetailTitle = Driver.getDriver().getTitle();
        String expectedProductDetailsTitle = "Automation Exercise - Product Details";
        softAssert.assertEquals(expectedProductDetailsTitle, actualProductDetailTitle);

        softAssert.assertTrue(locates.productDetailsQuantity.isDisplayed());

        //Verify that detail detail is visible: product name, category, price, availability, condition, brand
        softAssert.assertTrue(locates.productDetailsProductName.isDisplayed());
        softAssert.assertTrue(locates.productDetailsCategoryName.isDisplayed());
        softAssert.assertTrue(locates.productDetailsPrice.isDisplayed());
        softAssert.assertTrue(locates.productDetailsAvailability.isDisplayed());
        softAssert.assertTrue(locates.productDetailsCondition.isDisplayed());
        softAssert.assertTrue(locates.productDetailsBrandName.isDisplayed());

        softAssert.assertAll();
    }
}
