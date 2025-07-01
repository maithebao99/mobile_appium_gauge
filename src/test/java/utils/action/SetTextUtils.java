package utils.action;

import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utils.LocatorUtils;

@Slf4j
@RequiredArgsConstructor
public class SetTextUtils {

    private static final AndroidDriver driver = DriverFactory.getAndroidDriver();

    public static void setTextByLocator(String text, String type, String inputLocator) {
        try {
            By locator = LocatorUtils.getLocator(type, inputLocator);
            driver.findElement(locator).sendKeys(text);
            log.info("Successfully set text {}",text);

        } catch (NoSuchElementException e) {
            log.error("Element not found. Locator Type: {}, Input: {}", type.toUpperCase(), inputLocator);
            throw new RuntimeException("Element not found for input: " + inputLocator, e);

        } catch (IllegalArgumentException e) {
            log.error("Unsupported locator type provided: {}", type);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error occurred while setting text. Locator Type: {}, Input: {}",
                    type.toUpperCase(), inputLocator, e);
            throw new RuntimeException("Unexpected error during setText operation", e);
        }
    }
}
