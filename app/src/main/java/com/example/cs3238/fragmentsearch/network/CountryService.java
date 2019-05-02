package com.example.cs3238.fragmentsearch.network;

import com.example.cs3238.fragmentsearch.model.CountryData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    @GET("rest/v2/all")
    Call<ArrayList<CountryData>> getCountryDetails();

}
