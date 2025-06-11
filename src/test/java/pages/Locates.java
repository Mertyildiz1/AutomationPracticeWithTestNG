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

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement featuresItemsText;

    @FindBy(xpath = "//*[text()='Login to your account']")
    public WebElement loginToYourAccText;

    @FindBy(xpath = "//*[@type='email']")
    public WebElement loginPageEmailArea;

    @FindBy(xpath = "//*[text()='Your email or password is incorrect!']")
    public WebElement loginErrorText;

    @FindBy(xpath = "//*[@class='fa fa-lock']")
    public WebElement logoutButton;

    @FindBy(xpath = "//*[text()=' Home']")
    public WebElement homePageHomeButton;

    @FindBy(xpath = "//*[text()='New User Signup!']")
    public WebElement newUserSignUpText;

    @FindBy(xpath = "//*[.='Email Address already exist!']")
    public WebElement alreadyExistText;

    @FindBy(xpath = "//i[@class='fa fa-envelope']")
    public WebElement contactUsButton;

    @FindBy(xpath = "//*[.='Get In Touch']")
    public WebElement getInTouchText;

    @FindBy(css = ".form-control")
    public WebElement contactUsNameInputArea;

    @FindBy(xpath = "(//input[@class='form-control'])[4]")
    public WebElement contactUsFileUpload;

    @FindBy(xpath = "//input[@class='btn btn-primary pull-left submit_form']")
    public WebElement contactUsSubmitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    public WebElement contactUsSuccessMessage;

    @FindBy(xpath = "//i[@class='fa fa-angle-double-left']")
    public WebElement contactUsHomeButton;

    @FindBy(xpath = "//b[text()='Test Cases']")
    public WebElement testCasePageTestCaseText;

    @FindBy(css = ".material-icons.card_travel")
    public WebElement homePageProductsButton;

    @FindBy(xpath = "//input[@id='search_product']")
    public WebElement productsSearchArea;

    @FindBy(xpath = "//*[.='All Products']")
    public WebElement productsPageAllProductsText;

    @FindBy(xpath = "(//i[@class='fa fa-plus-square'])[1]")
    public WebElement firstProductsViewProductButton;

    @FindBy(xpath = "//*[.='Quantity:']")
    public WebElement productDetailsQuantity;

    @FindBy(xpath = "//*[.='Blue Top']")
    public WebElement productDetailsProductName;

    @FindBy(xpath = "//*[.='Category: Women > Tops']")
    public WebElement productDetailsCategoryName;

    @FindBy(xpath = "//*[.='Rs. 500']")
    public WebElement productDetailsPrice;

    @FindBy(xpath = "//*[.='Availability:']")
    public WebElement productDetailsAvailability;

    @FindBy(xpath = "//*[.='Condition:']")
    public WebElement productDetailsCondition;

    @FindBy(xpath = "//*[.='Brand:']")
    public WebElement productDetailsBrandName;
}
