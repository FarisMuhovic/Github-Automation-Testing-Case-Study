package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegisterPage {
    private final WebDriver driver;

    // Locators for form fields
    private final By emailField = By.id("email");
    private final By usernameField = By.id("login");
    private final By passwordField = By.id("password");
    private final By continueButton = By.className("js-octocaptcha-load-captcha");

    // Error messages
    private final By emailError = By.cssSelector("div .error > .mb-0");
    private final By passwordError = By.cssSelector("div .error > .password-validity-summary");
    private final By usernameError = By.cssSelector("div .error > div > .mb-1");
    private final By captchaPage = By.id("captcha-container-nux");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailField);
        emailElement.sendKeys(email);
        loseFocus(emailElement);
    }

    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
        loseFocus(usernameElement);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
        loseFocus(passwordElement);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    private void loseFocus(WebElement element) {
        // Create an Actions object to simulate mouse movement
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().perform();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getEmailError() {
        return emailError;
    }

    public By getPasswordError() {
        return passwordError;
    }

    public By getUsernameError() {
        return usernameError;
    }

    public By getCaptchaPage() {
        return captchaPage;
    }
}
