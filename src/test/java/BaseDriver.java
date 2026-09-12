import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseDriver {

    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
     playwright = Playwright.create();
     browserType = playwright.chromium();
     browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
     browserContext = browser.newContext();
     page = browser.newPage();
        System.out.println("Browser Version: " +browser.version());

    }
    @Test
    public void OpenUrl() throws InterruptedException {
        page.navigate("https://playwright.dev/java/");
        Thread.sleep(2000);
    }
    @Test
    public void locators() throws InterruptedException {
        ElementHandle buttonElement = page.querySelector("//a[normalize-space()='Get started']");
        buttonElement.click();
        Thread.sleep(2000);

    }
    @AfterSuite
    public void stop(){
     page.close();
     browser.close();
     playwright.close();
    }
}
