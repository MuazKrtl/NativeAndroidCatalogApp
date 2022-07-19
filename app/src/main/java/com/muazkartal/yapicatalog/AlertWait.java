package com.muazkartal.yapicatalog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class AlertWait extends Dialog {
    public ProgressBar progressBar;
    public AlertWait(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.wait_dialog);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
