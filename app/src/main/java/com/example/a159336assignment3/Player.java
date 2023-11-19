package com.example.a159336assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Player {
    Paint mPaint;
    protected float mX,mY,mR;
    protected int mWidth, mHeight;
    public void draw(Canvas c) {
        c.save();
        c.drawCircle(mX,mY,mR,mPaint);
    }
    Player(int x,int y, int r, int width, int height) {
        mX = x;
        mY = y;
        mR = r;
        mWidth = width;
        mHeight = height;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(8);
        mPaint.setTextSize(64);
        mPaint.setAntiAlias(true);
    }
    //can add move() method later, first get prototype frame 1 drawn
    //move function changes internal location variables
    public void move(int x, int y) {
        if (x != 0) {
            mX += x;
            //out of canvas bounds check and revert
            if (mX > mWidth || mX < 0) {
                mX -= x;
            }
        } else if (y != 0) {
            mY += y;
            //out of canvas bounds check and revert
            if (mY >= (8 * (mHeight/10)) || mY < 10) {
                mY -= y;
            }
            //Log.d("newY", String.valueOf(mY));
        }
        //Log.d("BUTTONS", String.valueOf(y));
    }
}
