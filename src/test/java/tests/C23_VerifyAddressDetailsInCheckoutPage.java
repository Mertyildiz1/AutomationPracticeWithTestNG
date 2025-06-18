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

import java.awt.*;
import java.time.Duration;

public class C23_VerifyAddressDetailsInCheckoutPage {
    @Test
    public void test23() throws AWTException {

        Robot robot = new Robot();
        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());
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

        // Click on 'Signup / Login' button
        locates.homePageSignUpLoginButton.click();

        // Fill all details in Signup and create account
        locates.SignUpPageNameArea.sendKeys(
                faker.name().firstName(),
                Keys.TAB,
                faker.internet().emailAddress());

        locates.SignUpPageSignUpButton.click();

        locates.mrsMrSelectRadioBox.click();
        locates.mrsMrSelectRadioBox.sendKeys(Keys.TAB,
                Keys.TAB,
                faker.internet().password(),
                Keys.TAB,
                String.valueOf(faker.random().nextInt(1, 30)),
                Keys.TAB,
                "July",
                Keys.TAB,
                String.valueOf(faker.random().nextInt(1950, 2015)));

        locates.newsletterCheckBox.click();
        locates.newsletterCheckBox.sendKeys(Keys.TAB, Keys.SPACE);

        String firstNameInput = faker.name().firstName();
        String lastNameInput = faker.name().lastName();
        String componyName = faker.company().name();
        String fullAddress = faker.address().fullAddress();
        String addressCountry = faker.address().country();
        String addressState = faker.address().state();
        String addressCity = faker.address().city();
        String addressZipCode = faker.address().zipCode();
        String mobileNumber = faker.phoneNumber().phoneNumber();

        locates.registerPageFirstNameArea.sendKeys(
                firstNameInput,
                Keys.TAB,
                lastNameInput,
                Keys.TAB,
                componyName,
                Keys.TAB,
                fullAddress,
                Keys.TAB,
                Keys.TAB,
                addressCountry,
                Keys.TAB,
                addressState,
                Keys.TAB,
                addressCity,
                Keys.TAB,
                addressZipCode);

        locates.registerPhoneNumberArea.sendKeys(mobileNumber);

        //Click 'Create Account button'
        locates.registerPageCreateAccButton.click();

        // Verify 'ACCOUNT CREATED!' and click 'Continue' button
        softAssert.assertTrue(locates.accCreatedText.isDisplayed());
        String expectedAccCreatedText = "Automation Exercise - Account Created";
        softAssert.assertEquals(expectedAccCreatedText, Driver.getDriver().getTitle());

        locates.accCretedContinueButton.click();

        //Verify that 'Logged in as username' is visible
        softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());

        // Add products to cart
        actions.moveToElement(locates.firstProductMove).perform();
        js.executeScript("arguments[0].click();", locates.firstProductAddToCartButton);

        // Click 'Cart' button
        wait.until(ExpectedConditions.visibilityOf(locates.viewCartButton));
        locates.viewCartButton.click();

        // Verify that cart page is displayed
        wait.until(ExpectedConditions.visibilityOf(locates.shoppingCartTextInCartPage));
        softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed());

        // Click Proceed To Checkout
        locates.proceedToCheckoutButton.click();

        // Verify Address Details and Review Your Order
        String expectedBillName = "Mr. " + firstNameInput + " " + lastNameInput;
        String actualBillName = locates.nameOnBill.getText();
        softAssert.assertEquals(expectedBillName, actualBillName);

        softAssert.assertEquals(componyName, locates.componyNameOnBill.getText());
        softAssert.assertEquals(fullAddress, locates.addressOnBill.getText());

        String expectedBillStateCityZip = addressCity + " " + addressState + " " + addressZipCode;
        softAssert.assertEquals(expectedBillStateCityZip, locates.stateCityZipOnBill.getText());

        softAssert.assertEquals(mobileNumber, locates.cellNumberOnBill.getText());

        // Click 'Delete Account' button
        locates.homePageDeleteAccButton.click();

        // Verify 'ACCOUNT DELETED!' is visible and click 'Continue' button
        softAssert.assertTrue(locates.accDeletedText.isDisplayed());
        String expectedAccDeleteText = "ACCOUNT DELETED!";
        String actualAccDeletedText = locates.accDeletedText.getText();
        softAssert.assertEquals(actualAccDeletedText,expectedAccDeleteText);
        locates.deleteAccContinueButton.click();

        softAssert.assertAll();
    }
}
