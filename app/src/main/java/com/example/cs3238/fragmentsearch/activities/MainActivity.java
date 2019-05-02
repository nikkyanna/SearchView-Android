package com.example.cs3238.fragmentsearch.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cs3238.fragmentsearch.R;
import com.example.cs3238.fragmentsearch.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initialize all views
        bindViews();
        //click for views
        clickListeners();

    }


    //bindViews() - initialize all views
    private void bindViews() {
        mImageView = findViewById(R.id.iv_search);
    }

    //clickListeners() - //click for views
    private void clickListeners() {
        mImageView.setOnClickListener(this);
    }

    // when imageview is clicked activity is replaced with fragment
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                loadFragment();
                break;
        }
    }
    //Loading Fragment
    private void loadFragment() {

        SearchFragment searchFragment = new SearchFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_search, searchFragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}
