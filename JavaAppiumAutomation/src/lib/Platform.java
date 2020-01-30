package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String PATH_TO_CHROMEDRIVER_EXE = "/Users/anastasiakulagina/JavaAppium/src/chromedriver";
    private static Platform instance;
    private Platform(){}
    public static Platform getInstance()
    {
        if(instance==null){
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()) {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if(this.isIos()) {
            return new IOSDriver(URL,getIosDesiredCapabilities());
        } else if(this.isMw()){
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER_EXE);
            return new ChromeDriver(getMwChromeOptions());
        }else{
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }
    public boolean isAndroid()
    {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIos()
    {
        return this.isPlatform(PLATFORM_IOS);
    }
    public boolean isMw()
    {
        return this.isPlatform(PLATFORM_MOBILE_WEB);
    }
    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AND80");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/anastasiakulagina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIosDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/Users/anastasiakulagina/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }
    private ChromeOptions getMwChromeOptions()
    {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width",360);
        deviceMetrics.put("height",640);
        deviceMetrics.put("pixelRatio",3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics",deviceMetrics);
        mobileEmulation.put("userAgent","Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) ");

        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("window-size=360,640");
        return chromeOption;

    }
    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }


}
