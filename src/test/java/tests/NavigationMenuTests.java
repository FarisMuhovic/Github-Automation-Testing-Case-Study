package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import pages.HomePage;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationMenuTests extends BaseClass {
    private static final String BASE_URL = ConfigReader.getProperty("baseUrl");

    @Test
    public void testNavigationToSignup() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickSignup();
        assertTrue(driver.getCurrentUrl().contains("/signup"), "Failed to navigate to Sign up page");
    }

    @Test
    public void testNavigationToLogin() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);

        homePage.clickLogin();

        assertTrue(driver.getCurrentUrl().contains("/login"), "Failed to navigate to Login page");
    }

    @Test
    public void testNavigationToPricing() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);

        homePage.clickPricing();

        assertTrue(driver.getCurrentUrl().contains("/pricing"), "Failed to navigate to Pricing page");
    }

    @Test
    public void testNavigationToCopilot() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);

        homePage.clickCopilot();

        assertTrue(driver.getCurrentUrl().contains("/copilot"), "Failed to navigate to Copilot page");
    }
}
