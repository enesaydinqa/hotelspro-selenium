package context.base;

import context.driver.DriverManager;
import context.driver.DriverWebTestFactory;
import context.helper.JSHelper;
import context.manager.TestUserAccountManager;
import context.security.proxy.ZapSecurityTest;
import context.utils.Folder;
import context.utils.layout.LayoutDesign;
import context.utils.recorder.VideoRecorder;
import context.utils.report.ReportGenerate;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractWebTest extends AbstractSeleniumTest
{
    private static final Logger logger = Logger.getLogger(AbstractWebTest.class);

    private boolean takeVideo;
    protected JSHelper jsHelper;
    protected LayoutDesign layoutDesign;
    protected static ZapSecurityTest zapTest;
    protected static TestUserAccountManager testUserAccountManager;

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public ReportGenerate reportGenerate = new ReportGenerate();

    @Rule
    public final TestRule watchman = new TestWatcher()
    {

        @Override
        protected void starting(Description description)
        {
            logger.info("=================================================================");
            logger.info(String.format("TEST STARTED ... -> {%s}", description.getMethodName()));
            logger.info("=================================================================");
        }

        @Override
        protected void succeeded(Description description)
        {
            logger.info("=================================================================");
            logger.info("TEST PASSED ...");
            logger.info("=================================================================");
        }

        @Override
        protected void failed(Throwable e, org.junit.runner.Description description)
        {
            Folder folder = new Folder();

            folder.createFolder(System.getProperty("user.dir").concat("/target/PageSource"));

            String file = System.getProperty("user.dir") + String.format("/target/PageSource/%s-DOM.txt", description.getMethodName());

            PrintWriter writer = null;

            try
            {
                writer = new PrintWriter(file, "UTF-8");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            writer.println(driver.getPageSource());
            writer.close();
            logger.info("TEST FAIL ... Fail Screen Page Source --> " + file);
        }

        @Override
        protected void finished(org.junit.runner.Description description)
        {

            if (driver != null)
            {
                driver.close();
                driver.quit();
                driver = null;
            }
        }
    };

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

        driver.manage().window().setSize(new Dimension(1200, 800));

        VideoRecorder.startRecording(testName.getMethodName(), configuration.getTakeAVideo());

        testUserAccountManager = new TestUserAccountManager();

        jsHelper = new JSHelper(driver);

        layoutDesign = new LayoutDesign(driver, configuration);

        zapTest = new ZapSecurityTest(
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
    }

    // --------

    private void setHarFile(String harFileName)
    {

        String sFileName = configuration.getHarFilePath().concat(harFileName.concat(".har"));

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
                logger.info("Could not find file " + sFileName);
                ex.printStackTrace();
            }
        }
        catch (Exception ex)
        {
        }
    }

}
