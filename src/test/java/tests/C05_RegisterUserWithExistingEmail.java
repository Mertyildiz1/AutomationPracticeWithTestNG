package tests;

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

public class C05_RegisterUserWithExistingEmail {
    @Test
    public void test05() {

        SoftAssert softAssert = new SoftAssert();
        Locates locates = new Locates();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Verify that home page is visible successfully
        wait.until(ExpectedConditions.visibilityOf(locates.featuresItemsText));
        softAssert.assertTrue(locates.featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedTitle, actualTitle);

        // Click on 'Signup / Login' button
        locates.homePageSignUpLoginButton.click();

        // Verify 'New User Signup!' is visible
        String expectedText = "New User Signup!";
        String actualText = locates.newUserSignUpText.getText();
        softAssert.assertEquals(expectedText, actualText);

        // Enter name and already registered email address
        locates.SignUpPageNameArea.sendKeys(
                ConfigReader.getProperty("name"),
                Keys.TAB,
                ConfigReader.getProperty("mail"),
                Keys.TAB,
                Keys.ENTER);

        // Verify error 'Email Address already exist!' is visible
        String expectedExistText = "Email Address already exist!";
        softAssert.assertTrue(locates.alreadyExistText.isDisplayed());
        softAssert.assertEquals(expectedExistText, locates.alreadyExistText.getText());

        softAssert.assertAll();
    }
}
