package utils.action;

import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utils.LocatorUtils;
import utils.TestDataUtils;

@Slf4j
@RequiredArgsConstructor
public class CommonUtils {

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

    public static String getTextByLocator(String type, String inputLocator) {
        try {
            By locator = LocatorUtils.getLocator(type, inputLocator);
            String text = driver.findElement(locator).getText();
            log.info("Successfully got text: {}", text);
            return text;

        } catch (NoSuchElementException e) {
            log.error("Element not found. Locator Type: {}, Input: {}", type.toUpperCase(), inputLocator);
            throw new RuntimeException("Element not found for input: " + inputLocator, e);

        } catch (IllegalArgumentException e) {
            log.error("Unsupported locator type provided: {}", type);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error occurred while getting text. Locator Type: {}, Input: {}",
                    type.toUpperCase(), inputLocator, e);
            throw new RuntimeException("Unexpected error during getText operation", e);
        }
    }

    public static void verifyTextByLocator(String type, String inputLocator, String comparisonType, String expectedText) {
        try {
            String actualText = getTextByLocator(type, inputLocator);
            expectedText = TestDataUtils.resolveYaml(expectedText);
            comparisonType = comparisonType.trim().toUpperCase();

            switch (comparisonType) {
                case "EQUAL":
                    if (!actualText.equals(expectedText)) {
                        log.error("Assertion failed! Expected text: '{}', but found: '{}'", expectedText, actualText);
                        throw new AssertionError("Text mismatch! Expected: '" + expectedText + "', but found: '" + actualText + "'");
                    }
                    log.info("Text matches expected value: '{}'", expectedText);
                    break;

                case "NOTEQUAL":
                    if (actualText.equals(expectedText)) {
                        log.error("Assertion failed! Text should NOT be equal to '{}', but it is.", expectedText);
                        throw new AssertionError("Text should NOT be equal to '" + expectedText + "', but it is.");
                    }
                    log.info("Text is correctly not equal to '{}'", expectedText);
                    break;

                default:
                    log.error("Invalid comparison type: '{}'. Use 'EQUAL' or 'NOTEQUAL'", comparisonType);
                    throw new IllegalArgumentException("Unsupported comparison type: " + comparisonType + ". Use 'EQUAL' or 'NOTEQUAL'.");
            }

        } catch (Exception e) {
            log.error("Error during text verification. Locator Type: {}, Input: {}", type.toUpperCase(), inputLocator, e);
            throw e;
        }
    }


}
