package src.lib.ui.factrories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.android.AndroidArticlePageObject;
import src.lib.ui.ios.IOSArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory
{
    public static ArticlePageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        }
        else if(Platform.getInstance().isIos()){
            return new IOSArticlePageObject(driver);
        }else {
            return new MWArticlePageObject(driver);
        }
    }
}
