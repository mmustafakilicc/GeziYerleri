package com.klc.geziyerleri.network.repository;

import com.klc.geziyerleri.data.Region;

import java.util.List;

import retrofit2.Call;

public class RegionRepository extends BaseRepository {

    private static RegionRepository repository;

    public static RegionRepository newInstance() {

        if (repository == null) {
            repository = new RegionRepository();
        }

        return repository;
    }

    public Call<List<Region>> loadRegions() {
        return getApi().loadRegions();
    }
}
