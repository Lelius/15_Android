package com.leliuslogunov.a15chips;

public class PairXY {
    int x;
    int y;

    public PairXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PairXY(PairXY val) {
        this.x = val.x;
        this.y = val.y;
    }
}
