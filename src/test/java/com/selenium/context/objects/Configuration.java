package com.selenium.context.objects;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{

    private static final Logger logger = Logger.getLogger(Configuration.class);

    private Properties configProps = new Properties();

    private String browserType;
    private Boolean takeAVideo;
    private String galenReportPath;
    private String harFilePath;
    private String baseUrl;
    private Integer pageLoadTimeout;
    private Integer waitLoadTimeout;
    private Integer implicitlyWait;
    private String macChromeDriver;
    private String windowsChromeDriver;
    private String testUserId;
    private String testEmail;
    private String testEmailPassword;
    private String testResultPath;

    public Configuration() throws IOException
    {
        loadConfigProperties();

        this.browserType = System.getProperties().getProperty("browser.type");
        this.takeAVideo = Boolean.valueOf(System.getProperties().getProperty("take.a.video"));
        this.galenReportPath = configProps.getProperty("galen.report.path");
        this.harFilePath = configProps.getProperty("har.file.path");
        this.baseUrl = configProps.getProperty("base.url");
        this.pageLoadTimeout = Integer.valueOf(configProps.getProperty("page.load.timeout"));
        this.waitLoadTimeout = Integer.valueOf(configProps.getProperty("wait.timeout.seconds"));
        this.implicitlyWait = Integer.valueOf(configProps.getProperty("implicitly.wait"));
        this.macChromeDriver = configProps.getProperty("mac.chrome.driver");
        this.windowsChromeDriver = configProps.getProperty("windows.chrome.driver");
        this.testEmail = configProps.getProperty("test.mail.address");
        this.testUserId = configProps.getProperty("test.user.id");
        this.testEmailPassword = configProps.getProperty("test.mail.address.password");
        this.testResultPath = configProps.getProperty("test.result.report");

    }

    private void loadConfigProperties() throws IOException
    {
        String configFile = "config.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(configFile);

        configProps.load(in);
    }

    public String getBrowserType()
    {
        return browserType;
    }

    public void setBrowserType(String browserType)
    {
        this.browserType = browserType;
    }

    public Boolean getTakeAVideo()
    {
        return takeAVideo;
    }

    public void setTakeAVideo(Boolean takeAVideo)
    {
        this.takeAVideo = takeAVideo;
    }

    public String getGalenReportPath()
    {
        return galenReportPath;
    }

    public void setGalenReportPath(String galenReportPath)
    {
        this.galenReportPath = galenReportPath;
    }

    public String getHarFilePath()
    {
        return harFilePath;
    }

    public void setHarFilePath(String harFilePath)
    {
        this.harFilePath = harFilePath;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public Integer getPageLoadTimeout()
    {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(Integer pageLoadTimeout)
    {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public Integer getWaitLoadTimeout()
    {
        return waitLoadTimeout;
    }

    public void setWaitLoadTimeout(Integer waitLoadTimeout)
    {
        this.waitLoadTimeout = waitLoadTimeout;
    }

    public Integer getImplicitlyWait()
    {
        return implicitlyWait;
    }

    public void setImplicitlyWait(Integer implicitlyWait)
    {
        this.implicitlyWait = implicitlyWait;
    }

    public String getMacChromeDriver()
    {
        return macChromeDriver;
    }

    public void setMacChromeDriver(String macChromeDriver)
    {
        this.macChromeDriver = macChromeDriver;
    }

    public String getWindowsChromeDriver()
    {
        return windowsChromeDriver;
    }

    public void setWindowsChromeDriver(String windowsChromeDriver)
    {
        this.windowsChromeDriver = windowsChromeDriver;
    }

    public String getTestUserId()
    {
        return testUserId;
    }

    public void setTestUserId(String testUserId)
    {
        this.testUserId = testUserId;
    }

    public String getTestEmail()
    {
        return testEmail;
    }

    public void setTestEmail(String testEmail)
    {
        this.testEmail = testEmail;
    }

    public String getTestEmailPassword()
    {
        return testEmailPassword;
    }

    public void setTestEmailPassword(String testEmailPassword)
    {
        this.testEmailPassword = testEmailPassword;
    }

    public String getTestResultPath()
    {
        return testResultPath;
    }

    public void setTestResultPath(String testResultPath)
    {
        this.testResultPath = testResultPath;
    }
}
