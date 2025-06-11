package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class C06_ContactUsForm {
    @Test
    public void test06() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        Locates locates = new Locates();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();

        // Navigate to url
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Verify that home page is visible successfully
        wait.until(ExpectedConditions.visibilityOf(locates.featuresItemsText));
        softAssert.assertTrue(locates.featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedTitle, actualTitle);

        // Click on 'Contact Us' button
        locates.contactUsButton.click();

        // Verify 'GET IN TOUCH' is visible
        softAssert.assertTrue(locates.getInTouchText.isDisplayed());

        String expectedText = "GET IN TOUCH";
        String actualText = locates.getInTouchText.getText();
        softAssert.assertEquals(expectedText, actualText);
        Thread.sleep(1000);

        // Enter name, email, subject and message
        String subject = faker.lorem().sentence(4);
        String message = faker.lorem().sentence(15);
        locates.contactUsNameInputArea.sendKeys(
                faker.name().firstName(),
                Keys.TAB,
                faker.internet().emailAddress(),
                Keys.TAB,
                subject,
                Keys.TAB,
                message);

        // Upload file
        String everyoneOwnPath = System.getProperty("user.home");
        String commonFilePath = "\\Downloads\\test.txt";
        String filePath = everyoneOwnPath + commonFilePath;
        locates.contactUsFileUpload.sendKeys(filePath);

        // Click 'Submit' button
        Thread.sleep(1000);
        locates.contactUsSubmitButton.click();

        // Click OK button (HTML Alert)
        Driver.getDriver().switchTo().alert().accept();

        // Verify success message 'Success! Your details have been submitted successfully.' is visible
        softAssert.assertTrue(locates.contactUsSuccessMessage.isDisplayed());

        String expectedSuccessMessage = "Success! Your details have been submitted successfully.";
        String actualSuccessMessage = locates.contactUsSuccessMessage.getText();
        softAssert.assertEquals(expectedSuccessMessage, actualSuccessMessage);

        //Click 'Home' button and verify that landed to home page successfully
        locates.contactUsHomeButton.click();
        wait.until(ExpectedConditions.visibilityOf(locates.featuresItemsText));
        softAssert.assertTrue(locates.featuresItemsText.isDisplayed());
        softAssert.assertEquals(expectedTitle, actualTitle);

        softAssert.assertAll();
    }
}
