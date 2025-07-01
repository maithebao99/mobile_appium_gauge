package utils;

import com.thoughtworks.gauge.Gauge;
import driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotUtils {

    public static void capture(String name) {
        try {
            File srcFile = ((TakesScreenshot) DriverFactory.getAndroidDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(".gauge/screenshots/" + name + ".png");
            FileUtils.copyFile(srcFile, destFile);

            com.thoughtworks.gauge.Gauge.writeMessage(
                    "<img src='../.gauge/screenshots/" + name + ".png' width='400' />"
            );

        } catch (Exception e) {
            System.err.println("Could not capture screenshot: " + e.getMessage());
        }
    }

}
