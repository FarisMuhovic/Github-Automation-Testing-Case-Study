package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RepositoryPage;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositoryErrorHandlingTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");

    private static final String REPO_NAME = "super-octo-adventure";
    private static final String REPO_DESCRIPTION = "This is a test repository.";

    @Test
    public void testRepositoryNameDuplication() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        RepositoryPage repositoryPage = new RepositoryPage(driver);

        repositoryPage.clickNewRepoButton();
        repositoryPage.enterRepoName(REPO_NAME);
        repositoryPage.enterRepoDescription(REPO_DESCRIPTION);
        repositoryPage.clickCreateRepoButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RepoNameInput-message")));

        boolean errorMessageExists = driver.getPageSource().contains("already exists on this account");
        assertTrue(errorMessageExists, "Error message for duplicate repository name is not displayed.");
        System.out.println("Error message is displayed for duplicate repository name.");
    }

    @Test
    public void testFormValidationErrors() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        RepositoryPage repositoryPage = new RepositoryPage(driver);

        repositoryPage.clickNewRepoButton();
        repositoryPage.clickCreateRepoButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RepoNameInput-message")));

        boolean errorMessageExists = driver.getPageSource().contains("repository name must not be blank");
        assertTrue(errorMessageExists, "Error message for empty repository name is not displayed.");
        System.out.println("Error message is displayed for empty repository name.");
    }
}
