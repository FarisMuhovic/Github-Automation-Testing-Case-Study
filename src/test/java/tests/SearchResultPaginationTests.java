package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;

public class SearchResultPaginationTests extends BaseClass {
    private static final String BASE_SEARCH_URL = "https://github.com/search?q=selenium&type=repositories";

    @Test
    public void testNavigateToSecondPage() {
        driver.get(BASE_SEARCH_URL);

        driver.get(BASE_SEARCH_URL + "&p=2");

        assert driver.getCurrentUrl().contains("p=2") : "Second page of results not loaded correctly";
    }

    @Test
    public void testNavigateBackToFirstPage() {
        driver.get(BASE_SEARCH_URL);

        driver.get(BASE_SEARCH_URL + "&p=2");

        assert driver.getCurrentUrl().contains("p=2") : "Second page of results not loaded correctly";

        driver.get(BASE_SEARCH_URL);

        assert driver.getCurrentUrl().equals(BASE_SEARCH_URL) : "Failed to return to the first page";
    }

    @Test
    public void testDirectPageNavigation() {
        driver.get(BASE_SEARCH_URL);
        driver.get(BASE_SEARCH_URL + "&p=3");

        assert driver.getCurrentUrl().contains("p=3") : "Page 3 of results not loaded correctly";
    }
}
