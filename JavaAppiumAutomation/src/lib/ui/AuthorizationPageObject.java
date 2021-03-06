package src.lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
        LOGIN_BUTTON = "xpath://body//div//a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:#wpLoginAttempt";
    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    public void clickAuthButton(){
        //this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find auth button",10);
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button",10);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.waitForElementAndClick(LOGIN_BUTTON,"Cannot click auth button",10);
    }
    public void enterLoginData(String login, String password){
        //this.tryClickElementWithFewAttempts(LOGIN_INPUT,"Cannot find and put a login to the login input", 10);
        this.waitForElementPresent(LOGIN_INPUT,"Cannot find login input",10);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT,password,"Cannot find and put a password to the password input", 5);
    }
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON,"Cannot find and click Submit auth button", 5);
    }
}
