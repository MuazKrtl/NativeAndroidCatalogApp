package com.muazkartal.yapicatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageRecycleAdapter extends RecyclerView.Adapter<ImageRecycleAdapter.ImageHolder> {

    private  ArrayList<String> catalogImages;
    private  Context context;
    private static ImageRecyclerViewClickListener recyclerViewClickListener;


    public ImageRecycleAdapter(ArrayList<String> catalogImages, Context context, ImageRecyclerViewClickListener clickListener) {
        this.catalogImages = catalogImages;
        this.context = context;
        this.recyclerViewClickListener = clickListener;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_row,parent,false);
        return new ImageHolder(view);
    }

    @Override
    public int getItemCount() {
        return catalogImages.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Picasso.get().load(catalogImages.get(position)).into(holder.imageView);
    }

    class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog(context,this.getLayoutPosition(),catalogImages);
            customAlertDialog.show();
        }
    }
}

