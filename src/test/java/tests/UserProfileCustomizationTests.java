package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProfilePage;
import utils.ConfigReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserProfileCustomizationTests extends BaseClass {
    private static final String LOGIN_URL = ConfigReader.getProperty("loginUrl");
    private static final String VALID_EMAIL = ConfigReader.getProperty("validEmail");
    private static final String VALID_PASSWORD = ConfigReader.getProperty("validPassword");
    private static final String PROFILE_URL = ConfigReader.getProperty("profileUrl");

    @Test
    public void testUpdateProfileInformation() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        driver.get(PROFILE_URL);
        ProfilePage profilePage = new ProfilePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getPageSource().contains("Edit profile"));

        profilePage.editProfile();

        String updatedName = "Hippopotamusss";
        String updatedBio = "This is my updated bio.";

        profilePage.updateName(updatedName);
        profilePage.updateBio(updatedBio);

        profilePage.saveChanges();

        wait.until(driver -> driver.getPageSource().contains("Edit profile"));

        boolean isNamePresent = driver.getPageSource().contains(updatedName);
        boolean isBioPresent = driver.getPageSource().contains(updatedBio);

        assertTrue(isNamePresent, "Name is not present.");
        assertTrue(isBioPresent, "Bio is not present.");

        System.out.println("Successfully updated user information");
    }

    @Test
    public void testUpdateUserStatus() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        driver.get(PROFILE_URL);
        ProfilePage profilePage = new ProfilePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getPageSource().contains("Edit profile"));

        String status = "On Vacation :)";
        profilePage.setStatus(status);
        profilePage.saveStatus();

        profilePage.seeStatus();

        boolean isSaved = driver.getPageSource().contains(status);

        assertTrue(isSaved, "Status didn't save.");
        System.out.println("Status has been updated");
    }
}
