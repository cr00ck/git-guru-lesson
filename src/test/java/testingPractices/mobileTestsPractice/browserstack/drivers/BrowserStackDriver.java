package testingPractices.mobileTestsPractice.browserstack.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        //Set access credentials
        caps.setCapability("browserstack.user", "");
        caps.setCapability("browserstack.key", "");

        //Set URL of the application under test
        caps.setCapability("app", "bs://");


        //Specify device and os_version for testing
        caps.setCapability("devive", "");
        caps.setCapability("os_version", "");

        //Set other BrowserStack capabilities
        caps.setCapability("project", "");
        caps.setCapability("build", "");
        caps.setCapability("name", "");



        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"),caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }
}
