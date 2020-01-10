package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList() //Lesson 3
    {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = "Java (programming language)";//*/ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI.clickButtonUseText("NO THANKS");
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveTwoArticlesToListAndDeleteOneArticle() //Lesson 3 Ex.5
    {
        String search_line = "Java";
        String article_title1 = "Java (programming language)"; //первая статья для добавления в список
        String article_title2 = "JavaScript"; //вторая статья для добавления в список
        String name_of_folder = "Learning programming";

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickButtonUseText("SKIP");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title1);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickButtonUseText("NO THANKS");
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title2);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI.clickButtonUseText("NO THANKS");
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title1);

        //MyListPageObject.waitForArticleToAppearByTitle(name_of_folder);
        MyListPageObject.openArticleFromMyList(article_title2);
    }

}
