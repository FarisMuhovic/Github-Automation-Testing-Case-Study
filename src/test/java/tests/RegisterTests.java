package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTests extends BaseClass {

    private static final String REGISTER_URL = ConfigReader.getProperty("registerUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String INVALID_EMAIL_FORMAT = ConfigReader.getProperty("invalidEmailFormat");
    private static final String VALID_USERNAME = ConfigReader.getProperty("validUsername");
    private static final String INVALID_USERNAME = ConfigReader.getProperty("invalidUsername");
    private static final String WEAK_PASSWORD = ConfigReader.getProperty("weakPassword");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");

    private void register(String email, String username, String password) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterEmail(email);
        registerPage.enterUsername(username);
        registerPage.enterPassword(password);
        registerPage.clickContinue();
    }

    @Test
    public void testValidRegistration() {
        driver.get(REGISTER_URL);
        register(VALID_EMAIL, VALID_USERNAME, VALID_PASSWORD); // Make sure to change the data in configreader to be unique for username and email
        RegisterPage registerPage = new RegisterPage(driver);
        WebElement captcha = driver.findElement(registerPage.getCaptchaPage());
        // CAPTCHA will prevent the form from being submitted, so we stop before it.
        assertNotNull(captcha, "Terms of Service text not displayed.");
    }

    @Test
    public void testInvalidEmail() {
        driver.get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterEmail(INVALID_EMAIL_FORMAT);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getEmailError()));

        assertNotNull(emailError, "Email error not displayed.");
    }

    @Test
    public void testWeakPassword() {
        driver.get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterPassword(WEAK_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getPasswordError()));

        assertNotNull(passwordError, "Error message for weak password not shown.");
    }

    @Test
    public void testUsernameNotAvailable() {
        driver.get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterUsername(INVALID_USERNAME);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getUsernameError()));

        assertNotNull(usernameError, "Error message for unavailable username not shown.");
    }
}
