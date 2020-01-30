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

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle() //Lesson 2
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }else if(Platform.getInstance().isIos()) {
            button_skip = "Skip";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String search_line_result;
        if(Platform.getInstance().isAndroid()){
            search_line_result = "Java (programming language)";
        }else if(Platform.getInstance().isIos()){
            search_line_result = "Java (programming language)\nObject-oriented programming language";
        }else{
            search_line_result ="Object-oriented programming language";
        }

        SearchPageObject.clickByArticleWithSubstring(search_line_result);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = ArticlePageObject.getArticleTitle();
        String title;
        if(Platform.getInstance().isAndroid()) {
            title = "Object-oriented programming language";
        }else {
            title = "Java (programming language)";
        }
            assertEquals(
                "We see unexpected title description",
                title,
                article_title);
    }
    @Test
    public void testSwipeArticle()  //Lesson 3
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }else if(Platform.getInstance().isIos()){
            button_skip = "Skip";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }


        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String search_line_result;
        if(Platform.getInstance().isAndroid()){
            search_line_result = "Java (programming language)";
        }else if(Platform.getInstance().isIos()){
            search_line_result = "Java (programming language)\nObject-oriented programming language";
        }else{
            search_line_result ="Object-oriented programming language";
        }

        SearchPageObject.clickByArticleWithSubstring(search_line_result);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testOpenArticleAndFindTitle() //Lesson 3 Ex.6
    {
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }else if(Platform.getInstance().isIos()){
            button_skip = "Skip";
            NavigationUI NavigationUI = (src.lib.ui.NavigationUI) NavigationUIFactory.get(driver);
            NavigationUI.clickButtonUseText(button_skip);
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        String article_title;
        if(Platform.getInstance().isAndroid()){
            article_title = "Java (programming language)";
        }else if(Platform.getInstance().isIos()){
            article_title = "Java (programming language)\nObject-oriented programming language";
        }else{
            article_title ="Object-oriented programming language";
        }
        SearchPageObject.clickByArticleWithSubstring(article_title);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.findArticleAttributeByXpath("text");
        } else if(Platform.getInstance().isIos()){
            ArticlePageObject.findArticleAttributeByXpath("name");
        }else {
            ArticlePageObject.getArticleTitle();
        }
    }

}
