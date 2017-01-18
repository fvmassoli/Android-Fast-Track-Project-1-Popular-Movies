package com.example.android.fvmmovieapp.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.fvmmovieapp.R;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.example.android.fvmmovieapp.utilities.UtilMethods;
import com.squareup.picasso.Picasso;

import static com.example.android.fvmmovieapp.utilities.Constants.SAVED_DETAIL_MOVIE;
import static com.example.android.fvmmovieapp.utilities.Constants.SINGLE_MOVIE_DETAIL;


public class DetailFragment extends Fragment {

    private Movie mMovie;

    private ImageView iv_movie_poster_detail;
    private ImageView big_img;

    private TextView movie_title;
    private TextView release_date;
    private TextView rating;
    private TextView synopsis;
    private TextView release_date_tl;
    private TextView rating_tl;
    private TextView synopsis_tl;

    public DetailFragment(){

    }

    public static DetailFragment newInstance(Movie movie) {

        Bundle args = new Bundle();
        if(movie != null)
            args.putParcelable(SINGLE_MOVIE_DETAIL, movie);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if(savedInstanceState != null && savedInstanceState.containsKey(SAVED_DETAIL_MOVIE))
            mMovie = savedInstanceState.getParcelable(SAVED_DETAIL_MOVIE);
        else
            mMovie = bundle.getParcelable(SINGLE_MOVIE_DETAIL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        if(mMovie != null) {
            findViews(v);
            setMovieDetialPageContent();
        }

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mMovie != null)
            outState.putParcelable(SAVED_DETAIL_MOVIE, mMovie);
    }

    private void findViews(View v){

        big_img                = (ImageView) v.findViewById(R.id.big_img);
        iv_movie_poster_detail = (ImageView) v.findViewById(R.id.iv_movie_poster_detail);

        movie_title  = (TextView) v.findViewById(R.id.movie_title);
        release_date = (TextView) v.findViewById(R.id.release_date);
        rating       = (TextView) v.findViewById(R.id.rating);
        synopsis     = (TextView) v.findViewById(R.id.synopsis);
        release_date_tl = (TextView) v.findViewById(R.id.release_date_tl);
        rating_tl       = (TextView) v.findViewById(R.id.rating_tl);
        synopsis_tl     = (TextView) v.findViewById(R.id.synopsis_tl);
    }

    private void setMovieDetialPageContent(){

        Picasso.with(getActivity())
                .load(mMovie.getBackDropPath())
                .noPlaceholder()
                .error(R.drawable.ic_report_problem_black_24dp)
                .into(big_img);

        Picasso.with(getActivity())
                .load(mMovie.getPosterPath())
                .noPlaceholder()
                .error(R.drawable.ic_report_problem_black_24dp)
                .into(iv_movie_poster_detail);

        movie_title.setText(mMovie.getOriginalTitle());
        release_date.setText(mMovie.getReleaseDate());
        rating.setText(mMovie.getVoteAverage());
        synopsis.setText(mMovie.getOverView());
        release_date_tl.setText(getString(R.string.release_date));
        rating_tl.setText(getString(R.string.rate));
        synopsis_tl.setText(getString(R.string.overview));

        setTextViewTypeFace();
    }
    private void setTextViewTypeFace(){
        Typeface typeface  = UtilMethods.getTypeface(getActivity(), 0);
        Typeface typeface2 = UtilMethods.getTypeface(getActivity(), 1);

        movie_title.setTypeface(typeface2);
        release_date.setTypeface(typeface);
        rating.setTypeface(typeface);
        synopsis.setTypeface(typeface);
        release_date_tl.setTypeface(typeface);
        rating_tl.setTypeface(typeface);
        synopsis_tl.setTypeface(typeface);
    }

}
