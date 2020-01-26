package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factrories.ArticlePageObjectFactory;
import lib.ui.factrories.MyListsPageObjectFactory;
import lib.ui.factrories.NavigationUIFactory;
import lib.ui.factrories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList() //Lesson 3
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
        String search_line_result;
        if(Platform.getInstance().isAndroid()){
            search_line_result = "Java (programming language)";
        }else{
            search_line_result = "Java (programming language)\nObject-oriented programming language";
        }
        SearchPageObject.clickByArticleWithSubstring(search_line_result);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        //String article_title = "Java (programming language)";//*/ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
            NavigationUI.clickButtonUseText("places auth close");
        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isAndroid()) {
            NavigationUI.clickButtonUseText("NO THANKS");
        }
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(search_line_result);
    }
    @Test
    public void testSaveTwoArticlesToListAndDeleteOneArticle() //Lesson 3 Ex.5
    {
        String search_line = "Java";
        String article_title1;  //первая статья для добавления в список
        String article_title2 ; //вторая статья для добавления в список
        String title1 = "Java (programming language)";
        String title2 = "JavaScript";
        String button_skip;
        if(Platform.getInstance().isAndroid()){
            button_skip = "SKIP";
            article_title1 = "Java (programming language)";
            article_title2 = "JavaScript";
        }else{
            article_title1 = "Java (programming language)\nObject-oriented programming language";
            article_title2 = "JavaScript\nProgramming language";
            button_skip = "Skip";
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickButtonUseText(button_skip);

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article_title1);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleUseXpath(title1);
            ArticlePageObject.addArticleToMySaved();
            NavigationUI.clickButtonUseText("places auth close");
        }
        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isAndroid()) {
            NavigationUI.clickButtonUseText("NO THANKS");
        }
        SearchPageObject.initSearchInput();

        if(Platform.getInstance().isAndroid()){
            SearchPageObject.typeSearchLine(search_line);
        }

        SearchPageObject.clickByArticleWithSubstring(article_title2);

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyExistingList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleUseXpath(title2);
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isAndroid()) {
            NavigationUI.clickButtonUseText("NO THANKS");
        }

        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title1);
        MyListPageObject.openArticleFromMyList(article_title2);
    }

}
