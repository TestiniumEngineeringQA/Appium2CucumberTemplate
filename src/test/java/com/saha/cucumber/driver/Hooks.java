package com.saha.cucumber.driver;

import com.testinium.driver.TestiniumAndroidDriver;
import com.testinium.driver.TestiniumIOSDriver;
import com.testinium.util.Constants;
import com.testinium.util.TestiniumEnvironment;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

import static com.testinium.util.Constants.CapabilityConstants.APPIUM_APP_ACTIVITY;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_APP_PACKAGE;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_AUTOMATION_NAME;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_AUTO_ACCEPT_ALERTS;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_AUTO_GRANT_PERMISSIONS;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_BUNDLE_ID;
import static com.testinium.util.Constants.CapabilityConstants.APPIUM_NEW_COMMAND_TIMEOUT;
import static com.testinium.util.Constants.CapabilityConstants.UDID;

public class Hooks {

    private Logger logger = LoggerFactory.getLogger(getClass());
    public static AppiumDriver driver;
    public static FluentWait<AppiumDriver> appiumFluentWait;

    @Before
    public void setUp() {
        try {
            URL hubUrl = new URL("http://192.168.1.89:4723/");

            if (TestiniumEnvironment.isPlatformAndroid()) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(Constants.PLATFORM_NAME, Platform.ANDROID);
                caps.setCapability(UDID, "YOUR_UDID");
                caps.setCapability(APPIUM_AUTOMATION_NAME, "YOUR_AUTOMATION_NAME");
                caps.setCapability(APPIUM_APP_PACKAGE, "YOUR_APP_PACKAGE");
                caps.setCapability(APPIUM_APP_ACTIVITY, "YOUR_APP_ACTIVITY");
                caps.setCapability(APPIUM_AUTO_GRANT_PERMISSIONS, true);
                caps.setCapability(APPIUM_NEW_COMMAND_TIMEOUT, 60000);

                driver = new TestiniumAndroidDriver(hubUrl, caps);
            } else {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(Constants.PLATFORM_NAME, Platform.IOS);
                caps.setCapability(UDID, "YOUR_UDID");
                caps.setCapability(APPIUM_AUTOMATION_NAME, "YOUR_AUTOMATION_NAME");
                caps.setCapability(APPIUM_BUNDLE_ID, "YOUR_BUNDLE_ID");
                caps.setCapability(APPIUM_AUTO_ACCEPT_ALERTS, true);

                driver = new TestiniumIOSDriver(hubUrl, caps);
            }

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            appiumFluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(8))
                    .pollingEvery(Duration.ofMillis(350))
                    .ignoring(NoSuchElementException.class);

        } catch (Exception e) {
            logger.error("Driver setup error: ", e);
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
