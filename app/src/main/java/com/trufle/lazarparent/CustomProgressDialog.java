package com.trufle.lazarparent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by vishal on 2/24/18 as a part of Store-Manager-Pro.
 */

public class CustomProgressDialog extends AlertDialog {

    private boolean mIndeterminate = false;
    private String mMessage;

    static CustomProgressDialog customProgressDialog=null;

    protected CustomProgressDialog(@NonNull Context context) {
        super(context);
    }

    protected CustomProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View rootView = getLayoutInflater().inflate(R.layout.custom_progress_dialog, (ViewGroup) findViewById(R.id.pb_root_view));

        ProgressBar pBar = rootView.findViewById(R.id.pb_dialog_progress);
        pBar.setIndeterminate(mIndeterminate);
        TextView tvMsg = rootView.findViewById(R.id.pb_dialog_msg);
        if (mMessage != null) tvMsg.setText(mMessage);

        setView(rootView);
        super.onCreate(savedInstanceState);
    }

    public void setIndeterminate(@SuppressWarnings("SameParameterValue") boolean mIndeterminate) {
        this.mIndeterminate = mIndeterminate;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }


    public static void showCustomDialog(Context ctx, String msg){
       // if(customProgressDialog==null) {
        if(customProgressDialog!=null){
            if(customProgressDialog.isShowing()){
                customProgressDialog.dismiss();
            }
        }
            customProgressDialog = new CustomProgressDialog(ctx);
            customProgressDialog.setCancelable(false);
            customProgressDialog.setCanceledOnTouchOutside(false);
            customProgressDialog.setTitle("Please wait...");
            customProgressDialog.setIndeterminate(true);
        //}
        customProgressDialog.setMessage(msg);
        customProgressDialog.show();

    }
    public static void showCustomDialog(Context ctx){
        showCustomDialog(ctx,"Loading...");
    }

    public static void dismissCustomDialog(){
        if(customProgressDialog!=null){
            if(customProgressDialog.isShowing()){
                customProgressDialog.dismiss();
            }
        }
    }


}
