import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {

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
    @Test(priority = 0)
    public void OpenUrl() throws InterruptedException {
        page.navigate("https://www.qapractice.com/practice-forms");
        Thread.sleep(2000);
    }
    @Test(priority = 1)
    public void locateById() throws InterruptedException {
        ElementHandle firstName = page.getByTestId("forms-first-name").elementHandle();
        firstName.fill("Hasin");
        ElementHandle lastName = page.getByTestId("forms-last-name").elementHandle();
        lastName.fill("Pabon");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void locateByTagName() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("input");
        System.out.println("Element Size: " +element.size());
        Thread.sleep(3000);

        for (ElementHandle handle: element){
            String id = handle.getAttribute("id");
            System.out.println("ID :" +id );
        }
    }
    @Test(priority = 3)
    public void LocateByXpath() throws InterruptedException {
        ElementHandle emailField = page.querySelector("//input[@id='forms-email']");
        emailField.fill("hasin@test.com");
        Thread.sleep(3000);
    }
    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}


