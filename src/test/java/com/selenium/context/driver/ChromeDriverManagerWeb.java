package com.selenium.context.driver;

import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManagerWeb extends DriverManager
{
    private static final Logger logger = Logger.getLogger(ChromeDriverManagerWeb.class);

    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;

    @Override
    public void createDriver(Boolean withProxy)
    {

        chromeOptions = chromeOptions();

        desiredCapabilities = desiredCapabilities(withProxy, chromeOptions);

        if (Platform.getCurrent().is(Platform.MAC))
        {
            System.setProperty("webdriver.chrome.driver", configuration.getMacChromeDriver());
        }
        else if (Platform.getCurrent().is(Platform.WINDOWS))
        {
            System.setProperty("webdriver.chrome.driver", configuration.getWindowsChromeDriver());
        }

        driver = new ChromeDriver(desiredCapabilities);

        session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");
    }

    private DesiredCapabilities desiredCapabilities(Boolean withProxy, ChromeOptions chromeOptions)
    {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (withProxy)
        {
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

    private ChromeOptions chromeOptions()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("-lang= sl");
        chromeOptions.addArguments("--window-size=1400,800");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "tr");
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

}
