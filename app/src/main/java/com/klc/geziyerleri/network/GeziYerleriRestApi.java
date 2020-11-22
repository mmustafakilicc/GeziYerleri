package com.klc.geziyerleri.network;

import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.City;
import com.klc.geziyerleri.data.Place;
import com.klc.geziyerleri.data.Region;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeziYerleriRestApi {

    @GET(Constants.WebService.GET_REGIONS)
    Call<List<Region>> loadRegions();

    @GET(Constants.WebService.GET_REGION_CITIES)
    Call<List<City>> loadRegionCities(@Path("regionId") int regionId);

    @GET(Constants.WebService.GET_CITY_PLACES)
    Call<List<Place>> loadCityPlaces(@Path("cityId") int cityId);
}
