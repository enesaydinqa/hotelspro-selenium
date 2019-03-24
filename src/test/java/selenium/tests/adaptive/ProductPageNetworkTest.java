package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsResponsiveTest;
import context.flag.NetworkExecutable;
import context.flag.ParallelExecutable;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarResponse;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainResponsivePage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.List;

@NotThreadSafe
public class ProductPageNetworkTest extends AbstractNYXCosmeticsResponsiveTest
{
    private static final Logger logger = Logger.getLogger(ProductPageNetworkTest.class);

    private MainResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainResponsivePage(driver);
    }

    @Test
    @Description("Ürün detay sayfasında min 2 adet görsel var mı kontrolü.")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testProductDetailPageLoadImages()
    {
        navigateToURL(UrlFactory.THE_NEWEST_0_TO_50_PRICE);

        Long pageHeight = jsHelper.getPageHeight();
        secureScrollPage(0, pageHeight.intValue());
        pageScroll(0, 0);

        closeCampaingPopup();

        listElementRandomClick(mainPage.getProductList());

        closeCampaingPopup();

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        List<HarResponse> imageCount = new ArrayList<>();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Image : " + png.getRequest().getUrl());
                    imageCount.add(png.getResponse());
                });

        Assert.assertTrue("Product Page expected to be at least 2 photos", imageCount.size() >= 2);
    }

    @Test
    @Description("En yeniler sayfası yüklenirken yapılan requestlerin response larının 200 olduğunun kontrolü.")
    @Category({ParallelExecutable.class})
    public void testTheNewestLoadRequest()
    {
        navigateToURL(UrlFactory.THE_NEWEST_0_TO_50_PRICE);

        pageLongDownScroll();

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.forEach(png -> {
            logger.info("Check Response Url : " + png.getRequest().getUrl());

            Assert.assertEquals(
                    "HTTP Request Error : " + png.getRequest().getUrl(),
                    200, png.getResponse().getStatus());
        });
    }

    @Test
    @Description("En yeniler sayfası yüklenirken yapılan requestlerin response larının 200 olduğunun kontrolü.")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testCartPageLoadRequest()
    {
        randomProductSelectAndAddBasket();

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.forEach(png -> {
            logger.info("Check Response Url : " + png.getRequest().getUrl());

            Assert.assertEquals(
                    "HTTP Request Error : " + png.getRequest().getUrl(),
                    200, png.getResponse().getStatus());
        });
    }
}
