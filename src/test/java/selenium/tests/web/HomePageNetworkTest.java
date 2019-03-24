package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsTest;
import context.flag.NetworkExecutable;
import context.flag.SerialExecutable;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.web.MainPageWebPage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.stream.IntStream;

@NotThreadSafe
public class HomePageNetworkTest extends AbstractNYXCosmeticsTest
{
    private static final Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    private MainPageWebPage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);

        mainPage = new MainPageWebPage(driver);
    }

    @Test
    @Description("Anasayfa yüklenirken yapılan png/jpg request lerin 200 (ok) olduğunun kontrolü")
    @Category({NetworkExecutable.class, SerialExecutable.class})
    public void testHomePageLoadPNG()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertEquals(
                            "HTTP Request Error : " + png.getRequest().getUrl(),
                            200, png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Anasayfa yüklenirken yapılan request lerin response larının 400 den küçük olduğunun kontrolü")
    @Category({NetworkExecutable.class, SerialExecutable.class})
    public void testHomePageNetwork()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream()
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertTrue("HTTP Request Error : " + png.getRequest().getUrl(), 400 > png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Anasayfa daki ürünlerin fiyatının 0 dan büyük olduğunun kontrolü")
    @Category({NetworkExecutable.class, SerialExecutable.class})
    public void testProductSalePrice()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count))));
    }

    @Test
    @Description("Anasayfadaki slider ın çalıştığının kontrolü")
    @Category({NetworkExecutable.class, SerialExecutable.class})
    public void testHomePageSlider()
    {
        navigateToURL(UrlFactory.MAIN_URL);

        closeCampaingPopup();

        IntStream.range(0, 3)
                .forEach(i ->
                {
                    waitElementVisible(mainPage.getActiveSliderImage());
                    String dataGtmPromotion = getAttribute(mainPage.getActiveSliderImage(), "data-swiper-slide-index");

                    waitElementToBeClickable(mainPage.getSliderNext().get(i));
                    click(mainPage.getSliderNext().get(i));

                    Assert.assertNotEquals(dataGtmPromotion, getAttribute(mainPage.getActiveSliderImage(), "data" +
                            "-swiper-slide-index"));
                });
    }
}
