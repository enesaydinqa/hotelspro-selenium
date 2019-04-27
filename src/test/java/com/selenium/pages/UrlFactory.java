package com.selenium.pages;


public enum UrlFactory
{

    MAIN_URL_FIRST("https://www2.hotelspro.com"),
    MAIN_URL_SECOND("https://www.hotelspro.com"),
    PASSWORD_RESET(MAIN_URL_SECOND, "/account/password_reset/"),
    LOGIN(MAIN_URL_SECOND, "/account/login/"),
    SIGN_UP(MAIN_URL_FIRST, "/agency/signup/"),

    HOTELSPRO_LINKEDIN("https://www.linkedin.com/company/hotelspro/"),
    HOTELSPRO_INSTAGRAM("https://www.instagram.com/hotelspro/"),
    HOTELSPRO_FACEBOOK("https://www.facebook.com/HotelsPro"),
    HOTELSPRO_BLOG("http://blog.hotelspro.com/"),

    TERMS_CONDITIONS(MAIN_URL_FIRST, "/page/terms-conditions/"),
    PRIVACY(MAIN_URL_FIRST, "/page/privacy/"),
    CONTACT_US(MAIN_URL_FIRST, "/contactus/"),
    TRADE_FAIRS(MAIN_URL_FIRST, "/trade_fairs/"),
    FAQ(MAIN_URL_FIRST, "/faq/"),
    BLOG("http://blog.hotelspro.com/");

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
