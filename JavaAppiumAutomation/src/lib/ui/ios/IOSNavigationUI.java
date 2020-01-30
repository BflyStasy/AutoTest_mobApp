package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        BUTTON_TEXT_TPL = "id:{BUTTON_NAME}";
        MY_LISTS_LINK = "id:Saved";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
