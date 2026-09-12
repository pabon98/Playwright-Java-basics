import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBoxHandling {
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
    public void openUrl() throws InterruptedException {
        page.navigate("https://www.qapractice.com/practice-different-ui-elements");
        Thread.sleep(2000);
    }
    @Test
    public void clickOnSingleCheckBox() throws InterruptedException {
        ElementHandle checkBoxElem = page.querySelector("#ui-single-checkbox");
        checkBoxElem.click();
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "openUrl")
    public void clickMultipleCheckBoxes() throws InterruptedException {
        List<ElementHandle> elements = page.querySelectorAll(".form-check-input");
        for(ElementHandle checkbox: elements){
            if (!checkbox.isChecked()){
                checkbox.click();
                Thread.sleep(2000);
            }
        }
        Thread.sleep(4000);
    }
    @Test(dependsOnMethods = "openUrl")
    public void clickMultipleUnCheckBoxes() throws InterruptedException {
        List<ElementHandle> elements = page.querySelectorAll(".form-check-input");
        for(ElementHandle uncheckBox: elements){
            if (uncheckBox.isChecked()){
                uncheckBox.click();
                Thread.sleep(2000);
            }
        }
        Thread.sleep(4000);
    }
    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
