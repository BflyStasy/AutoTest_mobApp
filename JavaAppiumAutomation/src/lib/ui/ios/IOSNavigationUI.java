package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {
        BUTTON_TEXT_TPL = "id:{BUTTON_NAME}";
        MY_LISTS_LINK = "id:Saved";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
