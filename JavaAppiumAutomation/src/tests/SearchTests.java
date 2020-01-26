package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factrories.NavigationUIFactory;
import lib.ui.factrories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() //Lesson 2
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch_simple() //Lesson 2
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clearSearchInput();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch() //Lesson 3
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found to few results",
                amount_of_search_results > 0 );
    }
    @Test
    public void testAmountOfEmptySearch() //Lesson 3
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "tdgckzbdhkf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    public void testFindSearch() //Lesson 2, Ex.2
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String article_title = SearchPageObject.getTitleSearchField();
        assertEquals(
                "We see unexpected title",
                "Search Wikipedia",
                article_title);

    }
    @Test
    public void testCancelSearch() //Lesson 2, Ex.3
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForListResult();

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForNotPresentListResult();
    }
    @Test
    public void testFindWordInSearchResult() //Lesson 2, Ex.4
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.findTextInTitleArticle(search_line);

    }
}
