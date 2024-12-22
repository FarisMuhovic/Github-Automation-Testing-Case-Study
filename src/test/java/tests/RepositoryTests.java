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
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getPageSource().contains("New"));

        RepositoryPage repositoryPage = new RepositoryPage(driver, loginPage);
        repositoryPage.clickNewRepoButton();
        String repoName = "TestRepo" + Math.floor(Math.random() * 10000);
        repositoryPage.enterRepoName(repoName);
        repositoryPage.enterRepoDescription("This is a test repository created for Selenium automation.");

        // Wait for the validation status to be 'success' for repo name. (validation is complete)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RepoNameInput-is-available")));

        repositoryPage.clickCreateRepoButton();

        wait.until(ExpectedConditions.urlContains(repoName));

        assertTrue(driver.getCurrentUrl().contains(repoName), "Failed to create the repository!");
        System.out.println("Repository " + repoName + " was successfully created.");
    }

    @Test
    @Order(3)
    public void testPrivateRepoVisibility() {
        driver.get(PRIVATE_REPO_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isPrivateRepo = wait.until(
                driver -> driver.getPageSource().contains("404")
                        || driver.getPageSource().contains("This is not the web page you are looking for"));

        assertTrue(isPrivateRepo, "Private repository should not be visible to unauthorized users.");
        System.out.println("Test Passed: Private repository visibility is restricted.");
    }

    @Test
    @Order(2)
    public void testDeleteRepository() throws InterruptedException {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        driver.get(REPOS_URL);
        WebElement firstRepo = driver.findElement(By.cssSelector("#user-repositories-list > ul > li:first-child a"));
        firstRepo.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement settingsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("settings-tab")));

        settingsTab.click();
        Thread.sleep(2500);
        WebElement deleteButton = driver.findElement(By.id("dialog-show-repo-delete-menu-dialog"));
        deleteButton.click();
        Thread.sleep(2500);

        WebElement proceedDelete = driver.findElement(By.id("repo-delete-proceed-button"));
        proceedDelete.click();
        Thread.sleep(2500);

        WebElement proceedDeleteRerender = driver.findElement(By.id("repo-delete-proceed-button"));
        proceedDeleteRerender.click();
        Thread.sleep(2500);


        WebElement confirmationLabel = driver.findElement(By.xpath("//label[contains(text(), 'To confirm, type')]"));
        String repoName = confirmationLabel.getText().split("\"")[1];  // Extract the repository name from the label

        Thread.sleep(2500);

        WebElement inputField = driver.findElement(By.id("verification_field"));
        inputField.sendKeys(repoName);

        // Step 3: Wait for the "Delete this repository" button to become enabled
        WebDriverWait waitR = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButtonR = waitR.until(ExpectedConditions.elementToBeClickable(By.id("repo-delete-proceed-button")));

        deleteButtonR.click();

        Thread.sleep(2000);
        WebDriverWait waittt = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flashMessage = waittt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash-notice .js-flash-alert")));
        String messageText = flashMessage.getText();

        System.out.println(messageText);
        if (messageText.contains("was successfully deleted")) {
            System.out.println("Repository successfully deleted: ");
        } else {
            System.out.println("Unexpected message:");
        }

    }

}
