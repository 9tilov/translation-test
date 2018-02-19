package com.example.toor.translatetest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimationUtils {

    private static final int RADIUS = 150;
    private static final long TRANSLATION = 2000L;
    private static final long DURATION = 1000L;
    private static boolean isAnimation = false;

    private AnimationUtils() {
        throw new IllegalStateException("This is utility class");
    }

    public static void translate(View view, CarPosition oldPosition, CarPosition carPosition) {
        if (isAnimation) {
            return;
        }

        ObjectAnimator rotation = ObjectAnimator.ofFloat(view,
                "rotation", oldPosition.getAngel(), carPosition.getAngel());
        rotation.setDuration(DURATION);
        Path path = new Path();
        path.moveTo(view.getX(), view.getY());
        path.cubicTo(view.getX(), view.getY(),
                view.getX() + RADIUS, view.getY() + RADIUS,
                carPosition.getX(), carPosition.getY());
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
        objectAnimator.setDuration(TRANSLATION);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(objectAnimator).with(rotation);
        animationSet.start();
    }
}