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

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");
    private static final String FORGOT_PASSWORD_URL = ConfigReader.getProperty("forgotPasswordUrl");
    private static final String CREATE_ACCOUNT_URL = ConfigReader.getProperty("createAccountUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");
    private static final String INVALID_EMAIL = ConfigReader.getProperty("invalidEmail");
    private static final String INVALID_PASSWORD = ConfigReader.getProperty("invalidPassword");

    private void login(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
    }

    @Test
    public void testValidLogin() {
        driver.get(LOGIN_URL);
        login(VALID_EMAIL, VALID_PASSWORD);
        assertTrue(driver.getPageSource().contains("Dashboard"), "Login failed!");
        System.out.println("Test Passed: Login was successful.");
    }

    @Test
    public void testInvalidLogin() {
        driver.get(LOGIN_URL);
        login(INVALID_EMAIL, INVALID_PASSWORD);
        assertTrue(driver.getPageSource().contains("Incorrect username or password."), "Expected error message 'Incorrect username or password.' was not found on the page.");
        System.out.println("Test Passed: Expected error message found.");
    }

    @Test
    public void testPasswordFieldMasking() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        WebElement passwordField = driver.findElement(loginPage.getPasswordField());
        String inputType = passwordField.getDomAttribute("type");
        assertEquals("password", inputType, "Password field should mask the input.");
        System.out.println("Test Passed: Password field is masked.");
    }


    @Test
    public void testForgotPasswordRedirection() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(FORGOT_PASSWORD_URL, currentUrl, "The Forgot Password redirection failed.");
        System.out.println("Test Passed: Correct redirection to the Forgot Password page.");
    }

    @Test
    public void testCreateAccountRedirection() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccountLink();

        WebDriverWait waitForRedirect = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForRedirect.until(ExpectedConditions.urlContains(CREATE_ACCOUNT_URL));

        String currentUrl = driver.getCurrentUrl();

        assertEquals(CREATE_ACCOUNT_URL, currentUrl, "The Create Account redirection failed.");
        System.out.println("Test Passed: Correct redirection to the Create Account page.");
    }

    @Test
    public void testEmailNotRegistered() {
        driver.get(LOGIN_URL);
        login("nonexistent@domainf.fdcom", VALID_PASSWORD);
        assertTrue(driver.getPageSource().contains("Incorrect username or password."), "Error message not shown for unregistered email.");
        System.out.println("Test Passed: Non-registered email is correctly handled.");
    }

    @Test
    public void testLogout() {
        driver.get(LOGIN_URL);
        login(VALID_EMAIL, VALID_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Open user navigation menu']")));
        profileButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']")));
        logoutButton.click();

        WebElement signOutFromAccount = wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        signOutFromAccount.click();

        assertFalse(driver.getPageSource().contains("Dashboard"), "Logout failed. User not redirected to login page");
        System.out.println("Test Passed: User logged out successfully.");
    }


}
