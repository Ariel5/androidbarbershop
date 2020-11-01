package com.example.michaelbarbershop6oct.Service.SlopeOne;

import com.example.michaelbarbershop6oct.Model.ShoppingItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Slope One algorithm implementation
 */
public class SlopeOne {

    private static Map<ShoppingItem, Map<ShoppingItem, Double>> diff = new HashMap<>();
    private static Map<ShoppingItem, Map<ShoppingItem, Integer>> freq = new HashMap<>();
    private static Map<User, HashMap<ShoppingItem, Double>> inputData;
    private static Map<User, HashMap<ShoppingItem, Double>> outputData = new HashMap<>();

    public static void slopeOne(int numberOfUsers, List<ShoppingItem> shoppingItems) {
        inputData = new InputData(shoppingItems).initializeData(numberOfUsers);
        System.out.println("Slope One - Before the Prediction\n");
        buildDifferencesMatrix(inputData);
        System.out.println("\nSlope One - With Predictions\n");
        predict(inputData);
    }

    /**
     * Based on the available data, calculate the relationships between the
     * ShoppingItems and number of occurences
     * 
     * @param data
     *            existing user data and their ShoppingItems' ratings
     */
    private static void buildDifferencesMatrix(Map<User, HashMap<ShoppingItem, Double>> data) {
        for (HashMap<ShoppingItem, Double> user : data.values()) {
            for (Entry<ShoppingItem, Double> e : user.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<ShoppingItem, Double>());
                    freq.put(e.getKey(), new HashMap<ShoppingItem, Integer>());
                }
                for (Entry<ShoppingItem, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (ShoppingItem j : diff.keySet()) {
            for (ShoppingItem i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i).doubleValue();
                int count = freq.get(j).get(i).intValue();
                diff.get(j).put(i, oldValue / count);
            }
        }
        printData(data);
    }

    /**
     * Based on existing data predict all missing ratings. If prediction is not
     * possible, the value will be equal to -1
     * 
     * @param data
     *            existing user data and their ShoppingItems' ratings
     */
    private static void predict(Map<User, HashMap<ShoppingItem, Double>> data) {
        HashMap<ShoppingItem, Double> uPred = new HashMap<ShoppingItem, Double>();
        HashMap<ShoppingItem, Integer> uFreq = new HashMap<ShoppingItem, Integer>();
        for (ShoppingItem j : diff.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Entry<User, HashMap<ShoppingItem, Double>> inputDataItem : data.entrySet()) {
            for (ShoppingItem j : inputDataItem.getValue().keySet()) {
                for (ShoppingItem k : diff.keySet()) {
                    try {
                        double predictedValue = diff.get(k).get(j).doubleValue() + inputDataItem.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * freq.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + freq.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }
            HashMap<ShoppingItem, Double> clean = new HashMap<ShoppingItem, Double>();
            for (ShoppingItem j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
                }
            }
            for (ShoppingItem j : InputData.items) {
                if (inputDataItem.getValue().containsKey(j)) {
                    clean.put(j, inputDataItem.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0);
                }
            }
            outputData.put(inputDataItem.getKey(), clean);
        }
        printData(outputData);
    }

    private static void printData(Map<User, HashMap<ShoppingItem, Double>> data) {
        for (User user : data.keySet()) {
            System.out.println(user.getUsername() + ":");
            print(data.get(user));
        }
    }

    private static void print(HashMap<ShoppingItem, Double> hashMap) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        for (ShoppingItem j : hashMap.keySet()) {
            System.out.println(" " + j.getName() + " --> " + formatter.format(hashMap.get(j).doubleValue()));
        }
    }

}
