package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@class='android.view.ViewGroup']/*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']"; SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_INPUT_ID = "xpath:org.wikipedia:id/search_src_text";
        SEARCH_LIST_RESULT = "xpath:/XCUIElementTypeCollectionView";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
