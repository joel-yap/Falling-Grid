package com.example.a159336assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Pickup {
    Paint mPaint;
    protected float mX,mY,mR;
    protected int mWidth, mHeight, mIndex;
    public void draw(Canvas c) {
        c.save();
        c.drawCircle(mX,mY,mR,mPaint);
    }
    Pickup(int x,int y, int r, int width, int height, int index) {
        mX = x;
        mY = y;
        mR = r;
        mWidth = width;
        mHeight = height;
        mIndex = index;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(8);
        mPaint.setTextSize(64);
        mPaint.setAntiAlias(true);
    }
    public int getIndex() {
        return mIndex;
    }
}
