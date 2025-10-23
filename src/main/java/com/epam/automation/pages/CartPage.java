package com.epam.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(xpath = "//h1[contains(text(), 'Shopping Cart')]")
    private WebElement shoppingCartHeader;

    @FindBy(xpath = "//div[@data-name='Active Items']//span[@class='a-truncate-cut']")
    private List<WebElement> cartItemTitles;

    @FindBy(xpath = "//input[contains(@name, 'quantityBox')]")
    private List<WebElement> quantityInputs;

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    private List<WebElement> itemPrices;

    @FindBy(id = "sc-subtotal-amount-activecart")
    private WebElement subtotalAmount;

    @FindBy(name = "proceedToRetailCheckout")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//input[@value='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//input[@value='Save for later']")
    private List<WebElement> saveForLaterButtons;

    @FindBy(xpath = "//h2[contains(text(), 'Your Amazon Cart is empty')]")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement cartCount;

    @FindBy(xpath = "//select[contains(@name, 'quantity')]")
    private List<WebElement> quantityDropdowns;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(shoppingCartHeader);
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }

    public int getCartItemCount() {
        return cartItemTitles.size();
    }

    public List<String> getCartItemTitles() {
        return cartItemTitles.stream()
                .map(this::getText)
                .toList();
    }

    public String getCartItemTitle(int index) {
        if (index < cartItemTitles.size()) {
            return getText(cartItemTitles.get(index));
        }
        return "";
    }

    public void deleteItem(int index) {
        if (index < deleteButtons.size()) {
            click(deleteButtons.get(index));
        }
    }

    public void deleteFirstItem() {
        deleteItem(0);
    }

    public void deleteAllItems() {
        while (!isCartEmpty() && !deleteButtons.isEmpty()) {
            deleteFirstItem();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveForLater(int index) {
        if (index < saveForLaterButtons.size()) {
            click(saveForLaterButtons.get(index));
        }
    }

    public String getSubtotal() {
        if (isDisplayed(subtotalAmount)) {
            return getText(subtotalAmount);
        }
        return "";
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }

    public void updateQuantity(int itemIndex, String quantity) {
        if (itemIndex < quantityDropdowns.size()) {
            WebElement dropdown = quantityDropdowns.get(itemIndex);
            click(dropdown);

            WebElement option = driver.findElement(
                    By.xpath("//option[@value='" + quantity + "']")
            );
            click(option);
        }
    }
}