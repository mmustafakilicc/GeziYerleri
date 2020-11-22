package com.klc.geziyerleri.network.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IBaseViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading;

    private MutableLiveData<Throwable> error;

    public MutableLiveData<Throwable> getError() {
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if(isLoading == null){
            isLoading = new MutableLiveData<>(true);
        }
        return isLoading;
    }
}
