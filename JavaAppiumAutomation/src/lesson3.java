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
public class lesson3 {
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
    public void saveTwoArticlesToListAndDeleteOneArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[@text='SKIP']"),
                "cannot find button SKIP",
                7);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                10);
        String article_title1 = "Java (programming language)"; //первая статья для добавления в список
        String article_title2 = "JavaScript"; //вторая статья для добавления в список
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + article_title1 +"')]"),
                "Cannot find '" + article_title1 +"' topic searching by '" + search_line +"'",
                15);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + article_title1 +"')]"),
                "Cannot find article title: " + article_title1,
                15);
        String article_menu = "org.wikipedia:id/article_menu_bookmark";
        waitForElementAndClick(
                By.id(article_menu),
                "Cannot find button 'Add this article to a reading list' (first article)",
                15);
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'Got it'",
                7);
        waitForElementAndClick(
                By.xpath("//*[@text='Create new']"),
                "Cannot find button 'Create new'",
                15);
        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find input field",
                7);
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find button 'OK'",
                10);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton'][@content-desc='Navigate up']"),
                "Cannot find navigation-button 'Back'",
                15);
        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find button 'No Thanks'",
                10);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                10);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                10);
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + article_title2 +"')]"),
                "Cannot find '" + article_title2 +"' topic searching by '" + search_line +"'",
                15);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + article_title2 + "')]"),
                "Cannot find article title: " + article_title2,
                15);
        waitForElementAndClick(
                By.id(article_menu),
                "Cannot find button 'Add this article to a reading list' (second article)",
                15);
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find list '" + name_of_folder +"'",
                15);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton'][@content-desc='Navigate up']"),
                "Cannot find navigation-button 'Back'",
                15);
        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot find button 'No Thanks'",
                10);
        waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Cannot find button 'My lists'",
                15);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']//*[@class='android.view.ViewGroup']//*[@text='" + name_of_folder + "']"),
                "Cannot find button list " + name_of_folder,
                20);
        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='" + name_of_folder + "']"),
                "Cannot open List '"+ name_of_folder + "'",
                15 );
        swipeElementToLeft(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='" + article_title1 + "']"),
                "Cannot find saved article '"+ article_title1 + "'");
        waitForElementByNotPresent(
                By.xpath("//*[contains(@text,'" + article_title1 + "')]"),
                "Cannot delete saved article '" + article_title1 +"'",
                10);
        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']//*[@text='" + name_of_folder + "']"),
                "Cannot open List '"+ name_of_folder + "'",
                15 );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + article_title2 +"']"),
                "Cannot find saved article '" + article_title2 +"'",
                15 );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + article_title2 + "')]"),
                "Cannot find article title: " + article_title2,
                15);
    }

    @Test
    public void openArticleAndFindTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[@text='SKIP']"),
                "cannot find button SKIP",
                7);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                10);
        String article_title = "Java (programming language)";
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + article_title +"')]"),
                "Cannot find '" + article_title +"' topic searching by '" + search_line +"'",
                15);
        String search_result_locator ="//*[@resource-id='content']/*[@class='android.view.View']";
        String attribute = "text";
        assertElementPresent(
                By.xpath(search_result_locator),
                "Cannot open article",
                "Cannot find title article",
                attribute);

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.sendKeys(value);
        return element;
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element  = waitForElementPresent(by, error_message, 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int top_y = element.getLocation().getY();
        int bottom_y = top_y + element.getSize().getHeight();
        int middle_y = (top_y + bottom_y)/2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    private boolean waitForElementByNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private void assertElementPresent(By by, String error_message1, String error_message2, String attribute)
    {
        WebElement element = waitForElementPresent(by, error_message1, 5 );
        Assert.assertTrue(error_message2, element.getAttribute(attribute) != null);
    }

}

