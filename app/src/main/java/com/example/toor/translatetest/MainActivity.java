package com.example.toor.translatetest;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import static com.example.toor.translatetest.AnimationUtils.translate;

public class MainActivity extends AppCompatActivity {

    ImageView btnView;
    private int screenWidth;
    private int screenHeight;
    @NonNull
    private Coords carLastCoords = new Coords(0f, 0f);
    @Nullable
    private RetainedFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = findViewById(R.id.iv_car);
        getScreenSize();

        FragmentManager fm = getSupportFragmentManager();
        dataFragment = (RetainedFragment) fm.findFragmentByTag(RetainedFragment.TAG);
        if (dataFragment == null) {
            dataFragment = new RetainedFragment();
            fm.beginTransaction().add(dataFragment, RetainedFragment.TAG).commit();
            dataFragment.setData(getRelativeCoords());
        } else {
            initView(dataFragment.getData());
        }

    }

    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.screenWidth = size.x;
        this.screenHeight = size.y;
    }

    private void initView(@NonNull Coords coords) {
        carLastCoords = getAbsoluteCoords(coords);
        btnView.setX(carLastCoords.getX());
        btnView.setY(carLastCoords.getY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                Coords newCoords = new Coords(event.getX() - btnView.getWidth() / 2, event.getY() - btnView.getHeight());
                this.carLastCoords = newCoords;
                translate(btnView, newCoords);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataFragment != null) {
            dataFragment.setData(getRelativeCoords());
        }
    }

    @NonNull
    private Coords getRelativeCoords() {
        return new Coords(carLastCoords.getX() / screenWidth, carLastCoords.getY() / screenHeight);
    }

    private Coords getAbsoluteCoords(@NonNull Coords coords) {
        return new Coords(coords.getX() * screenWidth, coords.getY() * screenHeight);
    }
}
