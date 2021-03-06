package src.lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_TEXT_TPL,
            REMOVE_FROM_SAVE_BUTTON,
            ARTICLE_HREF;
    public MyListsPageObject(RemoteWebDriver driver) {
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
    private static String  getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVE_BUTTON.replace("{TITLE}", article_title);
    }
    private static String  getHrefByTitle(String article_title)
    {
        return ARTICLE_HREF.replace("{TITLE}", article_title);
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
        if(Platform.getInstance().isIos() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(article_xpath, "Cannot find saved article: " + article_title);
        }else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button for remove saved article",
                    10);
            driver.navigate().refresh();
        }
        if(Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article: " + article_title);
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void openArticleFromMyList(String article_title)
    {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.waitForElementAndClick(article_xpath, "Cannot find saved article: " + article_title, 15);
            String article = getArticleTitleXpath(article_title);
            this.waitForElementPresent(article, "Cannot find article title: " + article_title, 15);
    }
    public void isPresentSaveArticle(String title)
    {
        this.getHrefByTitle(title);
        this.waitForElementPresent(ARTICLE_HREF,"Cannot find saved article (use href)", 10);

    }

}
