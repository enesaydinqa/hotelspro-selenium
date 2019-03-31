package com.selenium.context.driver;


import org.apache.log4j.Logger;

public class DriverResponsiveTestFactory
{
    private static final Logger logger = Logger.getLogger(DriverResponsiveTestFactory.class);

    public static DriverManager getManager()
    {
        DriverManager driverManager;

        String browserType = System.getProperties().getProperty("browser.type");

        logger.info("BrowserType (DriverResponsiveTestFactory) : " + browserType);

        switch (browserType)
        {

            case "chrome":
                driverManager = new ChromeDriverManagerResponsive();
                break;

            default:
                driverManager = new ChromeDriverManagerResponsive();
                break;
        }

        return driverManager;
    }
}
