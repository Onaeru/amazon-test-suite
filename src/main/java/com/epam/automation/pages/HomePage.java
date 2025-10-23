package com.epam.automation.pages;

import com.epam.automation.utils.TestUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(id = "nav-link-accountList")
    private WebElement accountListButton;

    @FindBy(id = "nav-cart")
    private WebElement cartButton;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "nav-logo-sprites")
    private WebElement amazonLogo;

    @FindBy(xpath = "//span[contains(text(), 'Hello, sign in')]")
    private WebElement signInText;

    @FindBy(id = "nav-cart-count")
    private WebElement cartCount;

    @FindBy(xpath = "//a[@id='nav-orders']")
    private WebElement ordersLink;

    @FindBy(xpath = "//button[@alt='Continue shopping']")
    private WebElement continueShoppingButton;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAccountList() {
        click(accountListButton);
    }

    public LoginPage goToLogin() {
        click(accountListButton);
        return new LoginPage(driver);
    }

    public SearchResultsPage searchProduct(String productName) {
        handlePossibleVerificationOrIntermediatePage();
        sendKeys(searchBox, productName);
        searchBox.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public CartPage goToCart() {
        click(cartButton);
        return new CartPage(driver);
    }

    public boolean isAmazonLogoDisplayed() {
        return isDisplayed(amazonLogo);
    }

    public boolean isSignInTextDisplayed() {
        return isDisplayed(signInText);
    }

    public String getCartCount() {
        if (isDisplayed(cartCount)) {
            return getText(cartCount);
        }
        return "0";
    }

    public int getCartCountAsInt() {
        String count = getCartCount();
        return Integer.parseInt(count);
    }

    public boolean isHomePageLoaded() {
        return isAmazonLogoDisplayed() && isDisplayed(searchBox);
    }

    public void handlePossibleVerificationOrIntermediatePage() {
        try {
            // Esperar un momento para que la página cargue completamente
            TestUtils.sleep(1500);

            // Case 1: Página de verificación "Continue shopping"
            if (isElementPresent(continueShoppingButton)) {
                System.out.println("✓ Intermediate page detected — clicking 'Continue shopping'.");
                click(continueShoppingButton);
                waitForPageLoad();
                TestUtils.sleep(2000);
                return;
            }

            // Case 2: Ya estamos en la Home Page
            if (isElementPresent(amazonLogo) && isElementPresent(searchBox)) {
                System.out.println("✓ Home page already loaded — no action needed.");
                return;
            }

            // Case 3: Página no reconocida - intentar refresh
            System.out.println("⚠ Unrecognized page — attempting refresh...");
            driver.navigate().refresh();
            waitForPageLoad();
            TestUtils.sleep(2500);

            // Verificar nuevamente después del refresh
            if (isElementPresent(continueShoppingButton)) {
                System.out.println("✓ Button detected after refresh — clicking it.");
                click(continueShoppingButton);
                waitForPageLoad();
                TestUtils.sleep(2000);
            } else if (isElementPresent(amazonLogo) && isElementPresent(searchBox)) {
                System.out.println("✓ Home page loaded after refresh.");
            } else {
                System.out.println("✗ Unable to reach home page after refresh.");
            }

        } catch (Exception e) {
            System.out.println("✗ Error handling intermediate page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}