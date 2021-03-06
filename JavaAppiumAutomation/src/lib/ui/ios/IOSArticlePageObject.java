package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE_ID = "id:Java (programming language)";
        TITLE_TEXT_TPL = "id:{SUBSTRING}";
        FOOTER_ELEMENT = "id:View article in browser";
        MENU_BOOKMARK = "id:Save for later";
        BUTTON_CLOSE = "id:places auth close";
        CLOSE_ARTICLE = "id:Back";
        LOCATOR_PAGE = "xpath://XCUIElementTypeCollectionView";
    }
    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super((AppiumDriver) driver);
    }
}
