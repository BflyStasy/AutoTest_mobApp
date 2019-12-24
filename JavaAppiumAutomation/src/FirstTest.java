import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class FirstTest {
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

        //driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest() //Lesson 2
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "cannot find button SKIP",
                7);

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "cannot find search",
                7);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "cannot find search input",
                10);

        waitForElementPresent(
                //"By.xPath(//*[@resource-id, 'org.wikipedia:org.wikipedia:id/search_results_list']//*[@text,'Object-oriented programming language']"),
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);
    }

     @Test
     public void testCancelSearch_simple() //Lesson 2
     {
         waitForElementAndClick(
                 By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                 "cannot find button SKIP",
                 7);

         waitForElementAndClick(
                 By.id("org.wikipedia:id/search_container"),
                 "cannot find 'Search Wikipedia'",
                 7);
         waitForElementAndSendKeys(
                 By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                 "Jaba",
                 "cannot find search input",
                 10);
         waitForElementAndClear(
                 By.id("org.wikipedia:id/search_src_text"),
                 "cannot find search field",
                 10);

         waitForElementAndSendKeys(
                 By.id("org.wikipedia:id/search_src_text"),
                 "Java",
                 "cannot find search input",
                 15);
         waitForElementAndClick(
                 By.id("org.wikipedia:id/search_close_btn"),
                 "cannot find X to cancel search",
                 7);
         waitForElementByNotPresent(
                 By.id("org.wikipedia:id/search_close_btn"),
                 "X is steal present on the page",
                 7);
     }

     @Test
     public void testCompareArticleTitle() //Lesson 2
     {
         waitForElementAndClick(
                 By.xpath("//*[contains(@text,'SKIP')]"),
                 "cannot find button SKIP",
                 7);

         waitForElementAndClick(
                 By.id("org.wikipedia:id/search_container"),
                 "cannot find 'Search Wikipedia'",
                 7);
         waitForElementAndSendKeys(
                 By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                 "Java",
                 "cannot find search input",
                 10);
         waitForElementAndClick(
                 By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                 "cannot find 'Object-oriented programming language' topic searching by 'Java'",
                 10);
         waitForElementPresent(
                 By.xpath("//*[contains(@text,'Java (programming language)')]"),
                 "cannot find article title",
                 15);
         WebElement title_element = waitForElementPresent(
                 By.id("pagelib_edit_section_title_description"),
                 "cannot find article title",
                 15);

         String article_title = title_element.getAttribute("text");
         Assert.assertEquals(
                 "We see unexpected title",
                 "Object-oriented programming language",
                 article_title);
     }
    /*--------------------------------------------------------------------*/

    @Test
    public void testFindSearch() //Lesson 2, Ex2
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

    @Test
    public void testCancelSearch() //Lesson 2, Ex3
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "cannot find button SKIP",
                7);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "cannot find search input",
                15);
        moreThenOneElement(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find any article",
                "Not enough article",
                7);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "cannot find X to cancel search",
                7);
        waitForElementByNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Search result still are displayed",
                7);
    }

    @Test
    public void testFindWordInSearchResult() //Lesson 2, Ex4
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "cannot find button SKIP",
                7);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "cannot find search input",
                15);

        findWorldInTitle(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Don't have any result of search",
                "Сannot find the text ",
                "Java",
                5);
    }
    /*--------------------------------------------------------------------*/
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
    /*--------------------------------------------------------------------*/
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
    private void findWorldInTitle (By by, String error_message1, String error_message2, String text, long timeoutInSeconds)
    {
        List<WebElement> elements = waitForElementsPresent(
                by,
                error_message1,
                timeoutInSeconds);
        int count = 0;
        do{
            Assert.assertTrue(
                    error_message2 + ": '"+text + "'. (line number - " + (count+1) + ")",
                    elements.get(count).getAttribute("text").contains(text)
            );
            count++;
        }
        while (count < elements.size());
    }
}

