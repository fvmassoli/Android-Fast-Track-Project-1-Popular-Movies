package com.example.android.fvmmovieapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.example.android.fvmmovieapp.fragments.DetailFragment;
import com.example.android.fvmmovieapp.fragments.RecyclerViewAdapter;

import static com.example.android.fvmmovieapp.utilities.Constants.DETAIL_FRAGMENT_TAG;
import static com.example.android.fvmmovieapp.utilities.Constants.SAVED_DETAIL_MOVIE;
import static com.example.android.fvmmovieapp.utilities.Constants.SINGLE_MOVIE_DETAIL;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.GridItemClickListener{

    private boolean mIsTwoPaneMode;
    private DetailFragment mDetailFragment;
    private Movie mMovie = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsTwoPaneMode = (findViewById(R.id.detail_container) != null) ? true : false;

        if(savedInstanceState != null && savedInstanceState.containsKey(SAVED_DETAIL_MOVIE))
            mMovie = savedInstanceState.getParcelable(SAVED_DETAIL_MOVIE);

        if(mIsTwoPaneMode && mMovie != null) {
            mDetailFragment = DetailFragment.newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, mDetailFragment, DETAIL_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onGridItemClick(Movie movie) {
        if(mIsTwoPaneMode){
            mDetailFragment = DetailFragment.newInstance(movie);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, mDetailFragment, DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(SINGLE_MOVIE_DETAIL, movie);
            startActivity(intent);
        }
    }

}
