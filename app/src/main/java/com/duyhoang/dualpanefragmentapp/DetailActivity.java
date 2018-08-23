package com.duyhoang.dualpanefragmentapp;


import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Check if Landscape mode is on, then going for finishing this activity
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            // See fields of Configuration class.
            finish();
            return;
        }

        if(savedInstanceState == null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(getIntent().getExtras());
            ft.add(R.id.details, detailFragment).commit();
        }
        else{
            Log.e("mError:", "onCreate: savedInstanceState is not null");

        }

    }
}
