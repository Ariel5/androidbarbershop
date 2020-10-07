package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Model.Banner;

import java.util.List;



public interface ILookbookLoadListener {
    void onLookbookLoadSuccess(List<Banner> banners);
    void onLookbookLoadFailed(String message);
}
