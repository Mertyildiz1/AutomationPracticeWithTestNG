package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class C20_SearchProductsVerifyCartAfterLogin {
    @Test
    public void test20() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());
        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Click on 'Products' button
        locates.homePageProductsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());

        String expectedAllProductsText = "ALL PRODUCTS";
        String actualAllProductsText = locates.productsPageAllProductsText.getText();
        softAssert.assertEquals(actualAllProductsText, expectedAllProductsText);

        // Enter product name in search input and click search button
        locates.productsSearchArea.sendKeys("Blue Top");
        locates.productSearchButton.click();

        // Verify 'SEARCHED PRODUCTS' is visible
        softAssert.assertTrue(locates.searchedProductText.isDisplayed());

        String expectedSearchedText = "SEARCHED PRODUCTS";
        String actualSearchedText = locates.searchedProductText.getText();
        softAssert.assertEquals(actualSearchedText, expectedSearchedText);

        // Verify all the products related to search are visible
        js.executeScript("arguments[0].scrollIntoView(true);", locates.brandBıba);
        softAssert.assertTrue(locates.blueTopTextOnBanner.isDisplayed());

        // Add those products to cart
        locates.firstProductAddToCartButton.click();

        // Click 'Cart' button and verify that products are visible in cart
        locates.viewCartButton.click();
        softAssert.assertTrue(locates.productInCart.isDisplayed());

        String expectedProductName = "Blue Top";
        String actualProductName = locates.productInCart.getText();
        softAssert.assertEquals(actualProductName, expectedProductName);

        // Click 'Signup / Login' button and submit login details
        locates.homePageSignUpLoginButton.click();
        locates.loginPageEmailArea.sendKeys(
                ConfigReader.getProperty("mail"),
                Keys.TAB,
                ConfigReader.getProperty("password"),
                Keys.ENTER);


        // Again, go to Cart page
        locates.homePageCartButton.click();

        // Verify that those products are visible in cart after login as well
        softAssert.assertTrue(locates.productInCart.isDisplayed());

        String expectedProductName1 = "Blue Top";
        String actualProductName1 = locates.productInCart.getText();
        softAssert.assertEquals(actualProductName, expectedProductName);


        softAssert.assertAll();
    }
}
