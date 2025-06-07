package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class Locates {

    protected WebDriver driver;
    protected Select select;
    protected Actions actions;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public Locates() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[.=' Test Cases']")
    public WebElement homePageTestCasesButton;

    @FindBy(xpath = "//i[@class='fa fa-lock']")
    public WebElement homePageSignUpLoginButton;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement SignUpPageNameArea;

    @FindBy(xpath = "//button[.='Signup']")
    public WebElement SignUpPageSignUpButton;

    @FindBy(xpath = "//*[.='Enter Account Information']")
    public WebElement enterAccountInfoText;

    @FindBy(id = "id_gender1")
    public WebElement mrsMrSelectRadioBox;

    @FindBy(id = "newsletter")
    public WebElement newsletterCheckBox;

    @FindBy(id = "first_name")
    public WebElement registerPageFirstNameArea;

    @FindBy(id = "mobile_number")
    public WebElement registerPhoneNumberArea;

    @FindBy(xpath = "//*[.='Create Account']")
    public WebElement registerPageCreateAccButton;

    @FindBy(xpath = "//*[.='Account Created!']")
    public WebElement accCreatedText;

    @FindBy(xpath = "//*[.='Continue']")
    public WebElement accCretedContinueButton;

    @FindBy(xpath = "//*[text()=' Logged in as ']")
    public WebElement loggedInAsUser;

    @FindBy(xpath = "//*[.=' Delete Account']")
    public WebElement homePageDeleteAccButton;

    @FindBy(xpath = "//*[.='Account Deleted!']")
    public WebElement accDeletedText;

    @FindBy(xpath = "//a[.='Continue']")
    public WebElement deleteAccContinueButton;
}
