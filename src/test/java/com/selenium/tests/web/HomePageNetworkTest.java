package com.selenium.tests.web;

import com.selenium.context.annotations.ExpectedResult;
import com.selenium.context.annotations.PreconditionsToReproduce;
import com.selenium.context.base.AbstractHotelsProTest;
import com.selenium.context.flag.ParallelExecutable;
import com.selenium.pages.UrlFactory;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

public class HomePageNetworkTest extends AbstractHotelsProTest
{
    private static final Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    @Before
    public void init() throws Exception
    {
        super.init(true);
    }

    @Test
    @Category(ParallelExecutable.class)
    @PreconditionsToReproduce("Kullanıcı login olmaz.")
    @ExpectedResult("Kullanıcının başarılı bir şekilde https://www.hotelspro.com/ sayfasına ulaşması, login olmadan sayfada ki alanları başarı ile görüntüleyebilmesi.")
    public void testHomePageLoadImages()
    {
        navigateToURL(UrlFactory.MAIN_URL_FIRST);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {

                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertEquals("HTTP Request Error : " + png.getRequest().getUrl(), 200, png.getResponse().getStatus());

                });
    }
}
