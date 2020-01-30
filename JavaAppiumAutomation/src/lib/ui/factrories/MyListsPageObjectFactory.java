package src.lib.ui.factrories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.android.AndroidMyListsPageObject;
import src.lib.ui.ios.IOSMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.mobile_web.MWMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        }
        else if(Platform.getInstance().isIos()){
            return new IOSMyListsPageObject(driver);
        }else {
            return new MWMyListsPageObject(driver);
        }
    }
}
