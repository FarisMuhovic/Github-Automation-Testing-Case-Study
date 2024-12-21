package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.id("login_field");
    private final By passwordField = By.id("password");
    private final By signInButton = By.name("commit");
    private final By forgotPasswordLink = By.id("forgot-password");
    private final By createAccountLink = By.xpath("//a[@href='/signup?source=login']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }


    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public void clickCreateAccountLink() {
        driver.findElement(createAccountLink).click();
    }

    public By getEmailField() {
        return emailField;
    }

    public By getPasswordField() {
        return passwordField;
    }

    public By getSignInButton() {
        return signInButton;
    }

    public By getCreateAccountLink() {
        return createAccountLink;
    }
}
