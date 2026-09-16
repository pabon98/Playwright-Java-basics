import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class IframeHandle {
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
        page.navigate("https://www.automationtesting.co.uk/iframes.html");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void countIframe(){
        List<ElementHandle> iframes = page.querySelectorAll("iframe");
        System.out.println("Iframe Size: "+iframes.size());
    }

}
