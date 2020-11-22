package com.klc.geziyerleri.network.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.City;
import com.klc.geziyerleri.network.repository.CityRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityViewModel extends IBaseViewModel {

    private MutableLiveData<List<City>> cities;

    private CityRepository repository;

    public MutableLiveData<List<City>> getRegionCities() {

        if (repository == null) {
            repository = CityRepository.newInstance();
        }

        if (cities == null) {
            cities = new MutableLiveData<>();
        }

        return cities;
    }

    public void loadRegionCities(int regionId) {

        getError().setValue(null);
        getIsLoading().setValue(true);

        repository.loadRegionCities(regionId).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                if (response.isSuccessful()) {
                    getError().setValue(null);
                    cities.setValue(response.body());
                } else {
                    if (response.errorBody() != null) {
                        getError().setValue(new Throwable(response.errorBody().toString()));
                    } else {
                        getError().setValue(new Throwable(Constants.Messages.BILINMEYEN_HATA));
                    }
                    cities.setValue(null);
                }
                getIsLoading().setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {
                getError().setValue(t);
                getIsLoading().setValue(false);
                cities.setValue(null);
            }
        });
    }
}
