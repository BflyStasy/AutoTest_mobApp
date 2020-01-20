package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{
    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@class='android.view.ViewGroup']/*[@text='{SUBSTRING}']",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
        SEARCH_INPUT_ID = "xpath:org.wikipedia:id/search_src_text",
        SEARCH_LIST_RESULT = "id:org.wikipedia:id/page_list_item_title";
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
           return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",7);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input element after clicking search init element");
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and type into search input",10);
    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring: " + substring, 10);
    }
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button");
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementByNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present",7);
    }
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",7);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find anf click search result with substring: " + substring, 15);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request ", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element",15);
    }
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }
    public void clearSearchInput()
    {
        this.waitForElementAndClear(SEARCH_INPUT_ID, "Cannot find search field", 10);
    }

    public String getTitleSearchField()
    {
        WebElement title_element = this.waitForElementPresent(SEARCH_INPUT_ID, "cannot find search input");
        return title_element.getAttribute("text");
    }
    public void waitForListResult()
    {
        this.moreThenOneElement(SEARCH_LIST_RESULT, "Cannot find any article", "Not enough article", 7);
    }
    public void waitForNotPresentListResult()
    {
        this.waitForElementByNotPresent(SEARCH_LIST_RESULT, "Search result still are displayed", 7);
    }
    public void findTextInTitleArticle(String search_line)
    {
        this.findWorldInTitle(SEARCH_LIST_RESULT, "Don't have any result of search", "Сannot find the text: "+ search_line, search_line, 5);
    }
}
