package com.example.cs3238.fragmentsearch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs3238.fragmentsearch.R;
import com.example.cs3238.fragmentsearch.model.CountryData;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>  {

    private ArrayList<CountryData> mlist;
    private Context mContext;

    public CountryAdapter(ArrayList<CountryData> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_items, viewGroup, false);
        return new CountryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, final int i) {
        if (mlist.get(i) != null) {
            countryViewHolder.mCountryName.setText(mlist.get(i).getName());

            countryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mlist.get(i).getName(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView mCountryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountryName = itemView.findViewById(R.id.tv_name);
        }
    }


}
