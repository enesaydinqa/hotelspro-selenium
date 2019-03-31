package com.selenium.context.driver;


import org.apache.log4j.Logger;

public class DriverWebTestFactory
{
    private static final Logger logger = Logger.getLogger(DriverWebTestFactory.class);

    public static DriverManager getManager()
    {
        DriverManager driverManager;

        String browserType = System.getProperties().getProperty("browser.type");

        logger.info("BrowserType (DriverWebTestFactory) : " + browserType);

        switch (browserType)
        {

            case "chrome":
                driverManager = new ChromeDriverManagerWeb();
                break;

            default:
                driverManager = new ChromeDriverManagerWeb();
                break;
        }

        return driverManager;
    }
}
