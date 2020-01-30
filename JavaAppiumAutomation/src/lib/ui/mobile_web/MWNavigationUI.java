package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.NavigationUI;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='menu.watchlist'";
        OPEN_NAVIGATION ="css:#mw-mf-main-menu-button";

    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}

