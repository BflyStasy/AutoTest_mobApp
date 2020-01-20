package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResults() //Lesson 3
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitleUseXpath("Object-oriented programming language");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitleUseXpath("Object-oriented programming language");
        assertEquals(
                "Article title has been changed after screen rotation\ntitle befor: " + title_before_rotation + "\ntitle after: " + title_after_rotation,
                title_before_rotation,
                title_after_rotation );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitleUseXpath("Object-oriented programming language");
        assertEquals(
                "Article title has been changed after second screen rotation\ntitle befor:" + title_before_rotation + "\ntitle after second: " + title_after_second_rotation,
                title_before_rotation,
                title_after_second_rotation );

    }
    @Test
    public void testCheckArticleInBackground() //Lesson 3
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitleUseXpath("Object-oriented programming language");
        assertEquals(
                "We see unexpected title",
                "pagelib_edit_section_title_description",
                article_title);
        this.backgroundApp(2);
        article_title = ArticlePageObject.getArticleTitleUseXpath("Object-oriented programming language");
        assertEquals(
                "We see unexpected title after return background",
                "pagelib_edit_section_title_description",
                article_title);

    }
}
