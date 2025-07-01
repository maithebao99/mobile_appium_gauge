package steps.common;

import com.thoughtworks.gauge.Step;
import utils.action.ClickUtils;

public class ClickSteps {

    @Step("click on the element with <ID/XPATH/NAME/ACCESSIBILITY_ID/CLASSNAME> whose value is equal to <locator>")
    public static void clickWithElement(String locator, String value)
    {
        ClickUtils.clickByLocator(locator, value);
    }

}
