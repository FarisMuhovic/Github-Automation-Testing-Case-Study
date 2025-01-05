package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceTests extends BaseClass {
    private static final String BASE_URL = ConfigReader.getProperty("baseUrl");

    @Test
    public void testPageLoadTime() {
        driver.get(BASE_URL);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadTime = (Long) js.executeScript(
                "return performance.timing.loadEventEnd - performance.timing.navigationStart;");
        assertTrue(loadTime < 3000, "Page didn't load within 3 seconds");
        System.out.println("Page load time test passed, It is: " + loadTime + "ms");
    }

    @Test
    public void testApiResponseTime() {
        driver.get(BASE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        double apiResponseTime = (double) js.executeScript(
                "let entries = performance.getEntriesByType('resource');" +
                        "let apiCalls = entries.filter(e => e.initiatorType === 'fetch');" +
                        "return apiCalls.reduce((sum, e) => sum + e.duration, 0);"
        );
        System.out.println("API response time: " + apiResponseTime + "ms");
        assertTrue(apiResponseTime < 2000, "API calls should complete within 2 seconds");
    }
}
