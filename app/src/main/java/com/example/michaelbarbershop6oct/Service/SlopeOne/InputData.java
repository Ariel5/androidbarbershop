package com.example.michaelbarbershop6oct.Service.SlopeOne;

import com.example.michaelbarbershop6oct.Model.ShoppingItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputData {

    protected static List<ShoppingItem> items;

    InputData(List<ShoppingItem> shoppingItems) {
        items = shoppingItems;
    }

    public Map<User, HashMap<ShoppingItem, Double>> initializeData(int numberOfUsers) {
        Map<User, HashMap<ShoppingItem, Double>> data = new HashMap<>();
        HashMap<ShoppingItem, Double> newUser;
        Set<ShoppingItem> newRecommendationSet;
        for (int i = 0; i < numberOfUsers; i++) {
            newUser = new HashMap<ShoppingItem, Double>();
            newRecommendationSet = new HashSet<>();
            for (int j = 0; j < 20; j++) {
                newRecommendationSet.add(items.get((int) (Math.random() * 28)));
            }
            for (ShoppingItem item : newRecommendationSet) {
                newUser.put(item, Math.random());
            }
            data.put(new User("User " + i), newUser);
        }
        return data;
    }
}
