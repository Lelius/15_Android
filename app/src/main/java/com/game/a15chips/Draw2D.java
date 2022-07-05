package com.game.a15chips;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Draw2D extends View {

    private Paint mPaint = new Paint();

    private Bitmap box_Bitmap;
    private Bitmap reset_Bitmap;
    private Bitmap close_Bitmap;
    private Bitmap chip_1_Bitmap;
    private Bitmap chip_2_Bitmap;
    private Bitmap chip_3_Bitmap;
    private Bitmap chip_4_Bitmap;
    private Bitmap chip_5_Bitmap;
    private Bitmap chip_6_Bitmap;
    private Bitmap chip_7_Bitmap;
    private Bitmap chip_8_Bitmap;
    private Bitmap chip_9_Bitmap;
    private Bitmap chip_10_Bitmap;
    private Bitmap chip_11_Bitmap;
    private Bitmap chip_12_Bitmap;
    private Bitmap chip_13_Bitmap;
    private Bitmap chip_14_Bitmap;
    private Bitmap chip_15_Bitmap;
    private Bitmap wellWhite_Bitmap;
    private Bitmap wellBlack_Bitmap;

    private double xRatio;
    private double yRatio;

    public Draw2D(Context context) {
        super(context);

        Resources res = this.getResources();
        box_Bitmap = BitmapFactory.decodeResource(res, R.drawable.box);
        reset_Bitmap = BitmapFactory.decodeResource(res, R.drawable.reset);
        close_Bitmap = BitmapFactory.decodeResource(res, R.drawable.x2);
        chip_1_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip1);
        chip_2_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip2);
        chip_3_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip3);
        chip_4_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip4);
        chip_5_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip5);
        chip_6_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip6);
        chip_7_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip7);
        chip_8_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip8);
        chip_9_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip9);
        chip_10_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip10);
        chip_11_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip11);
        chip_12_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip12);
        chip_13_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip13);
        chip_14_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip14);
        chip_15_Bitmap = BitmapFactory.decodeResource(res, R.drawable.chip15);
        wellWhite_Bitmap = BitmapFactory.decodeResource(res, R.drawable.well_white_380x350);
        wellBlack_Bitmap = BitmapFactory.decodeResource(res, R.drawable.well_black_380x350);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        xRatio = 445 / (double)width;
        yRatio = 445 / (double)height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawPaint(mPaint);


    }
}
