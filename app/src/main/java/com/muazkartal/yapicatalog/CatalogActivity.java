package com.muazkartal.yapicatalog;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class CatalogActivity extends AppCompatActivity implements CatalogRecyclerViewClickListener {

    private AdView mAdView,mAdView2;

    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> catalogDateFromFb,catalogImagesFromFb;
    CatalogRecycleAdapter catalogRecycleAdapter;
    String option,path;
    RecyclerView recyclerView;
    AlertWait alertWait;



    //Banner ID : ca-app-pub-6906807864432817/5259966627
    //Banner Test ID : ca-app-pub-3940256099942544/6300978111

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

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

        alertWait = new AlertWait(CatalogActivity.this);

        catalogDateFromFb = new ArrayList<>();

        Intent intent = getIntent();
        option = intent.getStringExtra("Option");
        catalogDateFromFb = intent.getStringArrayListExtra("arrayDate");

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        catalogRecycleAdapter = new CatalogRecycleAdapter(catalogDateFromFb,CatalogActivity.this,CatalogActivity.this::recyclerViewListClicked,option);
        recyclerView.setAdapter(catalogRecycleAdapter);
        catalogRecycleAdapter.notifyDataSetChanged();
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        alertWait.show();
        position++;
        path = String.format("%s-%d",option,position);
        getDataFromFireStore(new FireStoreCallBackImage() {
            @Override
            public void onCallBack(ArrayList<String> catalogImagesFromFb) {
                Intent intent = new Intent(CatalogActivity.this,ImageActivity.class);
                intent.putExtra("arrayImage",catalogImagesFromFb);
                alertWait.dismiss();
                startActivity(intent);
            }
        });

    }
    public void getDataFromFireStore(FireStoreCallBackImage fireStoreCallBackImage) {
        catalogImagesFromFb = new ArrayList<>();
        CollectionReference collectionReference = firebaseFirestore.collection(option).document(path).collection("Images");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult()){
                        Map<String, Object> data = documentSnapshot.getData();
                        String image = (String) data.get("image");
                        catalogImagesFromFb.add(image);
                    }
                }
                fireStoreCallBackImage.onCallBack(catalogImagesFromFb);
            }
        });
    }
    private interface FireStoreCallBackImage{
        void onCallBack(ArrayList<String> catalogImagesFromFb);
    }
}