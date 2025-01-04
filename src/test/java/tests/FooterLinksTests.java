package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import pages.HomePage;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooterLinksTests extends BaseClass {

    private static final String BASE_URL = ConfigReader.getProperty("baseUrl");

    @Test
    public void testNavigationToSubscribe() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Scroll to the bottom of the page to ensure the footer is loaded
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        HomePage homePage = new HomePage(driver);
        homePage.clickSubscribe();
        assertTrue(driver.getCurrentUrl().contains("/newsletter"), "Failed to navigate to newsletter page");
    }

    @Test
    public void testNavigationToCli() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Scroll to the bottom of the page to ensure the footer is loaded
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        HomePage homePage = new HomePage(driver);
        homePage.clickCli();
        assertTrue(driver.getCurrentUrl().contains("cli"), "Failed to navigate to Cli page");
    }

    @Test
    public void testNavigationToDesktop() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Scroll to the bottom of the page to ensure the footer is loaded
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        HomePage homePage = new HomePage(driver);
        homePage.clickDesktop();
        assertTrue(driver.getCurrentUrl().contains("/desktop"), "Failed to navigate to Desktop page");
    }

    @Test
    public void testNavigationToMobile() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Scroll to the bottom of the page to ensure the footer is loaded
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        HomePage homePage = new HomePage(driver);
        homePage.clickMobile();
        assertTrue(driver.getCurrentUrl().contains("/mobile"), "Failed to navigate to mobile page");
    }
}
