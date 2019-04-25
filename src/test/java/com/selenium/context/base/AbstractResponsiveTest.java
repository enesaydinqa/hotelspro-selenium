package com.selenium.context.base;

import com.selenium.context.driver.DriverManager;
import com.selenium.context.driver.DriverResponsiveTestFactory;
import com.selenium.context.helper.JSHelper;
import com.selenium.context.security.proxy.ZapSecurityTest;
import com.selenium.context.utils.layout.LayoutDesign;
import com.selenium.context.utils.recorder.VideoRecorder;
import com.selenium.context.utils.report.ReportGenerate;
import com.selenium.context.utils.report.StatusRule;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.File;
import java.io.IOException;

public abstract class AbstractResponsiveTest extends AbstractSeleniumTest
{
    private static final Logger logger = Logger.getLogger(AbstractResponsiveTest.class);

    protected JSHelper jsHelper;
    protected LayoutDesign layoutDesign;
    protected static ZapSecurityTest zapProxy;

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public ReportGenerate screenShootRule = new ReportGenerate();

    @Rule
    public StatusRule statusRule = new StatusRule();


    @Before
    public void init() throws Exception
    {
        init(false);
    }

    public void init(Boolean withProxy) throws Exception
    {
        DriverManager driverManager;
        driverManager = DriverResponsiveTestFactory.getManager();

        if (withProxy)
        {
            while (true)
            {
                try
                {
                    proxy = new BrowserMobProxyServer();
                    proxy.setMitmDisabled(false);
                    proxy.start();
                    proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
                    proxy.newHar();
                    break;
                }
                catch (Exception ex)
                {
                    logger.error("Already Use Proxy, retrying start proxy.");
                    continue;
                }
            }

        }

        driver = driverManager.getDriver(withProxy);

        VideoRecorder.startRecording(testName.getMethodName(), configuration.getTakeAVideo());

        jsHelper = new JSHelper(driver);

        layoutDesign = new LayoutDesign(driver, configuration);

        zapProxy = new ZapSecurityTest(
                configuration.getZapHost(),
                configuration.getZapPort(),
                configuration.getZapApiKey());
    }


    @After
    public void tearDown() throws Exception
    {
        //setHarFile(testName.getMethodName());

        VideoRecorder.stopRecording(configuration.getTakeAVideo());

        try
        {
            if (proxy != null) proxy.stop();
        }
        catch (IllegalStateException ex)
        {
            logger.info("Already Stopped Proxy");
        }

        if (driver != null)
        {
            driver.close();
            driver.quit();
            driver = null;
        }
    }


    // --------

    private void setHarFile(String harFileName)
    {

        String sFileName = configuration.getHarFilePath().concat(harFileName + ".har");

        try
        {
            Har har = proxy.getHar();
            File harFile = new File(sFileName);
            try
            {
                har.writeTo(harFile);
            }
            catch (IOException ex)
            {
                System.out.println(ex.toString());
                System.out.println("Could not find file " + sFileName);
            }
        }
        catch (Exception ex)
        {
        }
    }
}

