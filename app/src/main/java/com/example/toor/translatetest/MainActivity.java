package com.example.toor.translatetest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView btnView;

    private static final int RADIUS = 150;
    private static final long DURATION = 1000;
    private boolean isAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = findViewById(R.id.iv_car);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                if (!isAnimation) {
                    translate(btnView, event.getX() - btnView.getWidth() / 2, event.getY() - 2 * btnView.getHeight());
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private void translate(View view, float toX, float toY) {
        Path path = new Path();
        path.moveTo(view.getX(), view.getY());
        path.cubicTo(view.getX(), view.getY(),
                view.getX() + RADIUS, view.getY() + RADIUS,
                toX, toY);
        ObjectAnimator objectAnimator =
                ObjectAnimator.ofFloat(view, View.X, View.Y, path);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimation = false;
            }
        });
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setDuration(DURATION);
        objectAnimator.start();
    }
}
