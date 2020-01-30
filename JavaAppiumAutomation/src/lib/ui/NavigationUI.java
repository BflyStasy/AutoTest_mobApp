package src.lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            BUTTON_TEXT_TPL,
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getTitleButton(String name_of_button)
    {
        return BUTTON_TEXT_TPL.replace("{BUTTON_NAME}", name_of_button);
    }
    /*TEMPLATES METHODS*/

    public void clickButtonUseText(String name_of_button)
    {
        String button = getTitleButton(name_of_button);
        this.waitForElementAndClick(button, "Cannot find button: " + name_of_button, 10);
    }

    public void clickMyLists()
    {
        if(Platform.getInstance().isMw()){
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find button 'My lists'",
                    5);
        }else {
            this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find button 'My lists'", 15);
        }
    }
    public void openNavigation()
    {
        if(Platform.getInstance().isMw()){
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }
}
