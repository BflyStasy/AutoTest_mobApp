package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/reading_list_list']/*[@class='android.view.ViewGroup']/*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@class='android.view.ViewGroup']/*[@text='{TITLE}']";
        ARTICLE_TEXT_TPL = "xpath://*[contains(@text,'{TITLE}')]";
    }
    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
