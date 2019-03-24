package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsResponsiveTest;
import context.flag.NetworkExecutable;
import context.flag.ParallelExecutable;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainResponsivePage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class HomePageNetworkTest extends AbstractNYXCosmeticsResponsiveTest
{
    private static final Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    private MainResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainResponsivePage(driver);
    }

    @Test
    @Description("Adaptive - Anasayfa yüklenirken yapılan png/jpg request lerin 200 (ok) olduğunun kontrolü")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testAdaptiveHomePageLoadPNG()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response this url -> " + png.getRequest().getUrl());
                    Assert.assertEquals("HTTP Request Error :" + png.getRequest().getUrl(), 200, png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Adaptive - Anasayfa yüklenirken yapılan request lerin response larının 400 den küçük olduğunun kontrolü")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testAdaptiveHomePageNetwork()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.forEach(png -> {
            logger.info("Check Response This Url -> " + png.getRequest().getUrl());
            Assert.assertTrue("HTTP Request Error : " + png.getRequest().getUrl(), 400 > png.getResponse().getStatus());
        });
    }
}
