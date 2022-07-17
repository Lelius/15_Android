package com.game.a15chips;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public class Draw2D extends View {

    private BoxWithChips boxWithChips;
    private Paint mPaint;

    final private Bitmap box_Bitmap;
    final private Bitmap reset_Bitmap;
    final private Bitmap close_Bitmap;
    final private Bitmap wellWhite_Bitmap;
    final private Bitmap wellBlack_Bitmap;

    private ArrayList<Chip> chips;

    final private double xyRatio;
    final private int width;
    final private int height;
    final private int xChip;
    final private int xBox;
    final private int yBox;
    final private int xReset;
    final private int yReset;
    final private int xClose;
    final private int yClose;
    final private int xWell;
    final private int yWell;
    final private int interval;
    final private int boxWallThickness;

    private int xZero;
    private int yZero;

    enum WhiteOrBlackWin { WHITE, BLACK };
    private WhiteOrBlackWin whiteOrBlackWinImage;
    private boolean countDownTimerStatus;

    public Draw2D(Context context) {
        super(context);

        this.chips = new ArrayList<>();
        this.boxWithChips = new BoxWithChips();
        this.mPaint = new Paint();

        boxWithChips.reloadRandomChips();
        whiteOrBlackWinImage = WhiteOrBlackWin.WHITE;
        //countDownTimerStatus обеспечивает уникальность таймера, отсекает двойников
        countDownTimerStatus = false;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        xBox = Math.min(width, height);
        yBox = xBox;
        xyRatio = 445.0 / (double) xBox;
        xChip = (int) (100.0 / xyRatio);
        xReset = (int) (332.0 / xyRatio);
        yReset = (int) (50.0 / xyRatio);
        xClose = (int) (112.0 / xyRatio);
        yClose = (int) (50.0 / xyRatio);
        xWell = (int) (380.0 / xyRatio);    //35
        yWell = (int) (350.0 / xyRatio);    //45
        interval = (int) (5.0 / xyRatio);
        boxWallThickness = (int) (10.0 / xyRatio);

        xZero = 0;
        MainActivity activity = (MainActivity) getContext();
        int heightActionBar = activity.getSupportActionBar().getHeight();
        yZero = (height - heightActionBar - (yBox + yReset)) / 2;

        Resources res = this.getResources();
        box_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.box), xBox, yBox, false);
        reset_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.reset), xReset, yReset, false);
        close_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.x3), xClose, yClose, false);
        wellWhite_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.well_white_380x350), xWell, yWell, false);
        wellBlack_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.well_black_380x350), xWell, yWell, false);

        chips.add(new Chip(1, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip1), xChip, xChip, false)));
        chips.add(new Chip(2, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip2), xChip, xChip, false)));
        chips.add(new Chip(3, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip3), xChip, xChip, false)));
        chips.add(new Chip(4, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip4), xChip, xChip, false)));
        chips.add(new Chip(5, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip5), xChip, xChip, false)));
        chips.add(new Chip(6, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip6), xChip, xChip, false)));
        chips.add(new Chip(7, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip7), xChip, xChip, false)));
        chips.add(new Chip(8, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip8), xChip, xChip, false)));
        chips.add(new Chip(9, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip9), xChip, xChip, false)));
        chips.add(new Chip(10, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip10), xChip, xChip, false)));
        chips.add(new Chip(11, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip11), xChip, xChip, false)));
        chips.add(new Chip(12, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip12), xChip, xChip, false)));
        chips.add(new Chip(13, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip13), xChip, xChip, false)));
        chips.add(new Chip(14, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip14), xChip, xChip, false)));
        chips.add(new Chip(15, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip15), xChip, xChip, false)));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        MainActivity activity = (MainActivity) getContext();
        int heightActionBar = activity.getSupportActionBar().getHeight();
        yZero = (height - (yBox + yReset + heightActionBar)) / 2;

        if(!boxWithChips.isOrderWin()) {
            onDrawStaticObjects(canvas);
            getCoordinateChips();
            for (Chip chip : chips) {
                canvas.drawBitmap(chip.getBitmapChip(), (float) chip.getX(), (float) chip.getY(), null);
            }
        }
        else {
            onDrawStaticObjects(canvas);
            onDrawWin(canvas);
            if (countDownTimerStatus == false) {
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        if (whiteOrBlackWinImage == WhiteOrBlackWin.BLACK)
                            whiteOrBlackWinImage = WhiteOrBlackWin.WHITE;
                        else
                            whiteOrBlackWinImage = WhiteOrBlackWin.BLACK;
                        countDownTimerStatus = false;
                        invalidate();
                    }
                }.start();
            }
            countDownTimerStatus = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //reload
                if (event.getX() >= xZero & event.getX() <= xZero + xReset) {
                    if (event.getY() > yZero + yBox & event.getY() <= yZero + yBox + yReset ) {
                        boxWithChips.reloadRandomChips();
                        invalidate();
                        countDownTimerStatus = false;
                    }
                }
                //close
                if (event.getX() > xZero + xReset & event.getX() <= xZero + xReset + xClose) {
                    if (event.getY() > yZero + yBox & event.getY() <= yZero + yBox + yReset ) {
                        MainActivity activity = (MainActivity) getContext();
                        activity.finishAffinity();
                    }
                }
                //chips
                for (int j = 0; j < BoxWithChips.yBoxSize; j++){
                    for (int i = 0; i < BoxWithChips.xBoxSize; i++){
                        if (event.getX() >= (xZero + boxWallThickness + interval + (i * (xChip + interval)))
                                & event.getX() < (xZero + boxWallThickness + interval + xChip + (i * (xChip + interval)))
                                & event.getY() >= (yZero + boxWallThickness + interval + (j * (xChip + interval)))
                                & event.getY() < (yZero + boxWallThickness + interval + xChip + (j * (xChip + interval)))){
                            boxWithChips.moveChip(boxWithChips.getChipPlace(new PairXY(i, j)));
                            invalidate();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    protected void onDrawStaticObjects(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawPaint(mPaint);

        canvas.drawBitmap(box_Bitmap, xZero, yZero, null);
        canvas.drawBitmap(reset_Bitmap, xZero, yZero + yBox + 1, null);
        canvas.drawBitmap(close_Bitmap, xZero + xReset + 1, yZero + yBox + 1, null);
    }

    public void getCoordinateChips() {
        for (Chip chip : chips){
            PairXY coordInBox = boxWithChips.searchChipPlace(chip.getNumberChip());
            chip.setX((int)(xZero + boxWallThickness + interval + (coordInBox.x * (xChip + interval))));
            chip.setY((int)(yZero + boxWallThickness + interval + (coordInBox.y * (xChip + interval))));
        }
    }

    public void onDrawWin(Canvas canvas){
        if (whiteOrBlackWinImage == WhiteOrBlackWin.WHITE)
            canvas.drawBitmap(wellWhite_Bitmap, (int) (xZero + (35.0 / xyRatio)), (int) (yZero + (45.0 / xyRatio)), null);
        else if (whiteOrBlackWinImage == WhiteOrBlackWin.BLACK)
            canvas.drawBitmap(wellBlack_Bitmap, (int) (xZero + (35.0 / xyRatio)), (int) (yZero + (45.0 / xyRatio)), null);
        else
            throw new IllegalArgumentException("Value does not coincide with possible options.");
    }
}