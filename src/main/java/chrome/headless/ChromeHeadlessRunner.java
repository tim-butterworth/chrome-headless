package chrome.headless;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeHeadlessRunner {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ChromeHeadlessRunner.class.getClassLoader().getResource("chromedriver").getPath());
        WebDriver webDriver = new ChromeDriver(populateChromeOptions());

        try {
            visitGoogle(webDriver);
        } catch (Exception e) {
            e.printStackTrace();  //So many things could go wrong, lets print up our special surprise
        } finally {
            webDriver.quit();  //comment me out to leave chrome running, if you want to confirm things are running headlessly
        }
    }

    private static void visitGoogle(WebDriver webDriver) {
        webDriver.navigate().to("https://google.com");

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);

        webDriverWait.until((wd) -> webDriver.findElement(By.tagName("body")));
        WebElement body = webDriver.findElement(By.tagName("body"));

        System.out.println(body.getText());
    }

    private static ChromeOptions populateChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.setBinary("/Applications/Google Chrome 2.app/Contents/MacOS/Google Chrome");
        options.addArguments("--headless");     //For headlessness!
        options.addArguments("--disable-gpu");  //For justice! (There is a bug, this property is not intended to be required long term)

        return options;
    }
}
