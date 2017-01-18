package com.example.android.fvmmovieapp.utilities;

import com.example.android.fvmmovieapp.BuildConfig;


public class Constants {

    public static final String MOVIE_DB_APY_KEY     = BuildConfig.MOVIE_DB_APY_KEY;
    public static final String BASE_QUERY_URL       = "https://api.themoviedb.org/3/movie/";
    public static final String BASE_POSTER_URL      = "http://image.tmdb.org/t/p/w185/";
    public static final String BASE_BACK_DROP_URL   = "http://image.tmdb.org/t/p/w1280/";
    public static final String TOP_RATED_PARAM      = "top_rated";
    public static final String MOST_POPULAR_PARAM   = "popular";
    public static final String API_KEY_QUERY_PARAM  = "api_key";
    public static final String LANGUAGE_QUERY_PARAM = "language";
    public static final String PAGE_QUERY_PARAMTER  = "page";

    public static final String SAVED_MOVIES              = "saved_movies";
    public static final String SAVED_DETAIL_MOVIE        = "saved_detail_movie";
    public static final String OVERFLOW_MENU_QUERY_PARAM = "overflow_menu_query_param";
    public static final String SINGLE_MOVIE_DETAIL       = "single_movie_detail";

    public static final String ORIGINAL_TITLE = "original_title";
    public static final String RELEASE_DATE   = "release_date";
    public static final String POSTER_PATH    = "poster_path";
    public static final String BACK_DROP_PATH = "backdrop_path";
    public static final String VOTE_AVERAGE   = "vote_average";
    public static final String OVERVIEW       = "overview";
    public static final String RESULTS        = "results";

    public static final String NETWORK_NOT_AVAILABLE = "no_network";
    public static final String DETAIL_FRAGMENT_TAG   = "MDFT";

    public static final int MOVIE_POSTER_LOADER_ID = 1001;
    public static final int PAGE_TO_QUERY          = 1;
}
