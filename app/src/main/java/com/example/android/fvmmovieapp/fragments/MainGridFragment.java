package com.example.android.fvmmovieapp.fragments;


import android.app.DialogFragment;
import android.content.res.Configuration;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.fvmmovieapp.R;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.example.android.fvmmovieapp.dialogs.NoNetworkDialog;
import com.example.android.fvmmovieapp.utilities.FetchDataAsync;
import com.example.android.fvmmovieapp.utilities.UtilMethods;
import java.util.ArrayList;

import static com.example.android.fvmmovieapp.utilities.Constants.NETWORK_NOT_AVAILABLE;
import static com.example.android.fvmmovieapp.utilities.Constants.OVERFLOW_MENU_QUERY_PARAM;
import static com.example.android.fvmmovieapp.utilities.Constants.SAVED_MOVIES;
import static com.example.android.fvmmovieapp.utilities.Constants.MOST_POPULAR_PARAM;
import static com.example.android.fvmmovieapp.utilities.Constants.MOVIE_POSTER_LOADER_ID;
import static com.example.android.fvmmovieapp.utilities.Constants.TOP_RATED_PARAM;


public class MainGridFragment extends BaseClass implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private String mMovieCriteriaSelection = null;
    private ArrayList<Movie> mMovies;

    public MainGridFragment(){

    }

    public static MainGridFragment newInstance() {

        Bundle args = new Bundle();

        MainGridFragment fragment = new MainGridFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        this.mMovies            = new ArrayList<>();
        this.mGridLayoutManager = new GridLayoutManager(mContext, setGridColumnNumber());

        if(savedInstanceState != null && savedInstanceState.containsKey(SAVED_MOVIES))
            mMovies.addAll(savedInstanceState.<Movie>getParcelableArrayList(SAVED_MOVIES));

        if(savedInstanceState != null && savedInstanceState.containsKey(OVERFLOW_MENU_QUERY_PARAM))
            this.mMovieCriteriaSelection = savedInstanceState.getString(OVERFLOW_MENU_QUERY_PARAM);
         else
            this.mMovieCriteriaSelection = MOST_POPULAR_PARAM;
    }
    private int setGridColumnNumber(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return 5;
        else
            return 3;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.grid_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_grid_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter((RecyclerViewAdapter.GridItemClickListener) getActivity(), getActivity());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if(mMovies.size() == 0)
            initOrRestartLoader(true);
        else
            mRecyclerViewAdapter.setData(mMovies);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_criteria_menu, menu);
        if(mMovieCriteriaSelection.equals(MOST_POPULAR_PARAM))
            menu.findItem(R.id.most_popular).setChecked(true);
        else
            menu.findItem(R.id.highest_rate).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!item.isChecked())
            item.setChecked(true);
        mMovies = new ArrayList<>();
        switch (item.getItemId()){
            case R.id.most_popular:
                mMovieCriteriaSelection = MOST_POPULAR_PARAM;
                initOrRestartLoader(false);
                break;
            case R.id.highest_rate:
                mMovieCriteriaSelection = TOP_RATED_PARAM;
                initOrRestartLoader(false);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initOrRestartLoader(boolean init){
        if(isNetworkAvailable()){
            if(init)
                getLoaderManager().initLoader(MOVIE_POSTER_LOADER_ID, null, this);
            else
                getLoaderManager().restartLoader(MOVIE_POSTER_LOADER_ID, null, this);
        } else {
            DialogFragment dialog = NoNetworkDialog.newInstance();
            dialog.setCancelable(false);
            dialog.show(getActivity().getFragmentManager(), NETWORK_NOT_AVAILABLE);
        }
    }
    private boolean isNetworkAvailable() {
        return UtilMethods.isWifiConnected(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SAVED_MOVIES, mMovies);
        outState.putString(OVERFLOW_MENU_QUERY_PARAM, mMovieCriteriaSelection);
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, Bundle args) {
        showProgressDialog();
        return new FetchDataAsync(getActivity(), mMovieCriteriaSelection, mRecyclerViewAdapter);
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
        if(data != null)
            mMovies.addAll(data);
        hideProgressDialog();
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
    }

}
