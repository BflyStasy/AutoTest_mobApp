package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject
{
    private static final String
            BUTTON_TEXT_TPL = "xpath://*[@text='{BUTTON_NAME}']",
            MY_LISTS_LINK = "xpath://*[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver) {
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
        String button_xpath = getTitleButton(name_of_button);
        this.waitForElementAndClick(button_xpath, "Cannot find button: " + name_of_button, 10);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find button 'My lists'",15);
    }
}
