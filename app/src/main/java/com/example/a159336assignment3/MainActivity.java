//Written by Joel Yap, ID 21007112
//This game, Falling Grid, is a turn-based shooting puzzle game. The player (green) can move in 4 directions and move onto grey circles (Targets) to remove them for score.
// The player can also pick up blue Pickups from the middle of the game board to gain ranged attacks, the buttons on the right are for shooting if the player has ammunition remaining.
//The targets will fall at random after the player moves, the player always moves first and there is no timer, but if the player is under a target when it drops, the player is defeated and the game is over. Avoid the falling targets and shoot them or move from the sides.
//When the player removes all 10 targets and has a score of 10, the Reset Button becomes visible and can be pressed to restart the game.

package com.example.a159336assignment3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GameView mGameView;
    TextView ammoView;
    TextView scoreView;
    String ammoText, scoreText;
    int ammoCounter, scoreCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ammoView = findViewById(R.id.textView3);
        mGameView = findViewById(R.id.gameView);
        scoreView = findViewById(R.id.textView4);
        Button resetButton = findViewById(R.id.resetButton);
        //Setting up Button objects
        Button button = findViewById(R.id.button);
        Button buttonDown = findViewById(R.id.button4);
        Button buttonLeft = findViewById(R.id.button2);
        Button buttonRight = findViewById(R.id.button3);
        Button buttonFireUp = findViewById(R.id.buttonF1);
        Button buttonFireDown = findViewById(R.id.buttonF4);
        Button buttonFireLeft = findViewById(R.id.buttonF2);
        Button buttonFireRight = findViewById(R.id.buttonF3);
        //Button to move Player up, Buttons also count ammunition, score, move a random Target, display the reset Button if the player wins
        button.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button 1");
            mGameView.movePlayerUp();
            ammoCounter = mGameView.getAmmoCounter();
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to move Player down
        buttonDown.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button 4");
            mGameView.movePlayerDown();
            ammoCounter = mGameView.getAmmoCounter();
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to move Player left
        buttonLeft.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button 2");
            mGameView.movePlayerLeft();
            ammoCounter = mGameView.getAmmoCounter();
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to move Player right
        buttonRight.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button 3");
            mGameView.movePlayerRight();
            ammoCounter = mGameView.getAmmoCounter();
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to fire Player projectiles up if having enough ammo
        buttonFireUp.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button F1");
            if (ammoCounter > 0) {
                mGameView.fireUp();
                ammoCounter -= 1;
            }
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to fire Player projectiles
        buttonFireDown.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button F4");
            if (ammoCounter > 0) {
                mGameView.fireDown();
                ammoCounter -= 1;
            }
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to fire Player projectiles
        buttonFireLeft.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button F2");
            if (ammoCounter > 0) {
                mGameView.fireLeft();
                ammoCounter -= 1;
            }
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Button to fire Player projectiles
        buttonFireRight.setOnClickListener(v -> {
            //Log.d("BUTTONS", "User tapped button F3");
            if (ammoCounter > 0) {
                mGameView.fireRight();
                ammoCounter -= 1;
            }
            setupDisplay();
            mGameView.moveTarget();
            if (scoreCounter == 10 || !mGameView.getActiveState()) {
                resetButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                buttonDown.setEnabled(false);
                buttonLeft.setEnabled(false);
                buttonRight.setEnabled(false);
                buttonFireUp.setEnabled(false);
                buttonFireDown.setEnabled(false);
                buttonFireLeft.setEnabled(false);
                buttonFireRight.setEnabled(false);
            }
            mGameView.invalidate();
        });
        //Reset Button
        resetButton.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            finish();
            startActivity(i);
        });
    }

    private void setupDisplay() {
        ammoText = "Ammo: " + ammoCounter;
        ammoView.setText(ammoText);
        scoreCounter = mGameView.getScore();
        scoreText = "Score: " + scoreCounter;
        scoreView.setText(scoreText);
    }
}