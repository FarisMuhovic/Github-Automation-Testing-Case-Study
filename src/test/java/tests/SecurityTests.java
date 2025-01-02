package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import utils.ConfigReader;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");


    @Test
    public void testSecureHeaders() {
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        // Enable network interception to capture headers
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), response -> {
            var headers = response.getResponse().getHeaders();

            // Check for Content-Security-Policy header
            assertTrue(headers.containsKey("content-security-policy"),
                    "Content-Security-Policy header should be present");

            // Check for Strict-Transport-Security header
            assertTrue(headers.containsKey("strict-transport-security"),
                    "Strict-Transport-Security header should be present");
        });

        driver.get(LOGIN_URL);
    }

    @Test
    public void testCsrfTokenPresence() {
        driver.get(LOGIN_URL);

        // Locate the CSRF token in the login form
        WebElement csrfTokenElement = driver.findElement(By.name("authenticity_token"));
        String csrfToken = csrfTokenElement.getAttribute("value");

        assertNotNull(csrfToken, "CSRF token should be present in the login form");
    }

    @Test
    public void testSqlInjectionHandling() {
        driver.get(LOGIN_URL);

        // Input SQL injection payload
        driver.findElement(By.name("login")).sendKeys("' OR 1=1 --");
        driver.findElement(By.name("password")).sendKeys("invalidPassword");
        driver.findElement(By.name("commit")).click();

        // Verify that the login attempt failed
        WebElement errorMessage = driver.findElement(By.cssSelector("div.flash-error"));
        assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for SQL injection attempts");
    }
}
