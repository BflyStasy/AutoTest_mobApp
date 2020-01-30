package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        BUTTON_TEXT_TPL = "xpath://*[@text='{BUTTON_NAME}']";
        MY_LISTS_LINK = "xpath://*[@content-desc='My lists']";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
