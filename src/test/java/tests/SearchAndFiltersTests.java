package tests;

import base.BaseClass;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchAndFiltersPage;
import utils.ConfigReader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchAndFiltersTests extends BaseClass {

    private static final String SEARCH_URL = ConfigReader.getProperty("searchUrl");

    @Test
    @Order(1)
    public void testSearchForPublicRepository() {
        driver.get(SEARCH_URL);

        SearchAndFiltersPage searchAndFiltersPage = new SearchAndFiltersPage(driver);
        searchAndFiltersPage.enterSearch("Selenium");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div:first-child")));

        WebElement repos = driver.findElement(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div:first-child"));
        String elementText = repos.getText();
        assertTrue(elementText.contains("Selenium"), "The text does not contain 'Selenium'.");
    }

    @Test
    @Order(2)
    public void testFilterByProgrammingLanguage() {
        // here we can automatically search for selenium + python language,
        // because the languages in github filters are anchor tags with these properties.

        driver.get("https://github.com/search?q=selenium+language%3APython&type=repositories");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div:first-child")));

        WebElement repos = driver.findElement(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div:first-child"));
        String elementText = repos.getText();
        assertTrue(elementText.contains("Python"), "The text does not contain 'Python'.");
    }

    @Test
    @Order(3)
    public void testSortByStars() {
        // Same like test 2
        driver.get("https://github.com/search?q=selenium+language%3APython&type=repositories&s=stars&o=desc");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div:first-child")));

        List<WebElement> repos = driver.findElements(By.cssSelector(".Box-sc-g0xbh4-0 .gZKkEq > div"));
        List<Double> starCounts = new ArrayList<>();

        for (WebElement repo : repos) {
            WebElement starsElement = repo.findElement(By.cssSelector("a[aria-label*='stars'] span"));
            String starsCountText = starsElement.getText();

            double starsCount = parseStarsCount(starsCountText);
            starCounts.add(starsCount);
        }

        List<Double> sortedStarCounts = new ArrayList<>(starCounts);
        Collections.sort(sortedStarCounts, Collections.reverseOrder());

        // Assert that the actual star counts are in descending order
        assertTrue(starCounts.equals(sortedStarCounts), "Star counts are not in descending order.");
    }

    private double parseStarsCount(String starsCountText) {
        if (starsCountText.endsWith("k")) {
            return Double.parseDouble(starsCountText.replace("k", "")) * 1000;
        }
        return Double.parseDouble(starsCountText);
    }
}
