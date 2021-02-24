package com.lanyoumobility.mobility_webview.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.lanyoumobility.mobility_webview.R;


/**
 * Created by EZ on 2017/7/14.
 */

public class CallDialogUtil {

    public static void call(final String mobile) {
        Activity activity = ActivityController.getCurrActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dailogView = View.inflate(activity, R.layout.dialog_call, null);
        final AlertDialog dialog = builder.setView(dailogView).create();
        TextView tvPhoneNumber = (TextView) dailogView.findViewById(R.id.tv_phone_number);
        tvPhoneNumber.setText(mobile);
        dailogView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dailogView.findViewById(R.id.tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mobile));
                ActivityController.getCurrActivity().startActivity(intent);
            }
        });
        dialog.show();
    }





}
