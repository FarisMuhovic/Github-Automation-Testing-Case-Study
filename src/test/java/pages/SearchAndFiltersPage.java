package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchAndFiltersPage {
    private final WebDriver driver;

    private final By searchField = By.xpath("//input[@aria-label='Search GitHub']");


    public SearchAndFiltersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String query) {
        driver.findElement(searchField).sendKeys(query);
        driver.findElement(searchField).sendKeys(Keys.RETURN);
    }
}
