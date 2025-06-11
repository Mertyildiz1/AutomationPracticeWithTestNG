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

public class C02_LoginWithCorrectEmailAndPassword {
    @Test
    public void test02() {

        SoftAssert softAssert = new SoftAssert();
        Locates locates = new Locates();

        // Navigate to url 'http://automationexercise.com'

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Verify that home page is visible successfully
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(locates.featuresItemsText));
        softAssert.assertTrue(locates.featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedTitle, actualTitle);

        // Click on 'Signup / Login' button
        locates.homePageSignUpLoginButton.click();

        //Verify 'Login to your account' is visible
        wait.until(ExpectedConditions.visibilityOf(locates.loginToYourAccText));
        softAssert.assertTrue(locates.loginToYourAccText.isDisplayed());

        //Enter correct email address and password
        locates.loginPageEmailArea.sendKeys(
                ConfigReader.getProperty("mail"),
                Keys.TAB,
                ConfigReader.getProperty("password"),
                Keys.TAB,
                Keys.ENTER);

        //Verify that 'Logged in as username' is visible
        wait.until(ExpectedConditions.visibilityOf(locates.loggedInAsUser));
        softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());

        //Click 'Delete Account' button
        locates.homePageDeleteAccButton.click();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        softAssert.assertTrue(locates.accDeletedText.isDisplayed());
        locates.deleteAccContinueButton.click();

        softAssert.assertAll();
    }
}
