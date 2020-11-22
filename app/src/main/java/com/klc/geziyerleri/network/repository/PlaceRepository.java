package com.klc.geziyerleri.network.repository;

import com.klc.geziyerleri.data.Place;

import java.util.List;

import retrofit2.Call;

public class PlaceRepository extends BaseRepository {

    private static PlaceRepository repository;

    public static PlaceRepository newInstance() {
        if (repository == null) {
            repository = new PlaceRepository();
        }
        return repository;
    }

    public Call<List<Place>> loadCityPlaces(int cityId) {
        return getApi().loadCityPlaces(cityId);
    }
}
