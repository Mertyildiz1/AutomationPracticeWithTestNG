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

public class C04_LogoutUser {
    @Test
    public void test04() {

        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Verify that home page is visible successfully
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
        locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"),
                                                        Keys.TAB,
                                                        ConfigReader.getProperty("password"),
                                                        Keys.TAB,
                                                        Keys.ENTER);

        //Verify that 'Logged in as username' is visible
        wait.until(ExpectedConditions.visibilityOf(locates.loggedInAsUser));
        softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());

        //Click 'Logout' button
        locates.logoutButton.click();

        //Verify that user is navigated to login page
        softAssert.assertTrue(locates.loginToYourAccText.isDisplayed());

        String expectedLoginPageTitle = "Automation Exercise - Signup / Login";
        softAssert.assertEquals(expectedLoginPageTitle, Driver.getDriver().getTitle());

        softAssert.assertAll();
    }
}
