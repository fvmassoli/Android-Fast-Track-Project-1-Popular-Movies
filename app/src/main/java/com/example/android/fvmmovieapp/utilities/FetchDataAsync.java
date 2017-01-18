package com.example.android.fvmmovieapp.utilities;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.example.android.fvmmovieapp.fragments.RecyclerViewAdapter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


public class FetchDataAsync extends AsyncTaskLoader<ArrayList<Movie>> {

    private String mMovieCriteriaSelection;
    private String mJsonMovieResponse;
    private ArrayList<Movie> mDownlaodedMovieData;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public FetchDataAsync(Context context, String movieCriteriaSelection, RecyclerViewAdapter recyclerViewAdapter) {
        super(context);
        this.mMovieCriteriaSelection = movieCriteriaSelection;
        this.mRecyclerViewAdapter    = recyclerViewAdapter;
    }

    @Override protected void onStartLoading() {
        if(mDownlaodedMovieData != null) {
            deliverResult(mDownlaodedMovieData);
        }else
            forceLoad();
    }

    @Override
    public ArrayList<Movie> loadInBackground() {

        URL movieRequestUrl = UtilMethods.buildUrl(mMovieCriteriaSelection, getDeviceLanguage());

        try {

            mJsonMovieResponse   = UtilMethods.getResponseFromHttpUrl(movieRequestUrl);
            mDownlaodedMovieData = DataParser.ParseJson(mJsonMovieResponse);

            return mDownlaodedMovieData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getDeviceLanguage(){

        StringBuilder sb = new StringBuilder();
        sb.append(Locale.getDefault().getISO3Language().substring(0,2));
        sb.append("-");
        sb.append(Locale.getDefault().getISO3Country().substring(0,2));

        return sb.toString();
    }

    @Override
    public void deliverResult(ArrayList<Movie> data) {
        if(isStarted()) {
            mRecyclerViewAdapter.setData(data);
            super.deliverResult(data);
        }
    }

    @Override protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }

    @Override protected void onReset() {
        super.onReset();
        onStopLoading();
        mMovieCriteriaSelection = null;
    }

}
