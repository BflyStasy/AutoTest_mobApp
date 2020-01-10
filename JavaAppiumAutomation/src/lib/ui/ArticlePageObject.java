package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE_ID = "pagelib_edit_section_title_description",
            TITLE_TEXT_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            FOOTER_ELEMENT = "//*[contains(@text,'View page in browser')]",
            MENU_BOOKMARK = "org.wikipedia:id/article_menu_bookmark",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            BUTTON_NEW = "//*[@text='Create new']",
            MY_LIST_NEW_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE ="//*[@content-desc='Navigate up']",
            LOCATOR_PAGE = "//*[@resource-id='content']";

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
        return this.waitForElementPresent(By.id(TITLE_ID),"Cannot find article title description on page", 15);
    }
    public WebElement waitForTitleUseXpath(String substring)
    {
        String search_result_xpath = getTitleElement(substring);
        return this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find article title on page", 10);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of the article.",100);
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "Cannot find button 'Add this article to a reading list'",
                15);
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button 'Got it'",
                7);
        this.waitForElementAndClick(
                By.xpath(BUTTON_NEW),
                "Cannot find button 'Create new'",
                15);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NEW_INPUT),
                name_of_folder,
                "Cannot find input field",
                7);
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find button 'OK'",
                10);
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE),"Cannot find close button", 10);
    }
    public void addArticleToMyExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "Cannot find button 'Add this article to a reading list'",
                15);
        String title_folder = getTitleElement(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(title_folder),
                "Cannot find list: " + name_of_folder,
                15);

    }
    public void findArticleAttributeByXpath(String attribute)
    {
        this.assertElementPresent(
                By.xpath(LOCATOR_PAGE),
                "Cannot open article",
                "Cannot find title article",
                attribute);
    }
}
