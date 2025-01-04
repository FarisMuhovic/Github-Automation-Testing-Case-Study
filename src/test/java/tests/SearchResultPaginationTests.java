package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

public class SearchResultPaginationTests extends BaseClass {
    private static final String SEARCH_URL = ConfigReader.getProperty("searchUrl");
    private static final String SEARCH_QUERY = "Selenium";
    private static final String BASE_SEARCH_URL = "https://github.com/search?q=selenium&type=repositories";

    @Test
    public void testNavigateToSecondPage() {
        driver.get(BASE_SEARCH_URL);

        // Navigate to the second page directly by changing the URL
        driver.get(BASE_SEARCH_URL + "&p=2");

        // Validate that the second page URL is correct
        assert driver.getCurrentUrl().contains("p=2") : "Second page of results not loaded correctly";
    }

    @Test
    public void testNavigateBackToFirstPage() {
        driver.get(BASE_SEARCH_URL);

        // Navigate to the second page directly by changing the URL
        driver.get(BASE_SEARCH_URL + "&p=2");

        // Validate that the second page URL is correct
        assert driver.getCurrentUrl().contains("p=2") : "Second page of results not loaded correctly";

        // Navigate back to the first page directly by changing the URL
        driver.get(BASE_SEARCH_URL);

        // Validate that the first page URL is correct again
        assert driver.getCurrentUrl().equals(BASE_SEARCH_URL) : "Failed to return to the first page";
    }

    @Test
    public void testDirectPageNavigation() {
        driver.get(BASE_SEARCH_URL);
        // Navigate directly to page 3 by changing the URL
        driver.get(BASE_SEARCH_URL + "&p=3");

        // Validate that the third page URL is correct
        assert driver.getCurrentUrl().contains("p=3") : "Page 3 of results not loaded correctly";
    }
}
