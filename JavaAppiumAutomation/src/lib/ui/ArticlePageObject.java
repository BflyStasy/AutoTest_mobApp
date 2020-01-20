package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE_ID = "id:pagelib_edit_section_title_description",
            TITLE_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']",
            FOOTER_ELEMENT = "xpath://*[contains(@text,'View page in browser')]",
            MENU_BOOKMARK = "id:org.wikipedia:id/article_menu_bookmark",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            BUTTON_NEW = "xpath://*[@text='Create new']",
            MY_LIST_NEW_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE ="xpath://*[@content-desc='Navigate up']",
            LOCATOR_PAGE = "xpath://*[@resource-id='content']";

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
        return this.waitForElementPresent(TITLE_ID,"Cannot find article title description on page", 15);
    }
    public WebElement waitForTitleUseXpath(String substring)
    {
        String search_result_xpath = getTitleElement(substring);
        return this.waitForElementPresent(search_result_xpath,"Cannot find article title on page", 10);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public String getArticleTitleUseXpath(String text_title)
    {
        WebElement title_element = waitForTitleUseXpath(text_title);
        return title_element.getAttribute("resource-id");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of the article.",40);
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(MENU_BOOKMARK, "Cannot find button 'Add this article to a reading list'", 15);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find button 'Got it'", 7);
        this.waitForElementAndClick(BUTTON_NEW, "Cannot find button 'Create new'", 15);

        this.waitForElementAndSendKeys(MY_LIST_NEW_INPUT, name_of_folder, "Cannot find input field", 7);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot find button 'OK'", 10);
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
        this.assertElementPresent(LOCATOR_PAGE, "Cannot open article", "Cannot find title article", attribute);
    }
}
