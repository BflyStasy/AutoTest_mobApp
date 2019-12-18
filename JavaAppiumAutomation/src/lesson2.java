import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class lesson2 {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/KulaginaA/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testFindSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "cannot find button SKIP",
                7);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);
        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "cannot find article title",
                5);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Search Wikipedia",
                article_title);

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return  waitForElementPresent(by, error_message, 10);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementByNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    private void moreThenOneElement(By by, String error_message1,String error_message2, long timeoutInSeconds)
    {
        List<WebElement> elements = waitForElementsPresent(by, error_message1, timeoutInSeconds);
        Assert.assertTrue(error_message2, elements.size()>1);
    }
}
