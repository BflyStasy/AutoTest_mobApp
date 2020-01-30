package src.tests;

import lib.CoreTestCase;
import lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.NavigationUI;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factrories.ArticlePageObjectFactory;
import src.lib.ui.factrories.NavigationUIFactory;
import src.lib.ui.factrories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResults() //Lesson 3
    {
        if(Platform.getInstance().isMw()){
            return;
        }
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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
        if(Platform.getInstance().isMw()){
            return;
        }
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
        }else{
            button_skip = "Skip";
        }
        NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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
