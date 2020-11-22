package com.klc.geziyerleri.network.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.Place;
import com.klc.geziyerleri.network.repository.PlaceRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceViewModel extends IBaseViewModel {

    private MutableLiveData<List<Place>> liveDataPlaces;

    private PlaceRepository repository;

    public MutableLiveData<List<Place>> getPlaces() {

        if (repository == null) {
            repository = PlaceRepository.newInstance();
        }

        if (liveDataPlaces == null) {
            liveDataPlaces = new MutableLiveData<>();
        }

        return liveDataPlaces;
    }

    public void loadCityPlaces(int cityId) {
        getError().setValue(null);
        getIsLoading().setValue(true);
        repository.loadCityPlaces(cityId).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(@NonNull Call<List<Place>> call, @NonNull Response<List<Place>> response) {
                if (response.isSuccessful()) {
                    getError().setValue(null);
                    liveDataPlaces.setValue(response.body());
                } else {
                    if (response.errorBody() != null) {
                        getError().setValue(new Throwable(response.errorBody().toString()));
                    } else {
                        getError().setValue(new Throwable(Constants.Messages.BILINMEYEN_HATA));
                    }
                    liveDataPlaces.setValue(null);
                }
                getIsLoading().setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Place>> call, @NonNull Throwable t) {
                getError().setValue(null);
                getIsLoading().setValue(false);
                liveDataPlaces.setValue(null);
            }
        });
    }
}
