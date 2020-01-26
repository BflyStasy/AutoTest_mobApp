package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[@name='{TITLE}']";
        ARTICLE_TEXT_TPL = "xpath://*[contains(@text,'{TITLE}')]";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
