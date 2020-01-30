package src.lib.ui.factrories;

import lib.Platform;
import src.lib.ui.android.AndroidNavigationUI;
import src.lib.ui.ios.IOSNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.mobile_web.MWNavigationUI;

public class NavigationUIFactory {
    public static Object get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        }
        else if(Platform.getInstance().isIos()){
            return new IOSNavigationUI(driver);
        }else {
            return new MWNavigationUI(driver);
        }
    }
}
