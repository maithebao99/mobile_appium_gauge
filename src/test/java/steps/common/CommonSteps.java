package steps.common;

import com.thoughtworks.gauge.Step;
import utils.TestDataUtils;
import utils.action.CommonUtils;

public class CommonSteps {

    @Step("input value <text> on the element with <ID/XPATH/NAME/ACCESSIBILITY_ID/CLASSNAME> whose value is equal to <corresponding locator>")
    public void setTextWithTextBox(String text, String type, String locator)
    {
        CommonUtils.setTextByLocator(text, type, locator);
    }

    @Step("get value on the element with <ID/XPATH/NAME/ACCESSIBILITY_ID/CLASSNAME> whose value is equal to <corresponding locator> and save to variable <variable>")
    public void getTextWithTextBox(String type, String locator, String key) {
        String value = CommonUtils.getTextByLocator(type, locator);
        TestDataUtils.setStoreData(key, value);
    }

    @Step("verify the text on the element with <ID/XPATH/NAME/ACCESSIBILITY_ID/CLASSNAME> whose value is equal to <locator> with condition <EQUAL/NOTEQUAL> and expected text <expectedText>")
    public void verifyTextWithCondition(String type, String locator, String comparisonType, String expectedText) {
        CommonUtils.verifyTextByLocator(type, locator, comparisonType, expectedText);
    }

    @Step("wait for <seconds> second(s)")
    public void waitForSeconds(String seconds)
    {
        CommonUtils.waitForSeconds(seconds);
    }

}
