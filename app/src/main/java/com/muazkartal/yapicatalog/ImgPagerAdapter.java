package com.muazkartal.yapicatalog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImgPagerAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private int position;
    private Context context;

    private PhotoView photoView;
    private ViewGroup.LayoutParams layoutParams;


    public ImgPagerAdapter(Context context,ArrayList<String> images,int position) {
        this.images = images;
        this.context = context;
        this.position = position;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        photoView = new PhotoView(context);
        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

        photoView.setLayoutParams(layoutParams);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Picasso.get().load(images.get(position)).into(photoView);

        container.addView(photoView);
        return photoView;
    }
}
