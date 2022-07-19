package com.muazkartal.yapicatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatalogRecycleAdapter extends RecyclerView.Adapter<CatalogRecycleAdapter.CatalogHolder> {

    private  ArrayList<String> catalogTime;
    private  Context context;
    private static CatalogRecyclerViewClickListener recyclerViewClickListener;
    private String option;

    public CatalogRecycleAdapter(ArrayList<String> catalogTime, Context context, CatalogRecyclerViewClickListener clickListener,String option) {
        this.catalogTime = catalogTime;
        this.context = context;
        this.recyclerViewClickListener = clickListener;
        this.option = option;
    }

    @NonNull
    @Override
    public CatalogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.catalog_row,parent,false);
        return new CatalogHolder(view);
    }

    @Override
    public int getItemCount() {
        return catalogTime.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogHolder holder, int position) {
        holder.textView.setText(catalogTime.get(position));
        switch (option){
            case "Ikea":
                holder.imageView.setImageResource(R.drawable.ikea);
                break;
            case "Tekzen":
                holder.imageView.setImageResource(R.drawable.tekzen);
                break;
            case "Bauhaus":
                holder.imageView.setImageResource(R.drawable.bau);
                break;
            case "Koçtaş":
                holder.imageView.setImageResource(R.drawable.koctas);
                break;
            default:holder.imageView.setImageResource(R.drawable.ikea);
        }
    }

    class CatalogHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public CatalogHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.recyclerViewListClicked(v,this.getLayoutPosition());
        }
    }
}

