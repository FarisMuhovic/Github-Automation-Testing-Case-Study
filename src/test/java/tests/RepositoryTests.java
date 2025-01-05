package tests;

import base.BaseClass;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RepositoryPage;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");
    private static final String PRIVATE_REPO_URL = ConfigReader.getProperty("privateRepoUrl");
    private static final String REPOS_URL = ConfigReader.getProperty("reposUrl");

    @Test
    @Order(1)
    public void testCreateNewRepository() {
        loginAndNavigate(LOGIN_URL);

        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickNewRepoButton();

        String repoName = generateRepoName();
        repositoryPage.enterRepoName(repoName);
        repositoryPage.enterRepoDescription("This is a test repository created for Selenium automation.");

        waitForElementVisibility(By.id("RepoNameInput-is-available"));
        repositoryPage.clickCreateRepoButton();

        waitForUrlContains(repoName);
        assertTrue(driver.getCurrentUrl().contains(repoName), "Failed to create the repository!");
    }

    @Test
    @Order(3)
    public void testPrivateRepoVisibility() {
        driver.get(PRIVATE_REPO_URL);

        boolean isPrivateRepo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getPageSource().contains("404")
                        || driver.getPageSource().contains("This is not the web page you are looking for"));

        assertTrue(isPrivateRepo, "Private repository should not be visible to unauthorized users.");
    }

    @Test
    @Order(2)
    public void testDeleteRepository() throws InterruptedException {
        loginAndNavigate(LOGIN_URL);

        driver.get(REPOS_URL);

        WebElement firstRepo = waitForElementToBeClickable(By.cssSelector("#user-repositories-list > ul > li:first-child a"));
        firstRepo.click();

        WebElement settingsTab = waitForElementToBeClickable(By.id("settings-tab"));
        settingsTab.click();

        clickAndWait(By.id("dialog-show-repo-delete-menu-dialog"));
        clickAndWait(By.id("repo-delete-proceed-button"));
        clickAndWait(By.id("repo-delete-proceed-button"));

        WebElement confirmationLabel = waitForElementVisibility(By.xpath("//label[contains(text(), 'To confirm, type')]"));
        String repoName = extractRepoNameFromLabel(confirmationLabel);

        WebElement inputField = waitForElementToBeClickable(By.id("verification_field"));
        inputField.sendKeys(repoName);

        clickAndWait(By.id("repo-delete-proceed-button"));

        WebElement flashMessage = waitForElementVisibility(By.cssSelector(".flash-notice .js-flash-alert"));
        String messageText = flashMessage.getText();

        assertTrue(messageText.contains("was successfully deleted"),
                "Expected successful deletion message, but got: " + messageText);
    }

    private void loginAndNavigate(String url) {
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();
    }

    private String generateRepoName() {
        return "TestRepo" + Math.floor(Math.random() * 10000);
    }

    private WebElement waitForElementVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForUrlContains(String substring) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(substring));
    }

    private void clickAndWait(By locator) throws InterruptedException {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
        Thread.sleep(500);
    }

    private String extractRepoNameFromLabel(WebElement label) {
        return label.getText().split("\"")[1];
    }

}
