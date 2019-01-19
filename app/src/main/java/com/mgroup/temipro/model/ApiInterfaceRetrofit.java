package com.mgroup.temipro.model;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterfaceRetrofit {

    @POST("api/v2/public-store/load")
    Call<ArrayList<Contact>> getContacts(@Body InfoRetrofit infoRetrofit);

}