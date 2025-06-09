package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

public class C01_RegisterUser {
    @Test
    public void test01() {

        Locates locates = new Locates();
        SoftAssert softAssert = new SoftAssert();
        Faker faker = new Faker();

        // Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // Verify that home page is visible successfully
        String pageExpectedTitle = "Automation Exercise";
        String pageActualTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(pageExpectedTitle, pageActualTitle);

        softAssert.assertTrue(locates.homePageTestCasesButton.isDisplayed());

        // Click on 'Signup / Login' button
        locates.homePageSignUpLoginButton.click();

        //Verify 'New User Signup!' is visible
        String expectedLoginTitle = "Automation Exercise - Signup / Login";
        String actualLoginTitle = Driver.getDriver().getTitle();
        softAssert.assertEquals(expectedLoginTitle, actualLoginTitle);

        //Enter name and email address
        locates.SignUpPageNameArea.sendKeys(faker.name().firstName(), Keys.TAB, faker.internet().emailAddress());

        //Click 'Signup' button
        locates.SignUpPageSignUpButton.click();

        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        softAssert.assertTrue(locates.enterAccountInfoText.isDisplayed());

        //Fill details: Title, Name, Email, Password, Date of birth
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

        //Select checkbox 'Sign up for our newsletter!'
        locates.newsletterCheckBox.click();
        locates.newsletterCheckBox.sendKeys(Keys.TAB, Keys.SPACE);

        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        locates.registerPageFirstNameArea.sendKeys(faker.name().firstName(),
                Keys.TAB,
                faker.name().lastName(),
                Keys.TAB,
                faker.company().name(),
                Keys.TAB,
                faker.address().fullAddress(),
                Keys.TAB,
                Keys.TAB,
                faker.address().country(),
                Keys.TAB,
                faker.address().state(),
                Keys.TAB,
                faker.address().city(),
                Keys.TAB,
                faker.address().zipCode());

        locates.registerPhoneNumberArea.sendKeys(faker.phoneNumber().phoneNumber());

        //Click 'Create Account button'
        locates.registerPageCreateAccButton.click();

        // Verify that 'ACCOUNT CREATED!' is visible
        softAssert.assertTrue(locates.accCreatedText.isDisplayed());
        String expectedAccCreatedText = "Automation Exercise - Account Created";
        softAssert.assertEquals(expectedAccCreatedText, Driver.getDriver().getTitle());

        // Click 'Continue' button
        locates.accCretedContinueButton.click();

        //Verify that 'Logged in as username' is visible
        softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());

        //Click 'Delete Account' button
        locates.homePageDeleteAccButton.click();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        softAssert.assertTrue(locates.accDeletedText.isDisplayed());
        locates.deleteAccContinueButton.click();

        softAssert.assertAll();
    }
}
