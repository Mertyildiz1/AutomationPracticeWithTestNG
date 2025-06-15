package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class C18_ViewCategoryProducts {
    @Test
    public void test18() {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());
        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Verify that categories are visible on left side bar
        softAssert.assertTrue(locates.leftSideBarCategories.isDisplayed());

        // Click on 'Women' category
        locates.womenCategoriesSelection.click();

        // Click on any category link under 'Women' category, for example: Dress
        locates.dressSelectionAtWomenTab.click();

        // Verify that category page is displayed and confirm text 'WOMEN -  DRESS PRODUCTS'
        softAssert.assertTrue(locates.womenDressProductsTitle.isDisplayed());
        String expectedWomenDressPruductsText = "WOMEN - DRESS PRODUCTS";
        String actualWomenDressProductsText = locates.womenDressProductsTitle.getText();
        softAssert.assertEquals(actualWomenDressProductsText,expectedWomenDressPruductsText);

        // On left side bar, click on any sub-category link of 'Men' category
        locates.menCategoriesSelection.click();
        locates.tshirtSelectionAtMenTab.click();

        // Verify that user is navigated to that category page
        softAssert.assertTrue(locates.menTshirtProductsTitle.isDisplayed());
        String expectedMenTshirtPruductsText = "MEN - TSHIRTS PRODUCTS";
        String actualMenTshirtPruductsText = locates.menTshirtProductsTitle.getText();
        softAssert.assertEquals(actualMenTshirtPruductsText,expectedMenTshirtPruductsText);


        softAssert.assertAll();
    }
}
