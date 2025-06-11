package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class C07_VerifyTestCasesPage {
    @Test
    public void test07() {

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

        // Click on 'Test Cases' button
        locates.homePageTestCasesButton.click();

        // Verify user is navigated to test cases page successfully
        String expectedTestCaseTitle = "Test Cases";
        String actualTestCaseTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actualTestCaseTitle.contains(expectedTestCaseTitle));

        softAssert.assertTrue(locates.testCasePageTestCaseText.isDisplayed());

        softAssert.assertAll();
    }
}
