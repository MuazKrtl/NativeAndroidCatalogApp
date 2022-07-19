package com.muazkartal.yapicatalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity implements ImageRecyclerViewClickListener {

    private AdView mAdView,mAdView2;
    ArrayList<String> catalogImagesFromFb;
    ImageRecycleAdapter imageRecycleAdapter;
    RecyclerView recyclerView;

    //Banner ID : ca-app-pub-6906807864432817/5259966627
    //Banner Test ID : ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest2);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        catalogImagesFromFb = new ArrayList<>();
        Intent intent = getIntent();
        catalogImagesFromFb = intent.getStringArrayListExtra("arrayImage");

        imageRecycleAdapter = new ImageRecycleAdapter(catalogImagesFromFb, ImageActivity.this,ImageActivity.this::recyclerViewListClicked);
        recyclerView.setAdapter(imageRecycleAdapter);
        imageRecycleAdapter.notifyDataSetChanged();

    }


    @Override
    public void recyclerViewListClicked(View v, int position) {

    }

}