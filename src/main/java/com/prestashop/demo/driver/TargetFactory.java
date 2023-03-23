package com.prestashop.demo.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.prestashop.demo.config.ConfigurationManager.configuration;
import static java.lang.String.format;

public class TargetFactory {
    private static final Logger logger = LogManager.getLogger(TargetFactory.class);

    private TargetFactory() {
    }

    public static WebDriver createInstance(String browser) {
        Target target = Target.valueOf(configuration().target().toUpperCase());
        return switch (target) {
            case LOCAL -> BrowserFactory.valueOf(browser.toUpperCase()).createLocalDriver();
            case REMOTE -> createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
        };
    }

    private static RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());
            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            logger.error("Grid URL is invalid or Grid is not available");
            logger.error(format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            logger.error(format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }
        return remoteWebDriver;
    }

    public enum Target {
        LOCAL, REMOTE
    }
}
