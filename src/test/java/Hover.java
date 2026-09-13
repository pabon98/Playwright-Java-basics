import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Hover {

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

    @Test
    public void openUrl() throws InterruptedException {
        page.navigate("https://www.qapractice.com/practice-ecommerece-website");
        Thread.sleep(3000);

    }
    @Test(dependsOnMethods = "openUrl")
    public void hoverHandling() throws InterruptedException {
        Locator electronics = page.getByTestId("ecom-category-electronics");
        Locator fashion = page.getByTestId("ecom-category-fashion");
        electronics.hover();
        Thread.sleep(3000);
        fashion.hover();
        Thread.sleep(3000);
    }
    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }
}
