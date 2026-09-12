import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropDownHandle {
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
        System.out.println("Browser Version: " + browser.version());

    }

    @Test
    public void OpenUrl() throws InterruptedException {
        page.navigate("https://www.qapractice.com/practice-forms");
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "OpenUrl")
    public void SelectByIndex() throws InterruptedException {
        Locator countryDropdown = page.locator("#forms-country");
        countryDropdown.selectOption(new SelectOption().setIndex(2));
        Thread.sleep(2000);

    }
    @Test(dependsOnMethods = "OpenUrl")
    public void SelectByValue() throws InterruptedException {
        Locator countryDropdown = page.locator("#forms-country");
        countryDropdown.selectOption(new SelectOption().setValue("India"));
        Thread.sleep(2000);

    }
    @Test(dependsOnMethods = "OpenUrl")
    public void SelectByVisibleText() throws InterruptedException {
        Locator countryDropdown = page.locator("#forms-country");
        countryDropdown.selectOption(new SelectOption().setLabel("Other"));
        Thread.sleep(2000);

    }




    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
