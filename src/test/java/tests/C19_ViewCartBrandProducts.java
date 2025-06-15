package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class C19_ViewCartBrandProducts {
    @Test
    public void test19() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());
        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Click on 'Products' button
        locates.homePageProductsButton.click();

        // Verify that Brands are visible on left side bar
        softAssert.assertTrue(locates.brandsOnProducts.isDisplayed());

        // Click on any brand name
        locates.brandPoloSelection.click();

        // Verify that user is navigated to brand page and brand products are displayed
        String actualBrandPoloProductsText = locates.brandPoloProductsTitle.getText();
        String expectedBrandPoloProductText = "BRAND - POLO PRODUCTS";
        softAssert.assertEquals(actualBrandPoloProductsText,expectedBrandPoloProductText);

        List<WebElement> poloProductsResults = locates.productSearchResults;

        for (int i = 0; i < poloProductsResults.size(); i++) {
            softAssert.assertTrue(poloProductsResults.get(i).isDisplayed());
        }

        // On left side bar, click on any other brand link
        locates.brandHMSelection.click();

        // Verify that user is navigated to that brand page and can see products
        String expectedBrandHMProductText = "BRAND - H&M PRODUCTS";
        String actualBrandHMProductText = locates.brandHMProductsTitle.getText();
        softAssert.assertEquals(actualBrandHMProductText,expectedBrandHMProductText);

        List<WebElement> hmProductResults = locates.productSearchResults;

        for (int i = 0; i < hmProductResults.size(); i++) {
            softAssert.assertTrue(hmProductResults.get(i).isDisplayed());
        }

        softAssert.assertAll();
    }
}
