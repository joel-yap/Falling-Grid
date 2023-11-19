package com.example.a159336assignment3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Targets {
    private ArrayList<Target> mTargetList;
    private Pickup mPickup;
    private boolean pickupReady = false;
    private int pickupSide;
    private Player player;
    private Random mRand = new Random();
    private int nTargets = 5;
    private Paint paint;
    private int mWidth, mHeight;

    Targets(int width, int height) {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        mWidth = width;
        mHeight = height;
        mTargetList = new ArrayList<>(nTargets);
        //targetsRemaining = 10;
        //add one example first, for loop array later
        //First 5 horizontal targets
        mTargetList.add(new Target((mWidth/10), (mHeight/10), (mWidth/20), width, height, 1));
        mTargetList.add(new Target(((mWidth/10) + (mWidth/5)), (mHeight/10), (mWidth/20), width, height, 2));
        mTargetList.add(new Target(((mWidth/10) + 2 * (mWidth/5)), (mHeight/10), (mWidth/20), width, height, 3));
        mTargetList.add(new Target(((mWidth/10) + 3 * (mWidth/5)), (mHeight/10), (mWidth/20), width, height, 4));
        mTargetList.add(new Target(((mWidth/10) + 4 * (mWidth/5)), (mHeight/10), (mWidth/20), width, height, 5));
        //Second row
        mTargetList.add(new Target((mWidth/10), (2 * (mHeight/10)), (mWidth/20), width, height, 6));
        mTargetList.add(new Target(((mWidth/10) + (mWidth/5)), (2 * (mHeight/10)), (mWidth/20), width, height, 7));
        mTargetList.add(new Target(((mWidth/10) + 2 * (mWidth/5)), (2 * (mHeight/10)), (mWidth/20), width, height, 8));
        mTargetList.add(new Target(((mWidth/10) + 3 * (mWidth/5)), (2 * (mHeight/10)), (mWidth/20), width, height, 9));
        mTargetList.add(new Target(((mWidth/10) + 4 * (mWidth/5)), (2 * (mHeight/10)), (mWidth/20), width, height, 10));
        //Third row
        /*mTargetList.add(new Target((mWidth/10), (3 * (mHeight/10)), (mWidth/20)));
        mTargetList.add(new Target(((mWidth/10) + (mWidth/5)), (3 * (mHeight/10)), (mWidth/20)));
        mTargetList.add(new Target(((mWidth/10) + 2 * (mWidth/5)), (3 * (mHeight/10)), (mWidth/20)));
        mTargetList.add(new Target(((mWidth/10) + 3 * (mWidth/5)), (3 * (mHeight/10)), (mWidth/20)));
        mTargetList.add(new Target(((mWidth/10) + 4 * (mWidth/5)), (3 * (mHeight/10)), (mWidth/20)));*/
        //Pickup row
        int r = mRand.nextInt(2);
        if (r == 0) {
            mPickup = new Pickup(((mWidth/10) + (mWidth/5)), (5 * (mHeight/10)), (mWidth/20),width, height, 1);
            pickupSide = 1;
            Log.d("Pickup", "Pickup 1");
        } else {
            mPickup = new Pickup(((mWidth/10) + 3 * (mWidth/5)), (5 * (mHeight/10)), (mWidth/20),width,height, 2);
            pickupSide = 2;
            Log.d("Pickup", "Pickup 2");
        }
        pickupReady = true;
        player = new Player(((mWidth/10) + 2 * (mWidth/5)), (7 * (mHeight/10)), (mWidth/20), width, height);
    }

    public void draw(Canvas c) {
        for (Target T: mTargetList) {
            T.draw(c);
        }
        if (pickupReady) {
            mPickup.draw(c);
        }
        if (player != null) {
            player.draw(c);
        }

        c.drawLine(10,10,(mWidth - 10),10,paint);
        c.drawLine(10,10,10,(8 * (mHeight/10)),paint);
        c.drawLine(10,(8 * (mHeight/10)),(mWidth - 10),(8 * (mHeight/10)),paint);
        c.drawLine((mWidth - 10),10,(mWidth - 10),(8 * (mHeight/10)),paint);
    }

    //add update function later, first prototype just needs to draw once.

    public void movePlayer(int x, int y) {
        player.move(x, y);
    }

    public void removeTarget(int index) {
        for (int i=0; i<mTargetList.size(); i++) {
            if (mTargetList.get(i).getIndex() == index) {
                mTargetList.remove(i);
                break;
            }
        }
    }
    public void removePlayer() {
        player = null;
    }

    public void removePickup() {
        mPickup = null;
        pickupReady = false;
    }
    public int getPickupSide() {
        return pickupSide;
    }

    public void moveTarget(int r, int x, int y) {
        //mTargetList.get(r).move(x,y);
        for (int i=0; i<mTargetList.size(); i++) {
            if (mTargetList.get(i).getIndex() == r) {
                mTargetList.get(i).move(x,y);
                //return true;
            }
        }
        //return false;
    }
}
