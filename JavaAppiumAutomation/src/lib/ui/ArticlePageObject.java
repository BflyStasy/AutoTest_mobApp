package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE_ID,
            TITLE_TEXT_TPL,
            FOOTER_ELEMENT,
            MENU_BOOKMARK,
            ADD_TO_MY_LIST_OVERLAY,
            BUTTON_NEW,
            MY_LIST_NEW_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE,
            LOCATOR_PAGE,
            BUTTON_CLOSE;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getTitleElement(String substring)
    {
        return TITLE_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    /*TEMPLATES METHODS*/
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE_ID,"Cannot find article title description on page", 20);
    }
    public WebElement waitForTitleUseXpath(String substring)
    {
        String search_result_xpath = getTitleElement(substring);
        return this.waitForElementPresent(search_result_xpath,"Cannot find article title on page", 10);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
        return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }
    }
    public String getArticleTitleUseXpath(String text_title)
    {
        WebElement title_element = waitForTitleUseXpath(text_title);
        return title_element.getAttribute("resource-id");
    }
    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article.", 60);
        } else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article.", 60);
        }
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Add this article to a reading list'", 15);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find button 'Got it'", 7);
        this.waitForElementAndClick(BUTTON_NEW, "Cannot find button 'Create new'", 15);

        this.waitForElementAndSendKeys(MY_LIST_NEW_INPUT, name_of_folder, "Cannot find input field", 7);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot find button 'OK'", 10);
    }
    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Save for later'",15);
        //this.waitForElementAndClick(BUTTON_CLOSE, "Cannot find button 'places auth close'",10);
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE,"Cannot find close button", 10);
    }
    public void addArticleToMyExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Add this article to a reading list'", 15);
        String title_folder = getTitleElement(name_of_folder);
        this.waitForElementAndClick(title_folder, "Cannot find list: " + name_of_folder, 15);

    }
    public void findArticleAttributeByXpath(String attribute)
    {
        if(Platform.getInstance().isAndroid()) {
            this.assertElementPresent(LOCATOR_PAGE, "Cannot open article", "Cannot find title article", attribute);
        } else {
            this.assertElementPresent(TITLE_ID, "Cannot open article", "Cannot find title article", attribute);

        }
    }
}
