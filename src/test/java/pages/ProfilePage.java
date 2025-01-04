package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By editButton = By.name("button");
    private final By nameField = By.id("user_profile_name");
    private final By bioField = By.id("user_profile_bio");
    private final By saveButton = By.xpath("//button[contains(., 'Save')]");
    private final By statusModal = By.className("user-status-circle-badge-container");
    private final By statusMessage = By.id("message");
    private final By setStatusButton = By.xpath("//button[contains(@class, 'js-user-status-submit') and contains(., 'Set status')]");

    public void editProfile() {
        driver.findElement(editButton).click();
    }
    public void updateName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void updateBio(String bio) {
        driver.findElement(bioField).clear();
        driver.findElement(bioField).sendKeys(bio);
    }


    public void saveChanges() {
        driver.findElement(saveButton).click();
    }

    public void setStatus(String status) {
        driver.findElement(statusModal).click();
        driver.findElement(statusMessage).clear();
        driver.findElement(statusMessage).sendKeys(status);
    }

    public void seeStatus() {
        driver.findElement(statusModal).click();
    }

    public void saveStatus() {
        driver.findElement(setStatusButton).click();
    }
}
