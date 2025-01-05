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
        loginAndNavigateToProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        waitForPageContains("Edit profile");

        profilePage.editProfile();

        String updatedName = "Hippopotamus";
        String updatedBio = "This is my new bio.";

        profilePage.updateName(updatedName);
        profilePage.updateBio(updatedBio);
        profilePage.saveChanges();

        waitForPageContains("Edit profile");

        assertTrue(isTextPresentOnPage(updatedName), "Name is not present.");
        assertTrue(isTextPresentOnPage(updatedBio), "Bio is not present.");
    }

    @Test
    public void testUpdateUserStatus() {
        loginAndNavigateToProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        waitForPageContains("Edit profile");

        String status = "On Vacation :)";
        profilePage.setStatus(status);
        profilePage.saveStatus();

        profilePage.seeStatus();

        assertTrue(isTextPresentOnPage(status), "Status didn't save.");
    }

    private void loginAndNavigateToProfile() {
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickSignIn();

        driver.get(PROFILE_URL);
    }

    private void waitForPageContains(String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getPageSource().contains(text));
    }

    private boolean isTextPresentOnPage(String text) {
        return driver.getPageSource().contains(text);
    }
}
