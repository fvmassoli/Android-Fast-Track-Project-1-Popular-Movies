package com.example.android.fvmmovieapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.Button;
import com.example.android.fvmmovieapp.R;


public class NoNetworkDialog extends DialogFragment {

    public NoNetworkDialog(){

    }

    public static NoNetworkDialog newInstance(){

        NoNetworkDialog signUpSuccessfullDialog = new NoNetworkDialog();

        return signUpSuccessfullDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setIcon(R.drawable.ic_signal_wifi_off_black_24dp)
                .setTitle(" ")
                .setMessage(R.string.no_network_message)
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        NoNetworkDialog.this.getDialog().cancel();
                    }
                });

        builder.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    NoNetworkDialog.this.getDialog().dismiss();
                }
                return true;
            }
        });

        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnNegative = alert.getButton(Dialog.BUTTON_NEGATIVE);
                btnNegative.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btnNegative.setBackground(ResourcesCompat
                                    .getDrawable(getResources(), R.drawable.textview_backgroundcolor_button_effect, null));
                }
            }
        });

        return alert;
    }
}
