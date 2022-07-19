package com.muazkartal.yapicatalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView,mAdView2;

    String option;
    ImageView bauhaus,ikea,tekzen,koctas;
    ArrayList<String> catalogDateFromFb;
    FirebaseFirestore firebaseFirestore;
    AlertWait alertWait;

    //Banner ID : ca-app-pub-6906807864432817/5259966627
    //Banner Test ID : ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        alertWait = new AlertWait(MainActivity.this);



        firebaseFirestore = FirebaseFirestore.getInstance();


        bauhaus = findViewById(R.id.bauhaus);
        koctas = findViewById(R.id.koctas);
        ikea = findViewById(R.id.ikea);
        tekzen = findViewById(R.id.tekzen);

        bauhaus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertWait.show();
                option = "Bauhaus";
                getDataFromFireStore(new FireStoreCallBackTime() {
                    @Override
                    public void onCallBack(ArrayList<String> catalogDateFromFb) {
                        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                        intent.putExtra("Option",option);
                        intent.putExtra("arrayDate",catalogDateFromFb);
                        alertWait.dismiss();
                        startActivity(intent);
                    }
                });
            }
        });
        tekzen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertWait.show();
                option = "Tekzen";
                getDataFromFireStore(new FireStoreCallBackTime() {
                    @Override
                    public void onCallBack(ArrayList<String> catalogDateFromFb) {
                        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                        intent.putExtra("Option",option);
                        intent.putExtra("arrayDate",catalogDateFromFb);
                        alertWait.dismiss();
                        startActivity(intent);
                    }
                });
            }
        });
        ikea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertWait.show();
                option = "Ikea";
                getDataFromFireStore(new FireStoreCallBackTime() {
                    @Override
                    public void onCallBack(ArrayList<String> catalogDateFromFb) {
                        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                        intent.putExtra("Option",option);
                        intent.putExtra("arrayDate",catalogDateFromFb);
                        alertWait.dismiss();
                        startActivity(intent);
                    }
                });
            }
        });
        koctas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertWait.show();
                option = "Koçtaş";
                getDataFromFireStore(new FireStoreCallBackTime() {
                    @Override
                    public void onCallBack(ArrayList<String> catalogDateFromFb) {
                        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                        intent.putExtra("Option",option);
                        intent.putExtra("arrayDate",catalogDateFromFb);
                        alertWait.dismiss();
                        startActivity(intent);
                    }
                });
            }
        });

    }
    public void getDataFromFireStore(FireStoreCallBackTime fireStoreCallBackTime){
        catalogDateFromFb = new ArrayList<>();
        CollectionReference collectionReference = firebaseFirestore.collection(option);
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult()){
                        Map<String,Object> data = documentSnapshot.getData();

                        String time = (String) data.get("time");
                        catalogDateFromFb.add(time);
                    }
                }fireStoreCallBackTime.onCallBack(catalogDateFromFb);
            }
        });
    }
    private interface FireStoreCallBackTime{
        void onCallBack(ArrayList<String> catalogDateFromFb);
    }
}