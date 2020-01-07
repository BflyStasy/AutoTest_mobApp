import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
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
        driver.rotate(ScreenOrientation.PORTRAIT); //Lesson 3, Ex.7
        driver.quit();
    }
    /*--------------------------------------------------------------------*/
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
    public void testFindSearch() //Lesson 2, Ex.2
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
    public void testCancelSearch() //Lesson 2, Ex.3
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
    public void testFindWordInSearchResult() //Lesson 2, Ex.4
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
    @Test
    public void testSwipeArticle()  //Lesson 3
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
                "Appium",
                "cannot find search input",
                10);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                10);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Cannot find article title",
                15);
        swipeUpToFindElement(
                By.xpath("//*[contains(@text,'View page in browser')]"),
                "Cannot find the end of the article.",
                20
        );


    }
    @Test
    public void saveFirstArticleToMyList () //Lesson 3
    {
        waitForElementAndClick(
                By.xpath("//*[@text='SKIP']"),
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
                15);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "cannot find article title",
                15);
        waitForElementPresent(
                By.id("pagelib_edit_section_title_description"),
                "cannot find article title",
                15);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find button 'Add this article to a reading list'",
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
                7);
        waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Cannot find button 'My lists'",
                15);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_list']//*[@class='android.view.ViewGroup']//*[@text='" + name_of_folder + "']"),
                "Cannot find button list " + name_of_folder,
                20);
        swipeElementToLeft(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find saved article");
        waitForElementByNotPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot delete saved article",
                10);
    }
    @Test
    public void testAmountOfNotEmptySearch() //Lesson 3
    {
        waitForElementAndClick(
                By.xpath("//*[@text='SKIP']"),
                "cannot find button SKIP",
                7);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);
        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "cannot find search input",
                10);
        String search_result_locator ="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator));
        Assert.assertTrue(
                "We found to few results",
                amount_of_search_results > 0 );
    }
    @Test
    public void testAmountOfEmptySearch() //Lesson 3
    {
        waitForElementAndClick(
                By.xpath("//*[@text='SKIP']"),
                "cannot find button SKIP",
                7);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'Search Wikipedia'",
                7);
        String search_line = "tdgckzbdhkf";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                10);
        String search_result_locator ="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label be the request " + search_line,
                15 );
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }
    @Test
    public void testChangeScreenOrientationOnSearchResults() //Lesson 3
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
        String search_result_locator = "//*[contains(@text,'Object-oriented programming language')]";
        waitForElementAndClick(
                By.xpath(search_result_locator),
                "Cannot find '" + By.xpath(search_result_locator).toString() +"' topic searching by '" + search_line +"'",
                15);
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='content']//*[@class='android.view.View']"),
                "text",
                "Cannot find title of article",
                20 );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='content']//*[@class='android.view.View']"),
                "text",
                "Cannot find title of article",
                30 );
        Assert.assertEquals(
                "Article title has been changed after screen rotation\ntitle befor: " + title_before_rotation + "\ntitle after: " + title_after_rotation,
                title_before_rotation,
                title_after_rotation );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='content']//*[@class='android.view.View']"),
                "text",
                "Cannot find title of article",
                30 );
        Assert.assertEquals(
                "Article title has been changed after second screen rotation\ntitle befor:" + title_before_rotation + "\ntitle after second: " + title_after_second_rotation,
                title_before_rotation,
                title_after_second_rotation );

    }
    @Test
    public void testCheckArticleInBackground() //Lesson 3
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
        String search_result_locator = "//*[contains(@text,'Object-oriented programming language')]";
        waitForElementAndClick(
                By.xpath(search_result_locator),
                "Cannot find article '" + By.xpath(search_result_locator).toString() +"' searching by '" + search_line +"'",
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
        driver.runAppInBackground(5);
        title_element = waitForElementPresent(
                By.id("pagelib_edit_section_title_description"),
                "cannot find article title",
                15);
        article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title after return background",
                "Object-oriented programming language",
                article_title);

    }
    /*--------------------------------------------------------------------*/
    @Test
    public void saveTwoArticlesToListAndDeleteOneArticle() //Lesson 3 Ex.5
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
    public void openArticleAndFindTitle() //Lesson 3 Ex.6
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
    /*--------------------------------------------------------------------*/ //Lesson 1
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
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
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
    /*--------------------------------------------------------------------*/ //Lesson 2
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
    /*--------------------------------------------------------------------*/ //Lesson 3
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick()
    {
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while(driver.findElements(by).size()==0){
            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
        ++already_swiped;
        }
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
    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }
    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An empty '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    private void assertElementPresent(By by, String error_message1, String error_message2, String attribute)
    {
        WebElement element = waitForElementPresent(by, error_message1, 5 );
        Assert.assertTrue(error_message2, element.getAttribute(attribute) != null);
    }
}

