import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void openBrowser(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browser.newPage();
    }
    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();


    }
    @Test
    public void openUrl() throws InterruptedException {
        page.navigate("https://www.qapractice.com/practice-ecommerece-website");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void scrollToBottom() throws InterruptedException {
        String script = "window.scrollTo(0, document.body.scrollHeight);";
        page.evaluate(script);
        Thread.sleep(2000);
    }
    @Test
    public void scrollToTop() throws InterruptedException {
        String script = "window.scrollTo(0,0);";
        page.evaluate(script);
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "openUrl")
    public void scrollToSpecifiqLocation() throws InterruptedException {
       ElementHandle specifiqLocation = page.getByText("\uD83C\uDFAF Locator Cheat Sheet").elementHandle();
       specifiqLocation.scrollIntoViewIfNeeded();
       specifiqLocation.click();
       Thread.sleep(3000);

    }
}
