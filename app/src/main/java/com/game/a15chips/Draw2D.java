package com.game.a15chips;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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
    /*final private Bitmap chip_1_Bitmap;
    final private Bitmap chip_2_Bitmap;
    final private Bitmap chip_3_Bitmap;
    final private Bitmap chip_4_Bitmap;
    final private Bitmap chip_5_Bitmap;
    final private Bitmap chip_6_Bitmap;
    final private Bitmap chip_7_Bitmap;
    final private Bitmap chip_8_Bitmap;
    final private Bitmap chip_9_Bitmap;
    final private Bitmap chip_10_Bitmap;
    final private Bitmap chip_11_Bitmap;
    final private Bitmap chip_12_Bitmap;
    final private Bitmap chip_13_Bitmap;
    final private Bitmap chip_14_Bitmap;
    final private Bitmap chip_15_Bitmap;*/
    final private Bitmap wellWhite_Bitmap;
    final private Bitmap wellBlack_Bitmap;

    /*final private Chip chip_1;
    final private Chip chip_2;
    final private Chip chip_3;
    final private Chip chip_4;
    final private Chip chip_5;
    final private Chip chip_6;
    final private Chip chip_7;
    final private Chip chip_8;
    final private Chip chip_9;
    final private Chip chip_10;
    final private Chip chip_11;
    final private Chip chip_12;
    final private Chip chip_13;
    final private Chip chip_14;
    final private Chip chip_15;*/

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

    private float xChip_1_Drawable;
    private float yChip_1_Drawable;

    public Draw2D(Context context) {
        super(context);

        this.chips = new ArrayList<>();
        this.boxWithChips = new BoxWithChips();
        this.mPaint = new Paint();

        boxWithChips.reloadRandomChips();

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
        xWell = (int) (380.0 / xyRatio);
        yWell = (int) (350.0 / xyRatio);
        interval = (int) (5.0 / xyRatio);
        boxWallThickness = (int) (10.0 / xyRatio);

        xZero = 0;
        MainActivity activity = (MainActivity) getContext();
        int heightActionBar = activity.getSupportActionBar().getHeight();
        yZero = (height - (yBox + yReset + heightActionBar)) / 2;

        Resources res = this.getResources();
        box_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.box), xBox, yBox, false);
        reset_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.reset), xReset, yReset, false);
        close_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.x3), xClose, yClose, false);
        /*chip_1_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip1), xChip, xChip, false);
        chip_2_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip2), xChip, xChip, false);
        chip_3_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip3), xChip, xChip, false);
        chip_4_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip4), xChip, xChip, false);
        chip_5_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip5), xChip, xChip, false);
        chip_6_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip6), xChip, xChip, false);
        chip_7_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip7), xChip, xChip, false);
        chip_8_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip8), xChip, xChip, false);
        chip_9_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip9), xChip, xChip, false);
        chip_10_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip10), xChip, xChip, false);
        chip_11_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip11), xChip, xChip, false);
        chip_12_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip12), xChip, xChip, false);
        chip_13_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip13), xChip, xChip, false);
        chip_14_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip14), xChip, xChip, false);
        chip_15_Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.chip15), xChip, xChip, false);*/
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

        onDrawStaticObjects(canvas);
        //canvas.save();
        //canvas.drawBitmap(chip_1_Bitmap, xChip_1_Drawable, yChip_1_Drawable, null);
        //canvas.restore();
        getCoordinateChips();
        for (Chip chip: chips){
            canvas.drawBitmap(chip.getBitmapChip(), (float) chip.getX(), (float) chip.getY(), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() >= xZero & event.getX() <= xZero + xReset) {
                    if (event.getY() > yZero + yBox & event.getY() <= yZero + yBox + yReset ) {
                        boxWithChips.reloadRandomChips();
                        invalidate();
                    }
                }
                if (event.getX() > xZero + xReset & event.getX() <= xZero + xReset + xClose) {
                    if (event.getY() > yZero + yBox & event.getY() <= yZero + yBox + yReset ) {
                        MainActivity activity = (MainActivity) getContext();
                        activity.finishAffinity();
                    }
                }
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
}