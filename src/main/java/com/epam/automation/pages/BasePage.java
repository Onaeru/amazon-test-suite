package com.epam.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();

        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        } else {
            throw new IllegalArgumentException("Text to send cannot be null or empty");
        }
    }

    protected String getText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }
}
