package utils;

import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
public class ApkInstaller {

    private static AndroidDriver driver = DriverFactory.getAndroidDriver();

    public static void installApk(String path) {
        Path apkPath = Paths.get(path);
        if (!apkPath.isAbsolute()) {
            apkPath = Paths.get(System.getProperty("user.dir")).resolve(path);
        }

        File apkFile = apkPath.toFile();

        if (!apkFile.exists()) {
            throw new RuntimeException("APK file not found at: " + apkFile.getAbsolutePath());
        }

        AndroidDriver driver = DriverFactory.getAndroidDriver();
        driver.installApp(apkFile.getAbsolutePath());
    }

    public static void launchApp() {
        driver.activateApp(Constant.APP_PACKAGE);
        log.info("Application launched successfully !!!");
    }

    public static void closeApp() {
        driver.terminateApp(Constant.APP_PACKAGE);
        log.info("Application closed successfully !!!");
    }

    public static void uninstallApp() {
        driver.removeApp(Constant.APP_PACKAGE);
    }

    public static void relaunchApp() {
        closeApp();
        launchApp();
    }
}
