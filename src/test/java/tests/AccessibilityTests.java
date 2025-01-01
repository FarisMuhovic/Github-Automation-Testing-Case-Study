package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessibilityTests extends BaseClass {

    @Test
    public void testLinksHaveDescriptiveText() {
        driver.get("https://github.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));

        for (WebElement link : driver.findElements(By.tagName("a"))) {
            String linkText = link.getText();

            if (linkText == null || linkText.trim().isEmpty()) {
                System.out.println("Link without descriptive text: " + link.getAttribute("outerHTML"));
            }

            assertTrue(linkText != null && !linkText.trim().isEmpty(), "Link don't have descriptive text");
            System.out.println("All links have descriptive text");
        }
    }

    @Test
    public void testFormLabelsOnLoginPage() {
        driver.get("https://github.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_field")));

        WebElement usernameLabel = driver.findElement(By.xpath("//label[@for='login_field']"));
        WebElement passwordLabel = driver.findElement(By.xpath("//label[@for='password']"));

        assertTrue(usernameLabel != null && passwordLabel != null, "Form fields don't have proper labels");
        System.out.println("Form fields have proper labels.");
    }

    @Test
    public void testKeyboardNavigationOnLoginPage() {
        driver.get("https://github.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_field")));

        WebElement usernameField = driver.findElement(By.id("login_field"));
        usernameField.click();
        usernameField.sendKeys("testUser");

        driver.findElement(By.id("password")).click();

        WebElement passwordField = driver.findElement(By.id("password"));
        assertTrue(passwordField.isDisplayed(), "Focus should be on the password field after tabbing");

        driver.quit();
    }

}
