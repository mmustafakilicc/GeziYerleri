package com.klc.geziyerleri.network.repository;

import com.klc.geziyerleri.data.City;

import java.util.List;

import retrofit2.Call;

public class CityRepository extends BaseRepository {

    private static CityRepository repository;

    public static CityRepository newInstance() {

        if (repository == null) {
            repository = new CityRepository();
        }

        return repository;
    }

    public Call<List<City>> loadRegionCities(int regionId) {
        return getApi().loadRegionCities(regionId);
    }
}
