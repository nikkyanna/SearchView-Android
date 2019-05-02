package com.example.cs3238.fragmentsearch.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cs3238.fragmentsearch.R;
import com.example.cs3238.fragmentsearch.adapter.CountryAdapter;
import com.example.cs3238.fragmentsearch.model.CountryData;
import com.example.cs3238.fragmentsearch.network.CountryService;
import com.example.cs3238.fragmentsearch.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private View mView;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private CountryAdapter mCountryAdapter;

    //countryData - to save original data from server
    //filteredCountryData - to save filtered data from countryDara
    private ArrayList<CountryData> countryData = new ArrayList<>();
    public ArrayList<CountryData> filteredCountryData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchView = mView.findViewById(R.id.sv_search);
        mSearchView.setMaxWidth( Integer.MAX_VALUE );
        mSearchView.setIconified(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() < 2){
                    filteredCountryData.clear();
                    mCountryAdapter.notifyDataSetChanged();
                }else {

                    getCountry(s);
                }


                return false;
            }
        });

        mRecyclerView = mView.findViewById(R.id.rv_search_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCountryAdapter = new CountryAdapter(filteredCountryData, getActivity());
        mRecyclerView.setAdapter(mCountryAdapter);
        mCountryAdapter.notifyDataSetChanged();

        return mView;
    }



    private void getCountry(final String s) {


        CountryService countryService = RetrofitInstance.createService(CountryService.class, getActivity());
        Call<ArrayList<CountryData>> call = countryService.getCountryDetails();
        call.enqueue(new Callback<ArrayList<CountryData>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<CountryData>> call, @NonNull Response<ArrayList<CountryData>> response) {

                if (response.code() == 200){
                    countryData.clear();
                    countryData.addAll(response.body());
                    filterCountryArray(s);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<CountryData>> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "ERROR!!Check Internet Connectivity", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void filterCountryArray(String newText){

        String countryName;
        filteredCountryData.clear();

        for (int i = 0; i < countryData.size(); i++){
            countryName = countryData.get(i).getName().toLowerCase();
            if (countryName.contains(newText.toLowerCase())){
                filteredCountryData.add(countryData.get(i));
            }
        }

        System.out.println(filteredCountryData);

        mCountryAdapter.notifyDataSetChanged();
        countryData.clear();
    }



}
