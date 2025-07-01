package steps.app;

import com.thoughtworks.gauge.Step;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import utils.ApkInstaller;
import utils.Constant;

@Slf4j
public class AppSteps {

    @Step("uninstall app")
    public void uninstallApp() {
        ApkInstaller.uninstallApp();
    }

    @Step("relaunch app")
    public void relaunchApp() {
        ApkInstaller.relaunchApp();
    }

    @Step("close driver")
    public void teardownTheApp() {
        DriverFactory.quitDriver();
    }

    @Step("Close the app")
    public void closeAppIfRunning() {
        AndroidDriver driver = DriverFactory.getAndroidDriver();
        try {
            if (driver.isAppInstalled(Constant.APP_PACKAGE)) {
                String currentPackage = driver.getCurrentPackage();
                if (Constant.APP_PACKAGE.equals(currentPackage)) {
                    log.info("The app is currently running. Closing the app...");
                    log.info("Close the app successfully !!!");
                    driver.terminateApp(Constant.APP_PACKAGE);
                } else {
                    log.info("The app is installed but not currently running. No need to close.");
                }
            } else {
                log.info("The app is not installed: {}", Constant.APP_PACKAGE);
            }
        } catch (Exception e) {
            log.error("Failed to check or close the app: {}", e.getMessage(), e);
        }
    }
}
