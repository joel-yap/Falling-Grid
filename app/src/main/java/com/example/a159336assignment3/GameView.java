package com.example.a159336assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameView extends View {
    private Targets mTargets;
    private Player player;
    private Context context;
    private int score = 0;
    private boolean isActive;
    private static int NTargets=10;
    private int targetsRemaining = 10;
    private int ammoCounter = 0;
    //In the Status Array, T is a Target, n is an empty space, P is the Player.
    private String[][] statusArray = {{"T1","T2","T3","T4","T5"},{"T6","T7","T8","T9","T10"},{"n","n","n","n","n"},{"n","n","n","n","n"},{"n","n","n","n","n"},{"n","n","n","n","n"},{"n","n","P","n","n"},};


    public GameView(Context context, AttributeSet attrs) {super(context, attrs);
    this.context = context;}
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            mTargets= new Targets(getWidth(),getHeight());
            targetsRemaining = 10;
            isActive = true;
            //Initialise random starting Pickup location, one of 2 positions.
            if (mTargets.getPickupSide() == 1) {
                statusArray[4][1] = "A";
                //Log.d("Pickup", "Set Pickup 1");
            } else if (mTargets.getPickupSide() == 2) {
                statusArray[4][3] = "A";
                //Log.d("Pickup", "Set Pickup 2");
            }
        }
    }
    @Override
    protected void onDraw (Canvas c){
        if(mTargets!=null)
            mTargets.draw(c);
    }
    protected void movePlayerUp() {
        mTargets.movePlayer(0, (-getHeight()/10));
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    statusArray[i][j] = "n";
                    if ((i - 1) >= 0) {
                        //check Target, if moving into a Target remove it and increase score
                        if (statusArray[i - 1][j].contains("T")) {
                            //Log.d("Move","Hit Target!");
                            targetsRemaining -= 1;
                            //Log.d("Move",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i - 1][j].substring(1));
                            //Log.d("Move",("Target Index " + targetIndex));
                            mTargets.removeTarget(targetIndex);
                            score += 1;
                        }
                        if (statusArray[i - 1][j].contains("A")) {
                            //Log.d("Move","Collected Pickup!");
                            mTargets.removePickup();
                            ammoCounter = 4;
                        }
                        statusArray[i - 1][j] = "P";
                    } else {
                        statusArray[i][j] = "P";
                    }
                    break breakLabel;
                }
            }
        }
    }
    protected void movePlayerDown() {
        mTargets.movePlayer(0, (getHeight()/10));
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    statusArray[i][j] = "n";
                    if ((i + 1) < 7) {
                        if (statusArray[i + 1][j].contains("T")) {
                            Log.d("Move","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Move",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i + 1][j].substring(1));
                            Log.d("Move",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            score += 1;
                        }
                        if (statusArray[i + 1][j].contains("A")) {
                            Log.d("Move","Collected Pickup!");
                            mTargets.removePickup();
                            ammoCounter = 4;
                        }
                        statusArray[i + 1][j] = "P";
                        //Log.d("Move",("Final Player Location " + (i + 1) + " " + j));
                    } else {
                        statusArray[i][j] = "P";
                        //Log.d("Move",("Final Player Location " + i + " " + j));
                    }
                    break breakLabel;
                }
            }
        }
    }
    protected void movePlayerLeft() {
        mTargets.movePlayer(-(getWidth()/5), 0);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    statusArray[i][j] = "n";
                    if ((j - 1) >= 0) {
                        if (statusArray[i][j - 1].contains("T")) {
                            Log.d("Move","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Move",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i][j - 1].substring(1));
                            Log.d("Move",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            score += 1;
                        }
                        if (statusArray[i][j - 1].contains("A")) {
                            Log.d("Move","Collected Pickup!");
                            mTargets.removePickup();
                            ammoCounter = 4;
                        }
                        statusArray[i][j - 1] = "P";
                        //Log.d("Move",("Final Player Location " + i + " " + (j - 1)));
                    } else {
                        statusArray[i][j] = "P";
                        //Log.d("Move",("Final Player Location " + i + " " + j));
                    }
                    break breakLabel;
                }
            }
        }
    }
    protected void movePlayerRight() {
        mTargets.movePlayer((getWidth()/5), 0);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    statusArray[i][j] = "n";
                    if ((j + 1) < 5) {
                        if (statusArray[i][j + 1].contains("T")) {
                            Log.d("Move","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Move",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i][j + 1].substring(1));
                            Log.d("Move",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            score += 1;
                        }
                        if (statusArray[i][j + 1].contains("A")) {
                            Log.d("Move","Collected Pickup!");
                            mTargets.removePickup();
                            ammoCounter = 4;
                        }
                        statusArray[i][j + 1] = "P";
                        //Log.d("Move",("Final Player Location " + i + " " + (j + 1)));
                    } else {
                        statusArray[i][j] = "P";
                        //Log.d("Move",("Final Player Location " + i + " " + j));
                    }
                    break breakLabel;
                }
            }
        }
    }
    protected int getAmmoCounter() {
        return ammoCounter;
    }
    protected int getScore() { return score; }
    protected void setAmmoCounter() {
        ammoCounter -= 1;
    }

    protected void fireUp() {
        Log.d("Shoot","Fireup Called");
        //Log.d("StatusArray11",statusArray[1][1]);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    //statusArray[i][j] = "n";
                    for (int i2 = i; i2 >= 0; i2--) {
                        if (statusArray[i2][j].contains("T")) {
                            Log.d("Shoot","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Shoot",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i2][j].substring(1));
                            Log.d("Shoot",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            statusArray[i2][j] = "n";
                            score += 1;
                            break breakLabel;
                        }
                    }
                    break breakLabel;
                }
            }
        }
        ammoCounter -= 1;
    }
    protected void fireDown() {
        Log.d("Shoot","Fireup Called");
        //Log.d("StatusArray11",statusArray[1][1]);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    //statusArray[i][j] = "n";
                    for (int i2 = i; i2 < 7; i2++) {
                        if (statusArray[i2][j].contains("T")) {
                            Log.d("Shoot","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Shoot",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i2][j].substring(1));
                            Log.d("Shoot",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            statusArray[i2][j] = "n";
                            score += 1;
                            break breakLabel;
                        }
                    }
                    break breakLabel;
                }
            }
        }
        ammoCounter -= 1;
    }
    protected void fireLeft() {
        Log.d("Shoot","Fireup Called");
        //Log.d("StatusArray11",statusArray[1][1]);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    //statusArray[i][j] = "n";
                    for (int j2 = j; j2 >= 0; j2--) {
                        if (statusArray[i][j2].contains("T")) {
                            Log.d("Shoot","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Shoot",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i][j2].substring(1));
                            Log.d("Shoot",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            statusArray[i][j2] = "n";
                            score += 1;
                            break breakLabel;
                        }
                    }
                    break breakLabel;
                }
            }
        }
        ammoCounter -= 1;
    }
    protected void fireRight() {
        Log.d("Shoot","Fireup Called");
        //Log.d("StatusArray11",statusArray[1][1]);
        breakLabel:
        for (int i=0; i<7; i++) {
            for (int j=0; j<5;j++) {
                if (statusArray[i][j] == "P") {
                    //Log.d("Move",("Starting Player Location " + i + " " + j));
                    //statusArray[i][j] = "n";
                    for (int j2 = j; j2 < 5; j2++) {
                        if (statusArray[i][j2].contains("T")) {
                            Log.d("Shoot","Hit Target!");
                            targetsRemaining -= 1;
                            Log.d("Shoot",("Targets Remaining:" + targetsRemaining));
                            int targetIndex = Integer.valueOf(statusArray[i][j2].substring(1));
                            Log.d("Shoot",("Target Index " + targetIndex));
                            mTargets.removeTarget((targetIndex));
                            statusArray[i][j2] = "n";
                            score += 1;
                            break breakLabel;
                        }
                    }
                    break breakLabel;
                }
            }
        }
        ammoCounter -= 1;
    }

    //Targets move at random after the player makes a move
    protected void moveTarget() {
        //Log.d("Target","Moving a Target");
        boolean targetMoved = false;
        boolean foundTarget = false;
        Random mRand = new Random();
        int pX = 0;
        int pY = 0;
        int tX = 0;
        int tY = 0, dX = 0, dY = 0;
        String targetName;
        while (!targetMoved) {
            //pick random target
            int r = mRand.nextInt(5) + 1;
            //Log.d("Random", Integer.toString(r));
            //pick random direction
            int r2 = mRand.nextInt(4);
            r += 5;
            targetName = "T" + r;
            //Log.d("Move",targetName);
            //targetName = "T6";
            //Checks the second row of Targets to choose one to drop, if Player is below when it drops it is a game over, if it reached the bottom it will not move further.
            breakLabel:
            for (int i=0; i<7; i++) {
                for (int j=0; j<5;j++) {
                    //Log.d("Random", statusArray[i][j]);
                    if (Objects.equals(statusArray[i][j], targetName)) {
                        //Log.d("Move",("Target starting at Location " + i + " " + j));
                        if ((i + 1) < 7) {
                            if (statusArray[i + 1][j] == "n" || statusArray[i + 1][j] == "P") {
                                targetMoved = true;
                                mTargets.moveTarget(r,0, (getHeight()/10));
                                if (statusArray[i + 1][j] == "P") {
                                    mTargets.removePlayer();
                                    isActive = false;
                                    CharSequence text = "Game Over!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(this.context, text, duration);
                                    toast.show();
                                }
                                statusArray[i][j] = "n";
                                statusArray[i + 1][j] = "T" + r;
                                //Log.d("Move",("Target Moving"));
                                //Log.d("Move",("Target starting at Location " + (i + 1) + " " + j));

                            }
                        }
                        foundTarget = true;
                        break breakLabel;
                    }
                }
            }
            //Checks the first row if the second row has been cleared, this row only starts to drop when there is space below
            if (!foundTarget) {
                r -= 5;
                targetName = "T" + r;
                int j2 = r - 1;
                for (int i2=0; i2<7; i2++) {
                    if (Objects.equals(statusArray[i2][j2], targetName)) {
                        //Log.d("Move",("Target starting at Location " + i2 + " " + j2));
                        if ((i2 + 1) < 7) {
                            if (statusArray[i2 + 1][j2] == "n" || statusArray[i2 + 1][j2] == "P") {
                                targetMoved = true;
                                mTargets.moveTarget(r,0, (getHeight()/10));
                                if (statusArray[i2 + 1][j2] == "P") {
                                    mTargets.removePlayer();
                                    isActive = false;
                                    CharSequence text = "Game Over!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(this.context, text, duration);
                                    toast.show();
                                }
                                statusArray[i2][j2] = "n";
                                statusArray[i2 + 1][j2] = "T" + r;
                                //Log.d("Move",("Target Moving"));
                                //Log.d("Move",("Target starting at Location " + (i2 + 1) + " " + j2));
                                break;
                            }
                        }

                    }
                }
            }
            targetMoved = true;
            

        }
    }

    protected boolean getActiveState() {
        //Log.d("ActiveCheck", "Active State Checked");
        //Log.d("ActiveCheck", String.valueOf(isActive));
        return isActive;
    }
}
