package com.example.android.fvmmovieapp.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import static com.example.android.fvmmovieapp.utilities.Constants.BASE_BACK_DROP_URL;
import static com.example.android.fvmmovieapp.utilities.Constants.BASE_POSTER_URL;


public class Movie implements Parcelable{

    private String mOriginalTitle;
    private String mReleaseDate;
    private String mPosterPath;
    private String mBackDropPath;
    private String mVoteAverage;
    private String mOverView;

    public Movie() {
    }

    public void setOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setPosterPath(String mPosterPath) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_POSTER_URL);
        sb.append(mPosterPath);
        this.mPosterPath = sb.toString();
    }

    public void setBackDropPath(String mBackDropPath) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_BACK_DROP_URL);
        sb.append(mBackDropPath);
        this.mBackDropPath = sb.toString();
    }

    public void setVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public void setOverView(String mOverView) {
        this.mOverView = mOverView;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackDropPath() {
        return mBackDropPath;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getOverView() {
        return mOverView;
    }


    protected Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mReleaseDate   = in.readString();
        mPosterPath    = in.readString();
        mBackDropPath  = in.readString();
        mVoteAverage   = in.readString();
        mOverView      = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mPosterPath);
        dest.writeString(mBackDropPath);
        dest.writeString(mVoteAverage);
        dest.writeString(mOverView);
    }
}
