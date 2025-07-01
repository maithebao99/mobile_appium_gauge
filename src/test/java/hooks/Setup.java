package hooks;

import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.ExecutionContext;
import utils.ScreenshotUtils;
import utils.ApkInstaller;
import utils.Constant;

public class Setup {

    @BeforeSuite
    public static void setupSuite() {
        ApkInstaller.installApk(Constant.APK_PATH);
        ApkInstaller.launchApp();
    }


    @AfterStep
    public void afterEachStepFail(ExecutionContext context) {
        if (context.getCurrentStep().getIsFailing()) {
            String fileName = "fail_step_" + System.currentTimeMillis();
            ScreenshotUtils.capture(fileName);
        }
    }

}
