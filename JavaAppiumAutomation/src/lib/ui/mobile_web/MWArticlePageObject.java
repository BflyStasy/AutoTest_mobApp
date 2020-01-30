package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.ArticlePageObject;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "css:div[class='page-heading']>h1";
        TITLE_TEXT_TPL = "xpath://div[contains(@class,'page-heading')]>h1[contains(text(),'{SUBSTRING}')]";
        FOOTER_ELEMENT = "css:footer";
        MENU_BOOKMARK = "css:#page-actions-watch>#ca-watch";
        //BUTTON_CLOSE = "id:places auth close";
        //CLOSE_ARTICLE = "id:Back";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch[title='Remove this page from your watchlist']";
        LOCATOR_PAGE = "css:main[class='mw-body']";
    }
    public  MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
