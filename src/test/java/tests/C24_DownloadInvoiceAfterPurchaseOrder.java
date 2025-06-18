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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class C24_DownloadInvoiceAfterPurchaseOrder {
    @Test
    public void test24() throws InterruptedException, AWTException {

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

        // Add products to cart
        locates.homePageProductsButton.click();
        js.executeScript("arguments[0].scrollIntoView(true);", locates.brandBıba);
        js.executeScript("arguments[0].click();", locates.firstProductAddToCartButton);
        Thread.sleep(1500);

        // Click 'Cart' button
        locates.viewCartButton.click();

        // Verify that cart page is displayed
        softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed());

        // Click Proceed To Checkout
        locates.proceedToCheckoutButton.click();

        // Click 'Register / Login' button
        locates.ProceedToCheckoutRegisterLoginButton.click();

        // Fill all details in Signup and create account
        locates.SignUpPageNameArea.sendKeys(
                ConfigReader.getProperty("name"),
                Keys.TAB,
                faker.internet().emailAddress(),
                Keys.ENTER);


        locates.mrsMrSelectRadioBox.click();
        locates.mrsMrSelectRadioBox.sendKeys(
                Keys.TAB,
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

        // Verify ' Logged in as username' at top
        softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());
        String expectedLoggedText = "Logged in as " + ConfigReader.getProperty("name");
        String actualLoggedText = locates.loggedInAsUser.getText();
        softAssert.assertEquals(expectedLoggedText, actualLoggedText);

        //Click 'Cart' button
        locates.homePageCartButton.click();

        // Click 'Proceed To Checkout' button
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

        // Enter description in comment text area and click 'Place Order'
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

        locates.commentAreaBeforePayment.sendKeys(
                faker.lorem().sentence(10),
                Keys.TAB,
                Keys.ENTER);

        // Enter payment details: Name on Card, Card Number, CVC, Expiration date
        locates.paymentNameOnCard.sendKeys(
                ConfigReader.getProperty("name"),
                Keys.TAB,
                faker.business().creditCardNumber(),
                Keys.TAB,
                "311",
                Keys.TAB,
                "02",
                Keys.TAB,
                "2030",
                Keys.TAB);

        // Click 'Pay and Confirm Order' button
        locates.payAndConfirmOrder.click();

        // Verify success message 'Congratulations! Your order has been confirmed!'
        softAssert.assertTrue(locates.verifySuccessOrder.isDisplayed());
        String expectedVerifySuccessText = "Congratulations! Your order has been confirmed!";
        String actualVerifySuccessText = locates.verifySuccessOrder.getText();
        softAssert.assertEquals(expectedVerifySuccessText, actualVerifySuccessText);

        // Click 'Download Invoice' button and verify invoice is downloaded successfully.
        locates.downloadInvoıceButton.click();

        String everyoneOwnFilePath = System.getProperty("user.home");
        String commonFilePath = "\\Downloads\\invoice.txt";

        String filePath = everyoneOwnFilePath + commonFilePath;

        for (int i = 0; i < 100; i++) {
            if (Files.exists(Paths.get(filePath))) {
                break;
            }
            Thread.sleep(1000);
        }

        softAssert.assertTrue(Files.exists(Paths.get(filePath)));

        Thread.sleep(3000);
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Click 'Continue' button
        locates.orderPlaceContinueButton.click();

        // Click 'Delete Account' button
        locates.homePageDeleteAccButton.click();

        // Verify 'ACCOUNT DELETED!' is visible and click 'Continue' button
        softAssert.assertTrue(locates.accDeletedText.isDisplayed());
        locates.deleteAccContinueButton.click();

        softAssert.assertAll();
    }
}
