package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected static ChromeOptions options;
    protected WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();

        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--window-size=1024,768");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Prevent detection
        options.setExperimentalOption("useAutomationExtension", false);
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(new Random().nextInt(5000) + 500); // Random delay between 500ms and 5500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("All tests have been executed.");
    }
}
