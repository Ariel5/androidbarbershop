package com.example.michaelbarbershop6oct.Model.EventBus;


import com.example.michaelbarbershop6oct.Model.Barber;

import java.util.List;

public class BarberDoneEvent {

    private List<Barber> mBarberList;

    public BarberDoneEvent(List<Barber> barberList) {
        mBarberList = barberList;
    }

    public List<Barber> getBarberList() {
        return mBarberList;
    }

    public void setBarberList(List<Barber> barberList) {
        mBarberList = barberList;
    }
}
