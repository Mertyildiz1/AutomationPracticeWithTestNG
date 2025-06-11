package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C11_VerifySubscriptionInCartPage {
    @Test
    public void test11() throws AWTException {

        Faker faker = new Faker();
        Robot robot = new Robot();
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

        // Click 'Cart' button
        locates.homePageCartButton.click();

        // Scroll down to footer
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

        //Verify text 'SUBSCRIPTION'
        String expectedSubText = "SUBSCRIPTION";
        softAssert.assertEquals(expectedSubText, locates.subscriptionText.getText());

        // Enter email address in input and click arrow button
        locates.subscriptionEmailInput.sendKeys(faker.internet().emailAddress());
        locates.subscriptionArrowButton.click();

        // Verify success message 'You have been successfully subscribed!' is visible
        softAssert.assertTrue(locates.subscriptionVerifyAlert.isDisplayed());

        String actualSubAlertText = locates.subscriptionVerifyAlert.getText();
        String expectedSubAlertText = "You have been successfully subscribed!";
        softAssert.assertEquals(expectedSubAlertText, actualSubAlertText);

        softAssert.assertAll();
    }
}
