package com.selenium.context.utils.param;

public interface Constants
{
    String HOTEL_NAME = "Istanbul";
    String COUNTRY = "Turkey";

    interface SearchResult
    {
        String FILTER_RESULT_NO_MESSAGE = "Arama kriterlerinize uygun otel bulunamadı.Daha sonra tekrar deneyiniztarih değiştirme veya oda tercihleri";
    }

    interface Filter
    {
        String DISTRICT = "Istanbul (and vicinity)";
        String HOTEL_NAME = "Zirve";
        String FACILITY_NAME = "Restaurant";
        String LANDMARK = "İstanbul Teknik Üniversitesi";
        String ROOM_TYPE = "Double";
        String MEAL_TYPE = "Breakfast Continental";
        String ROOM_CATEGORY = "Suite";
        int MIN_PRICE = 50;
        int MAX_PRICE = 500;
        String PROMOTION_AND_DEALS = "Show only promotions & deals";
    }
}
