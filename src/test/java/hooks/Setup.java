package hooks;

import com.thoughtworks.gauge.BeforeSuite;
import utils.ApkInstaller;
import utils.Constant;

public class Setup {

    @BeforeSuite
    public static void setupSuite() {
        ApkInstaller.installApk(Constant.APK_PATH);
        ApkInstaller.launchApp();
    }
}
