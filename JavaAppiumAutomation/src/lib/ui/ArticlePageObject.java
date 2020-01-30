package src.lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            BUTTON_CLOSE;

    public ArticlePageObject(RemoteWebDriver driver) {
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
        }else if(Platform.getInstance().isIos()){
            return title_element.getAttribute("name");
        }else {
            return title_element.getText();
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
        } else if(Platform.getInstance().isIos()){
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article.", 60);
        } else {
            this.scrollWebPageTotleElementNotVisible(FOOTER_ELEMENT,"Cannot find the end of the article.",60);
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
    public void addArticleToMySaved() {
        //this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Save for later'",15);
        if(Platform.getInstance().isMw()){
            removeArticleFromSavedIfItAdd();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Save for later'",15);
    }
    public void removeArticleFromSavedIfItAdd(){
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "cannot find and click button to remove an article from saved",
                    5);
           this.waitForElementPresent(
                    MENU_BOOKMARK,
                    "Cannot find button to add an article to saved list after removing it from this list before",
                    5);
        }
    }
    public void closeArticle()
    {
        if(!Platform.getInstance().isMw()){
            this.waitForElementAndClick(CLOSE_ARTICLE,"Cannot find close button", 10);
        }else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }
    public void addArticleToMyExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Add this article to a reading list'", 15);
        String title_folder = getTitleElement(name_of_folder);
        this.waitForElementAndClick(title_folder, "Cannot find list: " + name_of_folder, 15);

    }
    public void findArticleAttributeByXpath(String attribute)
    {
        if(Platform.getInstance().isIos()) {
            this.assertElementPresent(TITLE_ID, "Cannot open article", "Cannot find title article", attribute);

        } else {
            this.assertElementPresent(LOCATOR_PAGE, "Cannot open article", "Cannot find title article", attribute);
        }
    }
}
