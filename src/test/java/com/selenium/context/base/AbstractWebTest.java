package com.selenium.context.base;

import com.selenium.context.driver.DriverManager;
import com.selenium.context.driver.DriverWebTestFactory;
import com.selenium.context.helper.JSHelper;
import com.selenium.context.utils.layout.LayoutDesign;
import com.selenium.context.utils.recorder.VideoRecorder;
import com.selenium.context.utils.report.ReportGenerate;
import com.selenium.context.utils.report.StatusRule;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public abstract class AbstractWebTest extends AbstractSeleniumTest
{
    private static final Logger logger = Logger.getLogger(AbstractWebTest.class);

    protected JSHelper browserJS;
    protected LayoutDesign layoutDesign;

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public ReportGenerate reportGenerate = new ReportGenerate();

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
        driverManager = DriverWebTestFactory.getManager();

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

        browserJS = new JSHelper(driver);

        layoutDesign = new LayoutDesign(driver, configuration);

    }


    @After
    public void tearDown() throws Exception
    {
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
            driver.quit();
            driver = null;
        }
    }


}
