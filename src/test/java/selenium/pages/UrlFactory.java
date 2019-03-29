package selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www.nyxcosmetics.com.tr"),
    CHECKOUT_PAYMENT(MAIN_URL, "/checkout-payment");

    //-----

    public final String pageUrl;

    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
