package tests;

import base.BaseClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TitleTests extends BaseClass {

    @Test
    public void testLoginPageTitle() {
        driver.get("https://github.com/login");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Sign in to GitHub"), "Login page title does not contain 'Sign in to GitHub'");
    }

    @Test
    public void testSignupPageTitle() {
        driver.get("https://github.com/signup");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Sign up to GitHub"), "Signup page title does not contain 'Sign up to GitHub'");
    }

    @Test
    public void testPricingPageTitle() {
        driver.get("https://github.com/pricing");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Pricing"), "Pricing page title does not contain 'Pricing'");
    }

    @Test
    public void testCopilotPageTitle() {
        driver.get("https://github.com/features/copilot");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("GitHub Copilot"), "GitHub Copilot page title does not contain 'GitHub Copilot'");
    }

}
