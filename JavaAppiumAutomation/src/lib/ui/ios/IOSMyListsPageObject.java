package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}";
        ARTICLE_TEXT_TPL = "id:{TITLE}";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
