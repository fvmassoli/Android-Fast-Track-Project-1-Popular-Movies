package com.example.android.fvmmovieapp.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static com.example.android.fvmmovieapp.utilities.Constants.API_KEY_QUERY_PARAM;
import static com.example.android.fvmmovieapp.utilities.Constants.BASE_QUERY_URL;
import static com.example.android.fvmmovieapp.utilities.Constants.LANGUAGE_QUERY_PARAM;
import static com.example.android.fvmmovieapp.utilities.Constants.MOVIE_DB_APY_KEY;
import static com.example.android.fvmmovieapp.utilities.Constants.PAGE_QUERY_PARAMTER;
import static com.example.android.fvmmovieapp.utilities.Constants.PAGE_TO_QUERY;


public final class UtilMethods {

    public static Typeface getTypeface(Context context, int type){
        if(type == 0)
            return Typeface.createFromAsset(context.getAssets(), "fonts/aller_lt.ttf");
        else
            return Typeface.createFromAsset(context.getAssets(), "fonts/aller_rg.ttf");
    }

    public static URL buildUrl(String movieCriteriaSelection, String language) {

        Uri builtUri = Uri.parse(BASE_QUERY_URL).buildUpon()
                .appendPath(movieCriteriaSelection)
                .appendQueryParameter(API_KEY_QUERY_PARAM, MOVIE_DB_APY_KEY)
                .appendQueryParameter(LANGUAGE_QUERY_PARAM, language)
                .appendQueryParameter(PAGE_QUERY_PARAMTER, Integer.toString(PAGE_TO_QUERY))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static boolean isWifiConnected(Context context) {
        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
    }
}
