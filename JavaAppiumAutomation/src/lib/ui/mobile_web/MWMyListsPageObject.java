package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.MyListsPageObject;

    public class MWMyListsPageObject extends MyListsPageObject {
        static {
            ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";//'Java (programming language)'
            REMOVE_FROM_SAVE_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/parent::*/parent::*//a[contains(@class,'watched')]";
            ARTICLE_HREF="css:a[href='/wiki/'{TITLE}'']";
        }
        public MWMyListsPageObject(RemoteWebDriver driver) {
            super(driver);
        }
    }

