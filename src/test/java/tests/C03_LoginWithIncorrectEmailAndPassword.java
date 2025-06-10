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

public class C03_LoginWithIncorrectEmailAndPassword {
    @Test
    public void LoginWithCorrectEmailAndPassword() {

        SoftAssert softAssert = new SoftAssert();
        Locates locates = new Locates();
        Faker faker = new Faker();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // Navigate to url
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

        //Enter incorrect email address and password
        locates.loginPageEmailArea.sendKeys(faker.internet().emailAddress(),
                                            Keys.TAB,
                                            faker.internet().password(),
                                            Keys.TAB, Keys.ENTER);

        //Verify error 'Your email or password is incorrect!' is visible
        String expectedErrorText = "Your email or password is incorrect!";

        softAssert.assertTrue(locates.loginErrorText.isDisplayed());
        softAssert.assertEquals(expectedErrorText, locates.loginErrorText.getText());
    }
}
