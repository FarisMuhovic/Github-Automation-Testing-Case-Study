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
    public void testSecurityPageTitle() {
        driver.get("https://github.com/features/security");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Security"), "Security page title does not contain 'Security'");
    }

    @Test
    public void testCopilotPageTitle() {
        driver.get("https://github.com/features/copilot");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("GitHub Copilot"), "GitHub Copilot page title does not contain 'GitHub Copilot'");
    }

    @Test
    public void testSponsorsPageTitle() {
        driver.get("https://github.com/sponsors");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("GitHub Sponsors"), "GitHub Sponsors page title does not contain 'GitHub Sponsors'");
    }


}
