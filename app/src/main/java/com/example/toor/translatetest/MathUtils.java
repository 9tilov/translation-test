package com.example.toor.translatetest;

public class MathUtils {

    private static final int CIRCLE_DEGREES = 360;

    private MathUtils() {
        throw new IllegalStateException("This is utility class");
    }

    public static float getRotationAngel(float fromX, float fromY, float toX, float toY) {
        float x1 = fromX - toX;
        float y1 = fromY - toY;
        double angleRad = Math.atan2(x1, y1);

        return (float) ((CIRCLE_DEGREES - Math.toDegrees(angleRad)) % CIRCLE_DEGREES);
    }
}
