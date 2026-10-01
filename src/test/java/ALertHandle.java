import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ALertHandle {
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
        page.navigate("https://demoqa.com/alerts");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = "openUrl")
    public void simpleAlert() {
        page.onceDialog(dialog -> {
            System.out.println("Alert Message: " + dialog.message());

            try {
                Thread.sleep(3000); // Pause before accepting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            dialog.accept();
        });

        page.locator("#alertButton").click();
    }
    @Test(dependsOnMethods = "openUrl")
    public void confirmAlert() {
        page.onceDialog(dialog -> {
            System.out.println("Confirm Message: " + dialog.message());

            try {
                Thread.sleep(3000); // Pause before dismissing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            dialog.dismiss();
        });

        page.locator("#confirmButton").click();
    }
    @Test(dependsOnMethods = "openUrl")
    public void promptModal() {
        page.onceDialog(dialog -> {
            System.out.println("Prompt Message: " + dialog.message());
            System.out.println("Message: " + dialog.message());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            dialog.accept("Hello World");
            System.out.println("Prompt accepted successfully");
        });
        page.locator("#promtButton").click();
    }
    @Test(dependsOnMethods = "openUrl")
    public void alert(String msg) throws InterruptedException {
        String value = "accept";
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            if(dialog.type().equals("alert")) {
                dialog.accept();
                System.out.println("You clicked accept");
            }else if(dialog.type().equals("confirm")) {
                dialog.accept();
            } else if(dialog.type().equals("prompt")) {
                dialog.accept(msg);
            }
        });
    }


}
