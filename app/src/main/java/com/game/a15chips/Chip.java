package com.game.a15chips;

import android.graphics.drawable.Drawable;

public class Chip {
    private int numberChip;
    private int x;
    private int y;
    private Drawable imageChip;

    public Chip(int numberChip, int x, int y, Drawable imageChip){
        this.numberChip = numberChip;
        this.x = x;
        this.y = y;
        this.imageChip = imageChip;
    }

    public Chip(int numberChip, int x, int y){
        this.numberChip = numberChip;
        this.x = x;
        this.y = y;
        this.imageChip = null;
    }

    public Chip(int numberChip){
        this.numberChip = numberChip;
        this.x = 0;
        this.y = 0;
        this.imageChip = null;
    }

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

    public Drawable getImageChip() {
        return imageChip;
    }

    public void setImageChip(Drawable imageChip) {
        this.imageChip = imageChip;
    }
}
