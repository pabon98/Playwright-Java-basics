import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class GetCommands {
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
        Thread.sleep(3000);
    }
    @Test (dependsOnMethods = "openUrl")
    public void fetch(){
        System.out.println("Title: "+page.title());
        System.out.println("Url: "+page.url());
        System.out.println("Page Source: "+page.content());

    }

    @Test (dependsOnMethods = "openUrl")
    public void getCSSValue(){
        Locator addToCartLocator = page.getByTestId("add-to-cart-1");
        String backgroundColor = addToCartLocator.evaluate("element => getComputedStyle(element).backgroundColor").toString();
        System.out.println("Button Background Color Is:  "+backgroundColor);

    }
}
