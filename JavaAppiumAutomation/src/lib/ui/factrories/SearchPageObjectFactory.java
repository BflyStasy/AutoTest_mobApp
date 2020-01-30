package src.lib.ui.factrories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import src.lib.ui.SearchPageObject;
import src.lib.ui.android.AndroidSearchPageObject;
import src.lib.ui.ios.IOSSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.mobile_web.MWSearchPageObject;

public class SearchPageObjectFactory
{
    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        }
        else if(Platform.getInstance().isIos()){
            return new IOSSearchPageObject(driver);
        }else {
            return new MWSearchPageObject(driver);
        }
    }
}
