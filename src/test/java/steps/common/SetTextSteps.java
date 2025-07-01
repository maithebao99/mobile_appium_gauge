package steps.common;

import com.thoughtworks.gauge.Step;
import utils.action.SetTextUtils;

public class SetTextSteps {

    @Step("input value <text> on the element with <ID/XPATH/NAME/ACCESSIBILITY_ID/CLASSNAME> whose value is equal to <corresponding locator>")
    public void setTextWithTextBox(String text, String type, String locator)
    {
        SetTextUtils.setTextByLocator(text, type, locator);
    }
}
