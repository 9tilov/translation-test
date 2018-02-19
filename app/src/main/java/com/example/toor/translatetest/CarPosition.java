package com.example.toor.translatetest;

public class CarPosition {

    private final float x;
    private final float y;
    private final float angel;

    public CarPosition(final float x, final float y, float angel) {
        this.x = x;
        this.y = y;
        this.angel = angel;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngel() {
        return angel;
    }
}
