package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    private WebElement fullNameInput;

    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    private WebElement phoneNumberInput;

    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    private WebElement addressLine1Input;

    @FindBy(id = "address-ui-widgets-enterAddressLine2")
    private WebElement addressLine2Input;

    @FindBy(id = "address-ui-widgets-enterAddressCity")
    private WebElement cityInput;

    @FindBy(id = "address-ui-widgets-enterAddressStateOrRegion")
    private WebElement stateDropdown;

    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//input[@data-testid='Address_selectShipToThisAddress']")
    private WebElement useThisAddressButton;

    @FindBy(xpath = "//input[contains(@name, 'ppw-widgetEvent:SetPaymentPlanSelectContinueEvent')]")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@name='ppw-instrumentRowSelection']")
    private WebElement paymentMethodRadio;

    @FindBy(id = "pp-yxDMXC-15")
    private WebElement creditCardOption;

    @FindBy(xpath = "//h1[contains(text(), 'Select a payment method')]")
    private WebElement paymentMethodHeader;

    @FindBy(xpath = "//h1[contains(text(), 'Choose a shipping address')]")
    private WebElement shippingAddressHeader;

    @FindBy(xpath = "//input[@name='placeYourOrder1']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//h1[contains(text(), 'Review your order')]")
    private WebElement reviewOrderHeader;

    @FindBy(xpath = "//a[contains(text(), 'Change')]")
    private WebElement changeAddressLink;

    @FindBy(id = "orderSummaryPrimaryActionBtn")
    private WebElement primaryActionButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFullName(String fullName) {
        sendKeys(fullNameInput, fullName);
    }

    public void enterPhoneNumber(String phoneNumber) {
        sendKeys(phoneNumberInput, phoneNumber);
    }

    public void enterAddress(String address) {
        sendKeys(addressLine1Input, address);
    }

    public void enterCity(String city) {
        sendKeys(cityInput, city);
    }

    public void selectState(String state) {
        click(stateDropdown);
        WebElement stateOption = driver.findElement(
                org.openqa.selenium.By.xpath("//option[contains(text(), '" + state + "')]")
        );
        click(stateOption);
    }

    public void enterZipCode(String zipCode) {
        sendKeys(postalCodeInput, zipCode);
    }

    public void clickUseThisAddress() {
        click(useThisAddressButton);
    }

    public void fillShippingAddress(String name, String phone, String address,
                                    String city, String state, String zipCode) {
        enterFullName(name);
        enterPhoneNumber(phone);
        enterAddress(address);
        enterCity(city);
        selectState(state);
        enterZipCode(zipCode);
        clickUseThisAddress();
    }

    public void clickContinueToPayment() {
        if (isDisplayed(continueButton)) {
            click(continueButton);
        }
    }

    public void selectPaymentMethod() {
        if (isDisplayed(paymentMethodRadio)) {
            click(paymentMethodRadio);
        }
    }

    public void clickPlaceOrder() {
        if (isDisplayed(placeOrderButton)) {
            click(placeOrderButton);
        }
    }

    public boolean isShippingAddressPageDisplayed() {
        return isDisplayed(shippingAddressHeader);
    }

    public boolean isPaymentMethodPageDisplayed() {
        return isDisplayed(paymentMethodHeader);
    }

    public boolean isReviewOrderPageDisplayed() {
        return isDisplayed(reviewOrderHeader);
    }
}
