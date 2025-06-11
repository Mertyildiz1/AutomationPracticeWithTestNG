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

public class C09_SearchProduct {
    @Test
    public void test09() {

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

        // Click on 'Products' button
        locates.homePageProductsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        String expectedAllProductsTitle = "Automation Exercise - All Products";
        String actualAllProductsTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedAllProductsTitle, actualAllProductsTitle);

        softAssert.assertTrue(locates.productsSearchArea.isDisplayed());

        //Enter product name in search input and click search button
        locates.productsSearchArea.sendKeys(ConfigReader.getProperty("searchedProduct"));
        locates.productSearchButton.click();

        // Verify 'SEARCHED PRODUCTS' is visible
        softAssert.assertTrue(locates.searchedProductText.isDisplayed());
        String expectedText = "SEARCHED PRODUCTS";
        softAssert.assertEquals(expectedText, locates.searchedProductText.getText());

        //Verify all the products related to search are visible
        actions.scrollToElement(locates.brandBıba).perform();
        softAssert.assertTrue(locates.searchedProductProductName.isDisplayed());
        softAssert.assertEquals(ConfigReader.getProperty("searchedProduct"), locates.searchedProductProductName.getText());

        softAssert.assertAll();
    }
}
