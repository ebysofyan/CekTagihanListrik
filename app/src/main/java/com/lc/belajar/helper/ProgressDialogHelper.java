package com.lc.belajar.helper;

import android.app.ProgressDialog;

/**
 * Created by eby on 16/07/16.
 */
public class ProgressDialogHelper {
    public static void showDialog(ProgressDialog progressDialog, String message) {
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
}
