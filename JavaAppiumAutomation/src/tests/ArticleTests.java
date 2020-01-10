package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle() //Lesson 2
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title description",
                "Object-oriented programming language",
                article_title);
    }
    @Test
    public void testSwipeArticle()  //Lesson 3
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleUseXpath("Appium");
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testOpenArticleAndFindTitle() //Lesson 3 Ex.6
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        String article_title = "Java (programming language)";
        SearchPageObject.clickByArticleWithSubstring(article_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.findArticleAttributeByXpath("text");
    }

}
