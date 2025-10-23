package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(id = "buy-now-button")
    private WebElement buyNowButton;

    @FindBy(xpath = "//span{@class='a-price-whole']")
    private WebElement productPrice;

    @FindBy(id = "availability")
    private WebElement availabilityMessage;

    @FindBy(xpath = "//i[contains(@class, 'a-icon-star')]//span")
    private WebElement productRating;

    @FindBy(id = "acrCustomerReviewText")
    private WebElement reviewCount;

    @FindBy(xpath = "//div[@id='attach-added-to-cart-message']")
    private WebElement addedToCartMessage;

    @FindBy(xpath = "//span[contains(text(), 'Added to Cart')]")
    private WebElement addedToCartConfirmation;

    @FindBy(id = "attach-view-cart-button-form")
    private WebElement viewCartButton;

    @FindBy(id = "attach-close_sideSheet-link")
    private WebElement closeSideSheetButton;

    @FindBy(xpath = "//select[@id='quantity']")
    private WebElement quantityDropdown;

    @FindBy(id = "productDescription")
    private WebElement productDescription;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        waitForElementToBeVisible(productTitle);
        return getText(productTitle);
    }

    public void clickAddToCart() {
        scrollToElement(addToCartButton);
        click(addToCartButton);
    }

    public void clickBuyNow() {
        scrollToElement(buyNowButton);
        click(buyNowButton);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public String getProductPrice() {
        if (isDisplayed(productPrice)) {
            return getText(productPrice);
        }
        return "";
    }

    public String getAvailability() {
        if (isDisplayed(availabilityMessage)) {
            return getText(availabilityMessage);
        }
        return "";
    }

    public boolean isProductAvailable() {
        String availability = getAvailability().toLowerCase();
        return availability.contains("in stock") ||
                availability.contains("available");
    }

    public String getProductRating() {
        if (isDisplayed(productRating)) {
            return getText(productRating);
        }
        return "";
    }

    public boolean isAddedToCartMessageDisplayed() {
        return isDisplayed(addedToCartConfirmation) ||
                isDisplayed(addedToCartMessage);
    }

    public CartPage goToCartFromProductPage() {
        if (isDisplayed(viewCartButton)) {
            click(viewCartButton);
        }
        return new CartPage(driver);
    }

    public void closeSideSheet() {
        if (isDisplayed(closeSideSheetButton)) {
            click(closeSideSheetButton);
        }
    }

    public void selectQuantity(String quantity) {
        click(quantityDropdown);
        WebElement option = driver.findElement(
                org.openqa.selenium.By.xpath("//option[@value='" + quantity + "']")
        );
        click(option);
    }

    public boolean isProductPageLoaded() {
        return isDisplayed(productTitle) && isDisplayed(addToCartButton);
    }
}