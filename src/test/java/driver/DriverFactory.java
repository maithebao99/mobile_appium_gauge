package driver;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Constant;

import java.net.URL;
import java.nio.file.Paths;

@Slf4j
public class DriverFactory {

    private static AndroidDriver driver;

    public static AndroidDriver getAndroidDriver() {
        if (driver == null) {
            startAndroidDriver();
        }
        return driver;
    }

    private static void startAndroidDriver() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", Constant.DEVICE_NAME);
            capabilities.setCapability("platformName", Constant.PLATFORM_NAME);
            capabilities.setCapability("automationName", Constant.AUTOMATION_NAME);
            capabilities.setCapability("app", Paths.get(System.getProperty("user.dir"), Constant.APK_PATH).toString());
            capabilities.setCapability("appPackage", Constant.APP_PACKAGE);
            capabilities.setCapability("appActivity", Constant.APP_ACTIVITY);

            driver = new AndroidDriver(new URL(Constant.BASE_URL), capabilities);
            log.info("Android driver started successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to start AndroidDriver", e);
        }
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
