package com.klc.geziyerleri.network.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.Region;
import com.klc.geziyerleri.network.repository.RegionRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionViewModel extends IBaseViewModel {

    private MutableLiveData<List<Region>> regions;

    private RegionRepository repository;

    public MutableLiveData<List<Region>> getRegions() {

        if (repository == null) {
            repository = RegionRepository.newInstance();
        }

        if (regions == null) {
            regions = new MutableLiveData<>();
        }

        return regions;
    }

    public void loadRegions() {
        getError().setValue(null);
        getIsLoading().setValue(true);
        repository.loadRegions().enqueue(new Callback<List<Region>>() {
            @Override
            public void onResponse(@NonNull Call<List<Region>> call, @NonNull Response<List<Region>> response) {
                if (response.isSuccessful()) {
                    getError().setValue(null);
                    regions.setValue(response.body());
                } else {
                    if (response.errorBody() != null) {
                        getError().setValue(new Throwable(response.errorBody().toString()));
                    } else {
                        getError().setValue(new Throwable(Constants.Messages.BILINMEYEN_HATA));
                    }
                    regions.setValue(null);
                }
                getIsLoading().setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Region>> call, @NonNull Throwable t) {
                getError().setValue(t);
                getIsLoading().setValue(false);
            }
        });
    }
}
