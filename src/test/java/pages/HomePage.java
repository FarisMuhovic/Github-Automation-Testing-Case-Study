package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By signUpLink = By.linkText("Sign up");
    private final By loginLink = By.linkText("Sign in");
    private final By pricingLink = By.linkText("Pricing");
    private final By copilotLink = By.xpath("//span[text()='Try GitHub Copilot']");

    // footer links
    private final By subscribeLink = By.linkText("Subscribe");
    private final By cliLink = By.linkText("GitHub CLI");
    private final By desktopLink = By.linkText("GitHub Desktop");
    private final By mobileLink = By.linkText("GitHub Mobile");

    public void clickSignup() {
        driver.findElement(signUpLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickPricing() {
        driver.findElement(pricingLink).click();
    }

    public void clickCopilot() {
        driver.findElement(copilotLink).click();
    }

    public void clickSubscribe() {
        driver.findElement(subscribeLink).click();
    }

    public void clickCli() {
        driver.findElement(cliLink).click();
    }

    public void clickDesktop() {
        driver.findElement(desktopLink).click();
    }

    public void clickMobile() {
        driver.findElement(mobileLink).click();
    }
}
