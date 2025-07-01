package utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Constant {
    public static final String DEVICE_NAME =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.deviceName]");

    public static final String PLATFORM_NAME =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.platformName]");

    public static final String APP_PACKAGE =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.appPackage]");

    public static final String APP_ACTIVITY =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.appActivity]");

    public static final String AUTOMATION_NAME =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.automationName]");

    public static final String APK_PATH =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.apkPath]");

    public static final String BASE_URL =
            TestDataUtils.resolveYaml(":KW_emulator-config[emulator.baseUrl]");
}
