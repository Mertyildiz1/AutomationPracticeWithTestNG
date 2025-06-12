package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class C12_AddProductsInCart {
    @Test
    public void test12() throws InterruptedException {

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

        // Click on 'Products' button
        locates.homePageProductsButton.click();

        // Hover over first product and click 'Add to cart'
        actions.moveToElement(locates.firstProductMove).perform();
        js.executeScript("arguments[0].click();", locates.firstProductAddToCartButton);

        // Click 'Continue Shopping' button
        wait.until(ExpectedConditions.visibilityOf(locates.continueShoppingButton));
        locates.continueShoppingButton.click();

        // Hover over second product and click 'Add to cart'
        actions.moveToElement(locates.secondProductMove).perform();
        js.executeScript("arguments[0].click();", locates.secondProductAddToCartButton);

        // Click 'View Cart' button
        wait.until(ExpectedConditions.visibilityOf(locates.viewCartButton));
        locates.viewCartButton.click();

        // Verify both products are added to Cart
        int sayac = 0;
        for (int i = 0; i < locates.productsInCart.size(); i++) {
            locates.productsInCart.get(i).click();
            Thread.sleep(1500);
            Driver.getDriver().navigate().back();
            sayac++;
        }
        softAssert.assertEquals(2, sayac);

        // Verify their prices, quantity and total price
        String expectedBlueTopPrice = "Rs. 500";
        softAssert.assertEquals(expectedBlueTopPrice, locates.blueTopPriceInCart.getText());

        String expectedMenTshirtPrice = "Rs. 400";
        softAssert.assertEquals(expectedMenTshirtPrice, locates.menTshirtPriceInCart.getText());

        String expectedBlueTopQuantity = "1";
        softAssert.assertEquals(expectedBlueTopQuantity, locates.firstProductQuantityInCart.getText());

        String expectedMenTshirtQuantity = "1";
        softAssert.assertEquals(expectedMenTshirtQuantity, locates.secondProductQuantityInCart.getText());

        String actualTotalPrice1 = locates.cartTotalPriceFirstProduct.getText();
        String expectedTotalPrice1 = "Rs. 500";
        softAssert.assertEquals(expectedTotalPrice1, actualTotalPrice1);
        String actualTotalPrice2 = locates.cartTotalPriceSecondProduct.getText();
        String expectedTotalPrice2 = "Rs. 400";
        softAssert.assertEquals(expectedTotalPrice2, actualTotalPrice2);

        softAssert.assertAll();
    }
}
