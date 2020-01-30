package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE_ID = "id:pagelib_edit_section_title_description";
        TITLE_TEXT_TPL = "xpath://*[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "xpath://*[contains(@text,'View page in browser')]";
        MENU_BOOKMARK = "id:org.wikipedia:id/article_menu_bookmark";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        BUTTON_NEW = "xpath://*[@text='Create new']";
        MY_LIST_NEW_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE = "xpath://*[@content-desc='Navigate up']";
        LOCATOR_PAGE = "xpath://*[@resource-id='content']";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super((AppiumDriver) driver);
    }
}
