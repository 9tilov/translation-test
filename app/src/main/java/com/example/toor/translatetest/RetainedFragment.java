package com.example.toor.translatetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class RetainedFragment extends Fragment {

    public static final String TAG = "RetainedFragment";

    @NonNull
    private Coords relativeCoords = new Coords(0f, 0f);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    public Coords getData() {
        return relativeCoords;
    }

    public void setData(@Nullable Coords relativeCoords) {
        if (relativeCoords != null) {
            this.relativeCoords = relativeCoords;
        }
    }
}
