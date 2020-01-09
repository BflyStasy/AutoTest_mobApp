package tests;

import lib.CoreTestCase;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() //Lesson 2
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch_simple() //Lesson 2
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
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
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
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
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "tdgckzbdhkf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
