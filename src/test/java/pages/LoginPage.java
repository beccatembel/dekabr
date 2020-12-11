package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.CommonUtils;
import utilities.Users;

import static utilities.Constants.MAIL;

public class LoginPage {
    WebDriver driver = Driver.getDriver(CommonUtils.getProperty("browser"));
    Users users = new Users();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//span[@class='errorMsg']")
    public WebElement errorMsg;

    @FindBy(xpath = "//span[@class='MaxErrorAttempts']")
    public WebElement maxErrorAttempts;

    @FindBy(xpath = "//button[@class='resetPsw']")
    public WebElement resetPasswordBtn;

    @FindBy(xpath = "//div[@class='input-group']/input")
    public WebElement checkMailInput;

    @FindBy(xpath = "//div[@class='table-responsive']//tbody/tr[position()=1]//td[.='Reset Password']")
    public WebElement checkMailInbox;

    @FindBy(xpath = "//div[@class='inbox']//a[.='Reset Password']")
    public WebElement resetPasswordLink;


    /***
     * This method verify expected title
     */
    public void verifyTitle() {
        String expectedTitle = "Example.co/";
        Assert.assertEquals(expectedTitle, driver.getTitle());
    }

    /***
     * This method try login with provided credentials
     * @param credentials
     */
    public void authentication(String credentials) {
        login.sendKeys(users.usersEmail(credentials));
        password.sendKeys(users.usersPassword(credentials));
        loginBtn.click();
    }

    /***
     * This method click on reset button and provide credentials for resetting password
     * @param credentials
     */
    public void resetPassword(String credentials) {
        if (errorMsg.isDisplayed()) {
            resetPasswordBtn.click();
            login.sendKeys(users.usersEmail(credentials));
            loginBtn.click();
        }
    }

    /***
     * This method check receiving mail with reset password instructions from https://mailinator.com/
     * @param credentials
     */
    public void checkMail(String credentials) {
        driver.navigate().to(CommonUtils.getProperty(MAIL));
        checkMailInput.sendKeys(users.usersEmail(credentials) + Keys.ENTER);
        if (checkMailInbox.isDisplayed()) {
            checkMailInbox.click();
            resetPasswordLink.click();
        }
    }

    /***
     * This method provide new password for reset
     * @param credentials
     */
    public void provideNewPassword(String credentials) {
        login.sendKeys(users.usersPassword(credentials));
        loginBtn.click();
    }
}
