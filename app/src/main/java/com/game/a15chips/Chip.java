package com.game.a15chips;

import android.graphics.Bitmap;

public class Chip {
    private int numberChip;
    private int x;
    private int y;
    private Bitmap bitmapChip;
    private boolean isShowBitmapChip;

    public Chip(int numberChip, int x, int y, Bitmap bitmapChip){
        this.numberChip = numberChip;
        this.x = x;
        this.y = y;
        this.bitmapChip = bitmapChip;
        this.isShowBitmapChip = true;
    }

    public Chip(int numberChip, Bitmap bitmapChip){
        this.numberChip = numberChip;
        this.x = 0;
        this.y = 0;
        this.bitmapChip = bitmapChip;
        this.isShowBitmapChip = true;
    }

    public boolean isShowBitmapChip() { return isShowBitmapChip; }

    public void onShow() { isShowBitmapChip = true; }

    public void onHide() { isShowBitmapChip = false; }

    public int getNumberChip() {
        return numberChip;
    }

    public void setNumberChip(int numberChip) {
        this.numberChip = numberChip;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmapChip() { return bitmapChip; }

    public void setBitmapChip(Bitmap bitmapChip) {
        this.bitmapChip = bitmapChip;
    }
}
