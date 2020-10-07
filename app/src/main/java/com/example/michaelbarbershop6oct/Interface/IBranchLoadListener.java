package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Model.Salon;

import java.util.List;



public interface IBranchLoadListener {
    void onAllBranchLoadSuccess(List<Salon> areaNameList);
    void onAllBranchLoadFailed(String message);
}
