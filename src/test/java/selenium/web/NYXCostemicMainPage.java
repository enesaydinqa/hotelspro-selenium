package selenium.web;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.PageFactory;

import base.BaseWebTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import selenium.pages.MainPageWeb;

@DisplayName("NYX Costemic Main Page - Web")
public class NYXCostemicMainPage extends BaseWebTest
{

    Logger LOGGER = Logger.getLogger(NYXCostemicMainPage.class.getName());


    @Test
    @DisplayName("Main Page Load PNG")
    public void testMainPageLoadPNG()
    {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Request PNG Link");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png"))
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertEquals(200, png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("The Main Page Traffic")
    public void testMainPageTraffic()
    {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Traffic");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    //Assert.assertTrue(
                    //        "Broken : " + png.getRequest().getUrl(),
                    //        400 > png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("The Newest Products")
    public void testTheNewestProducts() throws InterruptedException
    {
        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        navigateToURL(URLFactory.MAIN_URL);

        wait(3);
        waitElementVisible(mainPage.getMainPageFancyBoxIFrame());
        switchFrame(mainPage.getMainPageFancyBoxIFrame());
        waitElementToBeClickable(mainPage.getMainPageBeInformed());
        click(mainPage.getMainPageBeInformed());

        driver.switchTo().parentFrame();

        listElementRandomClick(mainPage.getNewestProducts());
        waitElementVisible(mainPage.getBreadCrumb());
        mainPage.getBreadCrumb().isDisplayed();
    }

    @Test
    @DisplayName("Product Sale Price")
    public void testProductSalePrice()
    {
        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        navigateToURL(URLFactory.MAIN_URL);

        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });
    }

    @Test
    @DisplayName("Main Page Slider")
    public void testMainPageSlider()
    {
        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        navigateToURL(URLFactory.MAIN_URL);

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
