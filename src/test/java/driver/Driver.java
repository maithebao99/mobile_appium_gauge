package driver;

import com.thoughtworks.gauge.AfterSuite;

public class Driver {

    @AfterSuite
    public void tearDown() {
        System.out.println("Closing Appium driver...");
        DriverFactory.quitDriver();
    }

}
