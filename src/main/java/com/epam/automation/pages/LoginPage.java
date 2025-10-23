package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(id = "ap_email_login")
    private WebElement emailInput;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[@class='a-spacing-small']")
    private WebElement signInHeader;

    @FindBy(xpath = "//span[contains(text(), 'Your password is incorrect')]")
    private WebElement incorrectPasswordError;

    @FindBy(xpath = "//span[contains(text(), 'We cannot find an account')]")
    private WebElement accountNotFoundError;

    @FindBy(xpath = "//div[@id='auth-error-message-box']")
    private WebElement errorMessageBox;

    @FindBy(id = "auth-email-missing-alert")
    private WebElement emailMissingAlert;

    @FindBy(id = "auth-password-missing-alert")
    private WebElement passwordMissingAlert;

    @FindBy(xpath = "//span[contains(text(), 'There was a problem')]")
    private WebElement problemAlert;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void enterPassword(String password) {
        waitForElementToBeVisible(passwordInput);
        sendKeys(passwordInput, password);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    public void Login(String email, String password) {
        enterEmail(email);
        clickContinue();
        enterPassword(password);
        clickSignIn();
    }

    public boolean isSignInHeaderDisplayed() {
        return isDisplayed(signInHeader);
    }

    public boolean isIncorrectPasswordErrorDisplayed() {
        return isDisplayed(incorrectPasswordError);
    }

    public boolean isAccountNotFoundErrorDisplayed() {
        return isDisplayed(accountNotFoundError);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageBox) ||
                isDisplayed(problemAlert);
    }

    public boolean isEmailMissingAlertDisplayed() {
        return isDisplayed(emailMissingAlert);
    }

    public boolean isPasswordMissingAlertDisplayed() {
        return isDisplayed(passwordMissingAlert);
    }

    public String getErrorMessage() {
        if (isDisplayed(errorMessageBox)) {
            return getText(errorMessageBox);
        } else if (isDisplayed(problemAlert)) {
            return getText(problemAlert);
        }
        return "";
    }
}