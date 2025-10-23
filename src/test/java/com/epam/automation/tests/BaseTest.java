package com.epam.automation.tests;

import com.epam.automation.pages.HomePage;
import com.epam.automation.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.amazon.com/";

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browser) {
        driver = initializeDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(BASE_URL);

        HomePage homePage = new HomePage(driver);
        homePage.handlePossibleVerificationOrIntermediatePage();
    }

    private WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins-*");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser " + browser + " not supported");
        }

        return driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            System.out.println("Test FAILED: " + testName);
            TestUtils.takeScreenshot(driver, testName);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
