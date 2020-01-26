package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_TEXT_TPL;
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getArticleTitleXpath(String article_title)
    {
        return ARTICLE_TEXT_TPL.replace("{TITLE}", article_title);

    }
    /*TEMPLATES METHODS*/

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find folder by name " + name_of_folder, 20);
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title: " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementByNotPresent(article_xpath, "Saved article still present with title: " + article_title, 10);
    }
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "Cannot find saved article: " + article_title);
        if(Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(article_title, "Cannot find saved article: " + article_title);
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void openArticleFromMyList(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath, "Cannot find saved article: " + article_title, 15 );
        String article = getArticleTitleXpath(article_title);
        this.waitForElementPresent(article, "Cannot find article title: " + article_title, 15);
    }
}
