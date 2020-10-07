package com.example.michaelbarbershop6oct.Model.EventBus;

public class UnableNextButton {
    private boolean isUnable;

    public UnableNextButton(boolean isUnable) {
        this.isUnable = isUnable;
    }

    public boolean isUnable() {
        return isUnable;
    }

    public void setUnable(boolean unable) {
        isUnable = unable;
    }
}
