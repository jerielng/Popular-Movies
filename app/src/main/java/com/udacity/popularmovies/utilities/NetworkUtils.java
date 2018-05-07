package com.udacity.popularmovies.utilities;

import android.content.res.Resources;
import android.net.Uri;

import com.udacity.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/";
    private static final String API_VERSION = "3";
    private static final String MOVIE = "movie";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY_VALUE = "";

    public static URL buildMovieUrl(String sortType) {
        Uri uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(API_VERSION)
                .appendPath(MOVIE)
                .appendPath(sortType)
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    //taken from udacity code
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
}
