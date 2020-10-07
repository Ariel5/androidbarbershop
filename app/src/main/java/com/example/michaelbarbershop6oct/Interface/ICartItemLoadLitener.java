package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Database.CartItem;

import java.util.List;


public interface ICartItemLoadLitener {
    void onGetAllItemFromCartSuccess(List<CartItem> cartItemList);
}
