package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.security.Key;
import java.time.Duration;

public class C21_AddReviewOnProducts {
    @Test
    public void test21() {

        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
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

        // Click on 'View Product' button
        js.executeScript("arguments[0].scrollIntoView(true);", locates.brandBıba);
        locates.firstProductsViewProductButton.click();

        // Verify 'Write Your Review' is visible
        softAssert.assertTrue(locates.writeYourReviewText.isDisplayed());

        // Enter name, email and review
        locates.writeYourReviewNameArea.sendKeys(
                ConfigReader.getProperty("name"),
                Keys.TAB,
                ConfigReader.getProperty("mail"),
                Keys.TAB,
                faker.lorem().sentence(20));

        //  Click 'Submit' button
        locates.writeYourReviewSubmitButton.click();

        // Verify success message 'Thank you for your review.'
        wait.until(ExpectedConditions.visibilityOf(locates.writeYourReviewSuccessMessage));
        softAssert.assertTrue(locates.writeYourReviewSuccessMessage.isDisplayed());

        String expectedSuccessMessage = "Thank you for your review.";
        String actualSuccessMessage = locates.writeYourReviewSuccessMessage.getText();
        softAssert.assertEquals(actualSuccessMessage, expectedSuccessMessage);

        softAssert.assertAll();
    }
}
