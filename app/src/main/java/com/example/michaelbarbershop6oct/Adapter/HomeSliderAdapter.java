package com.example.michaelbarbershop6oct.Adapter;

import com.example.michaelbarbershop6oct.Model.Banner;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;


public class HomeSliderAdapter extends SliderAdapter {

    private static final String TAG = HomeSliderAdapter.class.getSimpleName();

    List<Banner> mBannerList;

    public HomeSliderAdapter(List<Banner> bannerList) {
        this.mBannerList = bannerList;
    }

    @Override
    public int getItemCount() {
        return mBannerList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(mBannerList.get(position).getImage());
    }
}
