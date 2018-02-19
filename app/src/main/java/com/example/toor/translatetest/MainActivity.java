package com.example.toor.translatetest;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import static com.example.toor.translatetest.AnimationUtils.translate;

public class MainActivity extends AppCompatActivity {

    private View btnView;
    private int screenWidth;
    private int screenHeight;
    @NonNull
    private CarPosition carLastCarPosition = new CarPosition(0f, 0f, 0f);
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

    private void initView(@NonNull CarPosition carPosition) {
        carLastCarPosition = getAbsoluteCoords(carPosition);
        btnView.setX(carLastCarPosition.getX());
        btnView.setY(carLastCarPosition.getY());
        btnView.setRotation(carLastCarPosition.getAngel());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                float newX = event.getX() - btnView.getWidth() / 2;
                float newY = event.getY() - btnView.getHeight() / 2;
                float angel = MathUtils.getRotationAngel(btnView.getX(), btnView.getY(), newX, newY);
                CarPosition newCarPosition = new CarPosition(newX, newY, angel);
                translate(btnView, carLastCarPosition, newCarPosition);
                this.carLastCarPosition = newCarPosition;
                return true;
            default:
                return true;
        }
    }

    @NonNull
    private CarPosition getRelativeCoords() {
        return new CarPosition(carLastCarPosition.getX() / screenWidth, carLastCarPosition.getY() / screenHeight, carLastCarPosition.getAngel());
    }

    private CarPosition getAbsoluteCoords(@NonNull CarPosition carPosition) {
        return new CarPosition(carPosition.getX() * screenWidth, carPosition.getY() * screenHeight, carPosition.getAngel());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataFragment != null) {
            dataFragment.setData(getRelativeCoords());
        }
    }
}
