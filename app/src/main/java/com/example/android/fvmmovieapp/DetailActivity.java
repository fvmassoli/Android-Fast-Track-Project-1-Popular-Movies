package com.example.android.fvmmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.example.android.fvmmovieapp.fragments.DetailFragment;

import static com.example.android.fvmmovieapp.utilities.Constants.DETAIL_FRAGMENT_TAG;
import static com.example.android.fvmmovieapp.utilities.Constants.SINGLE_MOVIE_DETAIL;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment mDetailFragment;
    private Intent mIntent;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIntent = getIntent();
        if(mIntent != null && mIntent.hasExtra(SINGLE_MOVIE_DETAIL))
            mMovie = getIntent().getParcelableExtra(SINGLE_MOVIE_DETAIL);

        if(savedInstanceState == null) {
            mDetailFragment = DetailFragment.newInstance(mMovie);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_container, mDetailFragment, DETAIL_FRAGMENT_TAG)
                    .commit();
        }
    }
}
