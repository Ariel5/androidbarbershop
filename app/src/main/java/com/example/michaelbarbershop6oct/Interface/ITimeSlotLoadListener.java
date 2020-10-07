package com.example.michaelbarbershop6oct.Interface;

import com.example.michaelbarbershop6oct.Model.TimeSlot;

import java.util.List;



public interface ITimeSlotLoadListener  {
    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
