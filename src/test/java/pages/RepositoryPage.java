package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RepositoryPage {
    private final WebDriver driver;
    public final LoginPage loginPage;

    private final By newRepoButton = By.cssSelector("a[href='/new']");
    private final By repoNameInput = By.cssSelector("[data-testid='repository-name-input']");
    private final By repoDescriptionInput = By.name("Description");
    private final By repoCreateButton = By.cssSelector("button[type='submit'] span.prc-Button-Label-pTQ3x");

    public RepositoryPage(WebDriver driver, LoginPage loginPage) {
        this.driver = driver;
        this.loginPage = loginPage;
    }

    public void clickNewRepoButton() {
        driver.findElement(newRepoButton).click();
    }

    public void enterRepoName(String name) {
        driver.findElement(repoNameInput).sendKeys(name);
    }

    public void enterRepoDescription(String description) {
        driver.findElement(repoDescriptionInput).sendKeys(description);
    }

    public void clickCreateRepoButton() {
        driver.findElement(repoCreateButton).click();
    }

}
