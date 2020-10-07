package com.example.michaelbarbershop6oct.Interface;


import com.example.michaelbarbershop6oct.Model.Banner;

import java.util.List;

public interface IBannerLoadListener {
    void onBannerLoadSuccess(List<Banner> banners);
    void onBannerLoadFailed(String message);
}
