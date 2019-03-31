package com.selenium.context.driver;

import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManagerResponsive extends DriverManager {

    private static final Logger logger = Logger.getLogger(ChromeDriverManagerResponsive.class);

    private static Map<String, String> mobileEmulation;
    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;

    private static final String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";

    @Override
    public void createDriver(Boolean withProxy) {

        mobileEmulation = mobileEmulation();
        chromeOptions = chromeOptions(mobileEmulation);
        desiredCapabilities = desiredCapabilities(withProxy, chromeOptions);

        if (Platform.getCurrent().is(Platform.MAC)) {
            System.setProperty("webdriver.chrome.driver", configuration.getMacChromeDriver());
        } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
            System.setProperty("webdriver.chrome.driver", configuration.getWindowsChromeDriver());
        }

        driver = new ChromeDriver(desiredCapabilities);
        driver.manage().window().setSize(new Dimension(414, 736));

        session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");

    }

    private DesiredCapabilities desiredCapabilities(Boolean withProxy, ChromeOptions chromeOptions) {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (withProxy) {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

            String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
            String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1);

            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            logger.info("=================================================================");
            logger.info("This Execute Browser Host --> " + host);
            logger.info("This Execute Browser Port --> " + port);
            logger.info("=================================================================");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private ChromeOptions chromeOptions(Map<String, String> mobileEmulation) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--user-agent=" + USER_AGENT);
        chromeOptions.setExperimentalOption("mobileEmulation", null);

        return chromeOptions;
    }

    private Map<String, String> mobileEmulation() {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("browserName", "iPhone");
        mobileEmulation.put("device", "iPhone 8 Plus");
        mobileEmulation.put("realMobile", "true");
        mobileEmulation.put("version", "70.0");

        return mobileEmulation;
    }


}
