package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpsEnforcementTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");

    @Test
    public void testRedirectToHttps() {
        driver.get("http://github.com");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "Application does not redirect to HTTPS");
    }

    @Test
    public void testSecurePageHttps() {
        driver.get(LOGIN_URL);
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "Login page is not served over HTTPS");
    }

    @Test
    public void testValidSslCertificate() {
        driver.get("https://www.ssllabs.com/ssltest/analyze.html?d=github.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isValid = wait.until(
                driver -> driver.getPageSource().contains("This server supports TLS")
                        || driver.getPageSource().contains("HTTP Strict Transport Security (HSTS) with long duration deployed on this server")
                        || driver.getPageSource().contains("DNS Certification Authority Authorization (CAA) Policy found for this domain.")
        );

        assertTrue(isValid, "SSL certificate is not valid.");
    }
}
