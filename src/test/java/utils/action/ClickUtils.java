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
public class ClickUtils {

    private static final AndroidDriver driver = DriverFactory.getAndroidDriver();

    public static void clickByLocator(String type, String input) {
        try {
            By locator = LocatorUtils.getLocator(type, input);
            driver.findElement(locator).click();
            log.info("Successfully clicked element {}",input);

        } catch (NoSuchElementException e) {
            log.error("Element not found. Locator Type: {}, Input: {}", type.toUpperCase(), input);
            throw new RuntimeException("Element not found for input: " + input, e);

        } catch (IllegalArgumentException e) {
            log.error("Unsupported locator type provided: {}", type);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error occurred while clicking element. Locator Type: {}, Input: {}",
                    type.toUpperCase(), input, e);
            throw new RuntimeException("Unexpected error during click operation", e);
        }
    }
}
