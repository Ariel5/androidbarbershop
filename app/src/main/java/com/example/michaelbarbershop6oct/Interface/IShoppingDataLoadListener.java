package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Model.ShoppingItem;

import java.util.HashMap;
import java.util.List;



public interface IShoppingDataLoadListener {
    void onShoppingDataLoadSuccess(List<ShoppingItem> shoppingItemList, HashMap<ShoppingItem, Double> currentUserRecommendations);
    void onShoppingDataLoadFailed(String message);
}
