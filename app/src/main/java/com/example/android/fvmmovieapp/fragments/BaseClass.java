package com.example.android.fvmmovieapp.fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import com.example.android.fvmmovieapp.R;

public class BaseClass extends Fragment {

    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.progress_dialog_loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
