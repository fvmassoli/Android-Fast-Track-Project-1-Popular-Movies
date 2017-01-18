package com.example.android.fvmmovieapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.android.fvmmovieapp.R;
import com.example.android.fvmmovieapp.data_model.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public interface GridItemClickListener{
        void onGridItemClick(Movie movie);
    }

    private GridItemClickListener mGridItemClickListener;
    private Context mContext;
    private ArrayList<Movie> mData = null;

    public RecyclerViewAdapter(GridItemClickListener gridItemClickListener, Context context){
        this.mGridItemClickListener = gridItemClickListener;
        this.mContext               = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.grid_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(Uri.parse(mData.get(position).getPosterPath()))
                .noPlaceholder()
                .error(R.drawable.ic_report_problem_black_24dp)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(ArrayList<Movie> data){
        mData = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mGridItemClickListener.onGridItemClick(mData.get(position));
        }
    }
}
