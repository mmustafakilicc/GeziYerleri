package com.klc.geziyerleri.constants;

public class Constants {

    public static class WebService{
        public static final String BASE_URL = "http://10.0.2.2:52708/api/";
        //public static final String BASE_URL = "http://192.168.1.22:3000/api/";
        public static final String IMAGE_URL = "http://10.0.2.2:52708/api/images/";
        public static final String GET_REGIONS = "Region/";
        public static final String GET_REGION_CITIES = "City/RegionCities/{regionId}";
        public static final String GET_CITY_PLACES = "Place/CityPlaces/{cityId}";
    }

    public static class IntentExtras{
        public static final String REGION_EXTRA = "region_extra";
        public static final String CITY_EXTRA = "city_extra";
        public static final String PLACE_EXTRA = "place_extra";
    }

    public static class Messages{
        public static final String BILINMEYEN_HATA = "Kesin bir şeyler oldu ama biz farkedemedik.";
    }
}
