package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SessionHandlingTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");

    @Test
    public void testSessionPersistence() {
        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        driver.navigate().refresh();

        assertTrue(driver.getPageSource().contains("Dashboard"), "Session is not persistent after page reload.");
        System.out.println("Session persists after page reload.");
    }
    @Test
    public void testSessionExpirationAfterLogout() {
        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Open user navigation menu']")));
        profileButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']")));
        logoutButton.click();

        WebElement signOutFromAccount = wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        signOutFromAccount.click();

        driver.get("https://github.com/dashboard");

        assertTrue(driver.getCurrentUrl().contains("https://github.com/login"), "User should be redirected to login page after logout");
        System.out.println("Test passed: User is being redirected to login after accessing forbidden route.");
    }
}
