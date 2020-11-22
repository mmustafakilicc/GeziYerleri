package com.klc.geziyerleri.network.repository;

import com.klc.geziyerleri.network.GeziYerleriClient;
import com.klc.geziyerleri.network.GeziYerleriRestApi;

public class BaseRepository {

    private GeziYerleriRestApi api;

    public GeziYerleriRestApi getApi(){
        if(api == null){
            api = GeziYerleriClient.newInstance().create(GeziYerleriRestApi.class);
        }
        return api;
    }
}
