package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Model.ShoppingItem;

import java.util.List;



public interface IShoppingDataLoadListener {
    void onShoppingDataLoadSuccess(List<ShoppingItem> shoppingItemList, boolean isAllItems);
    void onShoppingDataLoadFailed(String message);
}
