package com.muazkartal.yapicatalog;

import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;

public class CustomAlertDialog extends Dialog implements View.OnClickListener {

    public Context context;
    public Button closeButton;
    public int position;
    public ArrayList<String> images;
    public ViewPager viewPager;
    public ImgPagerAdapter imgPagerAdapter;

    public CustomAlertDialog(@NonNull Context context, int position, ArrayList<String> images) {
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.context = context;
        this.position = position;
        this.images = images;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        closeButton = (Button) findViewById(R.id.closeButton);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        imgPagerAdapter = new ImgPagerAdapter(context,images,position);
        viewPager.setAdapter(imgPagerAdapter);

        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
