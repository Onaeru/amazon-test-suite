package com.epam.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtils {
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = "screenshots/" + fileName;

        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);

            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved in: " + filePath);

            return filePath;
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + e.getMessage());
            return null;
        }
    }

    // Generate random email for testing

    public static String generateRandomEmail() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return "test_" + timestamp + "@example.com";
    }

    // Generate random phone number

    public static String generateRandomPhone() {
        Random random = new Random();
        return String.format("555-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000));
    }

    // Generate random zipcode

    public static String generateRandomZipCode() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Get current timestamp

    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // Clean String

    public static String cleanString(String input) {
        if (input == null) return "";
        return input.trim().replaceAll("\\s+"," ");
    }

    // Extract numbers

    public static String extractNumbers(String input) {
        if (input == null) return "";
        return input.replaceAll("[^0-9]", "");
    }

    public static double parsePrice(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            return 0.0;
        }

        String cleanPrice = priceString.replaceAll("[^0-9.]", "");

        try {
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceString);
            return 0.0;
        }
    }

    // Generate test data for address

    public static class TestAddress {
        public String name = "Lau Car";
        public String phone = generateRandomPhone();
        public String address = "102 Test Mont";
        public String city = "Cordoba";
        public String state = "Cordoba";
        public String zipCode = "5000";
    }

    // Obtain test address

    public static TestAddress getTestAddress() {
        return new TestAddress();
    }
}
