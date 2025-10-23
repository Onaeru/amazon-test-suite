package com.epam.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    private WebElement searchTermDisplay;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//span[contains(@class, 's-pagination-item')]")
    private List<WebElement> paginationItems;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']//h2/a")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//span[@class='a-price-whole']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//i[contains(@class, 'a-icon-star')]")
    private List<WebElement> productRatings;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchResultsDisplayed() {
        return !searchResults.isEmpty();
    }

    public int getSearchResultsCount() {
        return searchResults.size();
    }

    public String getSearchTerm() {
        if (isDisplayed(searchTermDisplay)) {
            return getText(searchTermDisplay);
        }
        return "";
    }

    public ProductPage clickOnProduct(int index) {
        if (index < productTitles.size()) {
            WebElement product = productTitles.get(index);
            scrollToElement(product);
            click(product);
            return new ProductPage(driver);
        }
        throw new IndexOutOfBoundsException("Product index out of range");
    }

    public ProductPage clickOnFirstProduct() {
        return clickOnProduct(0);
    }

    public String getProductTitle(int index) {
        if (index < productTitles.size()) {
            return getText(productTitles.get(index));
        }
        return "";
    }

    public List<String> getAllProductTitles() {
        return productTitles.stream()
                .map(this::getText)
                .toList();
    }

    public boolean hasPagination() {
        return !paginationItems.isEmpty();
    }

    public void appliFilter(String filterName) {
        WebElement filter = driver.findElement(
                By.xpath("//span[contains(text(), '" + filterName + "')}")
        );
        scrollToElement(filter);
        click(filter);
    }

    public void sortBy(String sortOption) {
        WebElement sortDropdown = driver.findElement(
                By.id("s-result-sort-select")
        );
        click(sortDropdown);

        WebElement option = driver.findElement(
          By.xpath("//option[contains(text().'" + sortOption + "')}")
        );
        click(option);
    }
}