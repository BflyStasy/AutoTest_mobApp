package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        BUTTON_TEXT_TPL = "xpath://*[@text='{BUTTON_NAME}']";
        MY_LISTS_LINK = "xpath://*[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
