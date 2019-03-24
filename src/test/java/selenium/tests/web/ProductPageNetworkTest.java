package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsTest;
import context.flag.NetworkExecutable;
import context.flag.ParallelExecutable;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.web.MainPageWebPage;
import selenium.pages.web.ProductDetailPage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.stream.IntStream;

@NotThreadSafe
public class ProductPageNetworkTest extends AbstractNYXCosmeticsTest
{
    private static final Logger logger = Logger.getLogger(ProductPageNetworkTest.class);

    private MainPageWebPage mainPage;
    private ProductDetailPage productDetailPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);

        mainPage = new MainPageWebPage(driver);
        productDetailPage = new ProductDetailPage(driver);
    }

    @Test
    @Description("Ürün detay sayfasında yapılan requestlerin response ların 400 den küçük olduğunun kontrolü")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testProductDetail()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        scrollToElement(mainPage.getMainPageAllProduct().get(15));
        waitElementToBeClickable(mainPage.getMainPageAllProduct().get(15));
        click(mainPage.getMainPageAllProduct().get(15));
        wait(10);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertTrue(
                            "HTTP Request Error : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Ürün detay açıklamasının 20 karakter veya daha fazla olduğunun kontrolü")
    @Category({ParallelExecutable.class})
    public void testProductDescriptionLength()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(10, 15)
                .forEach(i ->
                {
                    scrollToElement(mainPage.getMainPageAllProduct().get(i));
                    waitElementToBeClickable(mainPage.getMainPageAllProduct().get(i));
                    click(mainPage.getMainPageAllProduct().get(i));

                    waitElementVisible(productDetailPage.getProductDetail());
                    String description = getText(productDetailPage.getProductDetail());
                    Assert.assertTrue(description.length() >= 20);
                    driver.navigate().back();
                });
    }
}
