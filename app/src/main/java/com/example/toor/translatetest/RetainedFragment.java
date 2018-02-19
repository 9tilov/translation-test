package com.example.toor.translatetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class RetainedFragment extends Fragment {

    public static final String TAG = "RetainedFragment";

    @NonNull
    private CarPosition relativeCarPosition = new CarPosition(0f, 0f, 0f);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    public CarPosition getData() {
        return relativeCarPosition;
    }

    public void setData(@Nullable CarPosition relativeCarPosition) {
        if (relativeCarPosition != null) {
            this.relativeCarPosition = relativeCarPosition;
        }
    }
}
