package com.example.android.fvmmovieapp.utilities;

import com.example.android.fvmmovieapp.data_model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static com.example.android.fvmmovieapp.utilities.Constants.BACK_DROP_PATH;
import static com.example.android.fvmmovieapp.utilities.Constants.OVERVIEW;
import static com.example.android.fvmmovieapp.utilities.Constants.POSTER_PATH;
import static com.example.android.fvmmovieapp.utilities.Constants.ORIGINAL_TITLE;
import static com.example.android.fvmmovieapp.utilities.Constants.RELEASE_DATE;
import static com.example.android.fvmmovieapp.utilities.Constants.RESULTS;
import static com.example.android.fvmmovieapp.utilities.Constants.VOTE_AVERAGE;


public final class DataParser {

    public static ArrayList<Movie> ParseJson(String jsonDataStr) throws JSONException {

        ArrayList<Movie> movieList = new ArrayList<>();
        Movie movie;

        JSONObject movieJson = new JSONObject(jsonDataStr);

        if(movieJson != null){

            JSONArray jsonArray = movieJson.getJSONArray(RESULTS);

            int jsonArraySize = jsonArray.length();
            for(int i=0; i<jsonArraySize; i++){

                JSONObject movieInfoJson = jsonArray.getJSONObject(i);

                movie = new Movie();

                movie.setOriginalTitle(movieInfoJson.getString(ORIGINAL_TITLE));
                movie.setReleaseDate(movieInfoJson.getString(RELEASE_DATE));
                movie.setPosterPath(movieInfoJson.getString(POSTER_PATH));
                movie.setBackDropPath(movieInfoJson.getString(BACK_DROP_PATH));
                movie.setVoteAverage(movieInfoJson.getString(VOTE_AVERAGE));
                movie.setOverView(movieInfoJson.getString(OVERVIEW));

                movieList.add(movie);
            }
            return movieList;
        }
        else
            return null;
    }

}