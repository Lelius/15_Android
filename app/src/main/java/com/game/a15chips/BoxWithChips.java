package com.game.a15chips;

import java.util.Date;
import java.util.Random;

public class BoxWithChips {
    protected static final int xBoxSize = 4;
    protected static final int yBoxSize = 4;
    private int[][] boxWithChips;

    public BoxWithChips() {
        boxWithChips = new int[xBoxSize][yBoxSize];
    }

    public boolean moveChip(int numberChip) {
        PairXY chipPlace = searchChipPlace(numberChip);
        PairXY emptyPlace = searchEmptyPlace();

        if (chipPlace.x < 0 | chipPlace.y < 0 | emptyPlace.x < 0 | emptyPlace.y < 0)
            return false;

        if (emptyPlace.y == chipPlace.y){
            if ((emptyPlace.x == chipPlace.x - 1) | (emptyPlace.x == chipPlace.x + 1)) {
                setChipPlace(numberChip, emptyPlace);
                setEmptyPlace(chipPlace);
                return true;
            }
        }
        if (emptyPlace.x == chipPlace.x){
            if ((emptyPlace.y == chipPlace.y - 1) | (emptyPlace.y == chipPlace.y + 1)) {
                setChipPlace(numberChip, emptyPlace);
                setEmptyPlace(chipPlace);
                return true;
            }
        }

        return false;
    }

    public int getChipPlace(PairXY placeChip) {
        return boxWithChips[placeChip.x][placeChip.y];
    }

    public boolean setChipPlace(int numberChip, PairXY placeChip) {
        if (placeChip.x >= 0 & placeChip.x < xBoxSize & placeChip.y >= 0 & placeChip.y < yBoxSize) {
            boxWithChips[placeChip.x][placeChip.y] = numberChip;
            return true;
        }
        return false;
    }

    public boolean setEmptyPlace(PairXY placeEmpty) {
        if (placeEmpty.x >= 0 & placeEmpty.x < xBoxSize & placeEmpty.y >= 0 & placeEmpty.y < yBoxSize) {
            boxWithChips[placeEmpty.x][placeEmpty.y] = 0;
            return true;
        }
        return false;
    }

    public PairXY searchChipPlace(int numberChip) {
        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                if (boxWithChips[i][j] == numberChip)
                    return new PairXY(i, j);
            }
        }
        return new PairXY(-1, -1);
    }

    public PairXY searchEmptyPlace() {
        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                if (boxWithChips[i][j] == 0)
                    return new PairXY(i, j);
            }
        }
        return new PairXY( -1, -1);
    }

    public void reloadRandomChips() {
        Random random = new Random();
        random.setSeed((new Date()).getTime());

        for (int j = 0; j < yBoxSize; j++) {
            for (int i = 0; i < xBoxSize; i++) {
                boxWithChips[i][j] = 0;
            }
        }

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

}
