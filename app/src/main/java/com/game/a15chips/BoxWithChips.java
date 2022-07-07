package com.game.a15chips;

import java.util.Date;
import java.util.Random;

public class BoxWithChips {
    private static final int xBoxSize = 4;
    private static final int yBoxSize = 4;
    private int[][] boxWithChips;

    public BoxWithChips() {
        boxWithChips = new int[xBoxSize][yBoxSize];
    }

    public boolean moveChip(int numberChip) {

    }

    public Pair searchEmptyPlace() {
        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                if (boxWithChips[i][j] == 0)
                    return new Pair(i, j);
            }
        }
        return new Pair(xBoxSize - 1, yBoxSize - 1);
    }

    public void reloadRandomChips() {
        Random random = new Random();
        random.setSeed((new Date()).getTime());

        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                if (i == xBoxSize - 1 && j == yBoxSize - 1) {
                    boxWithChips[i][j] = 0;                         //Последняя ячейка пустая
                    return;
                }
                int nextChip;
                do {
                    nextChip = random.nextInt(15) + 1;
                } while (isChipInBox(nextChip));
                boxWithChips[i][j] = nextChip;
            }
        }
    }

    public boolean isChipInBox(int numberChip){
        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                if (numberChip == boxWithChips[i][j])
                    return true;
            }
        }
        return false;
    }

    private class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
