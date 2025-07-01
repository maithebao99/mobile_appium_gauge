package utils;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class LocatorUtils {

    public static By getLocator(String type, String input) {
        String value = TestDataUtils.getStoreData(input);

        switch (type.toUpperCase()) {
            case "ID":
                return By.id(value);
            case "XPATH":
                return By.xpath(value);
            case "NAME":
                return By.name(value);
            case "ACCESSIBILITY_ID":
                return MobileBy.AccessibilityId(value);
            case "CLASSNAME":
                return By.className(value);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + type);
        }
    }
}
