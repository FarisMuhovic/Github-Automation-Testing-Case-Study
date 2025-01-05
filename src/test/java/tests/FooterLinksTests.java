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
        navigateToFooterAndClick(() -> new HomePage(driver).clickSubscribe(), "/newsletter");
    }

    @Test
    public void testNavigationToCli() {
        navigateToFooterAndClick(() -> new HomePage(driver).clickCli(), "cli");
    }

    @Test
    public void testNavigationToDesktop() {
        navigateToFooterAndClick(() -> new HomePage(driver).clickDesktop(), "/desktop");
    }

    @Test
    public void testNavigationToMobile() {
        navigateToFooterAndClick(() -> new HomePage(driver).clickMobile(), "/mobile");
    }

    private void navigateToFooterAndClick(Runnable action, String expectedUrlFragment) {
        setWindowSize();
        navigateToBaseUrl();
        scrollToFooter();
        action.run();
        assertCurrentUrlContains(expectedUrlFragment);
    }

    private void setWindowSize() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    private void navigateToBaseUrl() {
        driver.get(BASE_URL);
    }

    private void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    private void assertCurrentUrlContains(String expectedUrlFragment) {
        assertTrue(driver.getCurrentUrl().contains(expectedUrlFragment),
                "Failed to navigate to the page containing: " + expectedUrlFragment);
    }
}
