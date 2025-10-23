package com.epam.automation.tests;

import com.epam.automation.pages.HomePage;
import com.epam.automation.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{
    private HomePage homePage;

    @BeforeMethod
    public void setUpPage() {
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, description = "Verify Amazon home page loads correctly")
    public void testHomePageLoads() {
        Assert.assertTrue(homePage.isHomePageLoaded(),
               "Home page should load successfully" );
        Assert.assertTrue(homePage.isAmazonLogoDisplayed(),
                "Amazon logo should be visible");
    }

    @Test(priority = 2, description = "Verify search functionality with valid product")
    public void testSearchProduct() {
        String searchTerm = "laptop";
        SearchResultsPage searchResults = homePage.searchProduct(searchTerm);

        Assert.assertTrue(searchResults.isSearchResultsDisplayed(),
                "Search results should be displayed");
        Assert.assertTrue(searchResults.getSearchResultsCount() > 0,
                "Should have at least one search result");
    }
}
