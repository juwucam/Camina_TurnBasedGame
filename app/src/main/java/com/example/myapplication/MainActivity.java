package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtLog, managerID, karenID, managerHP, karenHP;
    Button mSkill1, mSkill2, mSkill3, mSkill4, nextTurn;

    //Manager Stats
    String managerName = "You";
    int managerHealth = 200;
    int managerMinDmg = 35;
    int managerMaxDmg = 10;
    int managerATK1 = 35; //Logical Sense
    int managerATK2 = 10; //Polite Words
    int managerATK3 = 25; //State Policy
    int managerHEAL = 20; //Eat Borjor

    //Enemy Stats
    String enemyName = "Karen";
    int enemyHealth = 200;
    int enemyMinDmg = 5;
    int enemyMaxDmg = 30;

    //Game Turn
    int turnNumber = 1;
    boolean disabledTurn = false;
    int turnCounter = 0;
    int buttonCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Text Views
        txtLog = findViewById(R.id.txtCombatLog);

        managerID = findViewById(R.id.managerID);
        managerHP = findViewById(R.id.managerHP);
        managerHP.setText(String.valueOf(managerHealth));

        karenID = findViewById(R.id.karenID);
        karenHP = findViewById(R.id.karenHP);
        karenHP.setText(String.valueOf(enemyHealth));


        //Buttons
        mSkill1 = findViewById(R.id.btnSkill1);
        mSkill2 = findViewById(R.id.btnSkill2);
        mSkill3 = findViewById(R.id.btnSkill3);
        mSkill4 = findViewById(R.id.btnSkill4);
        nextTurn = findViewById(R.id.btnNext);

        mSkill1.setOnClickListener(this);
        mSkill2.setOnClickListener(this);
        mSkill3.setOnClickListener(this);
        mSkill4.setOnClickListener(this);
        nextTurn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        txtLog.findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int enemyatk = randomizer.nextInt(enemyMaxDmg - enemyMinDmg) + enemyMinDmg;
        int manageratk = randomizer.nextInt(managerMaxDmg - managerMinDmg) + managerMinDmg;

        if (turnNumber % 2 != 1) {
            mSkill1.setEnabled(false);
            mSkill2.setEnabled(false);
            mSkill3.setEnabled(false);
            mSkill4.setEnabled(false);
        } else if (turnNumber % 2 == 1) {
            mSkill1.setEnabled(true);
            mSkill2.setEnabled(true);
            mSkill3.setEnabled(true);
            mSkill4.setEnabled(true);
        }

        if (buttonCounter > 0) {
            mSkill1.setEnabled(false);
            mSkill2.setEnabled(false);
            mSkill3.setEnabled(false);
            mSkill4.setEnabled(false);
        } else if (buttonCounter > 0) {
            mSkill1.setEnabled(true);
            mSkill2.setEnabled(true);
            mSkill3.setEnabled(true);
            mSkill4.setEnabled(true);

        }

        switch (view.getId()) {
            case R.id.btnSkill1:

                enemyHealth = enemyHealth - managerATK1;
                turnNumber++;
                karenHP.setText(String.valueOf(enemyHealth));
                nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");
                txtLog.setText(String.valueOf(managerName) + " used Logical Sense. Karen takes " + String.valueOf(managerATK1) + " damage.");
                break;

            case R.id.btnSkill2:

                enemyHealth = enemyHealth - managerATK2;
                turnNumber++;
                karenHP.setText(String.valueOf(enemyHealth));
                nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");
                txtLog.setText(String.valueOf(managerName) + " used Polite Words. Karen stunned for 3 turns and takes " + String.valueOf(managerATK2) + " shame damage.");
                disabledTurn = true;
                turnCounter = 3;
                break;

            case R.id.btnSkill3:

                enemyHealth = enemyHealth - managerATK3;
                turnNumber++;
                karenHP.setText(String.valueOf(enemyHealth));
                nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");
                txtLog.setText(String.valueOf(managerName) + " used Assert Policies. Karen takes " + String.valueOf(managerATK3) + " anger damage.");
                break;

            case R.id.btnSkill4:

                managerHealth = managerHealth + managerHEAL;
                turnNumber++;
                karenHP.setText(String.valueOf(enemyHealth));
                nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                txtLog.setText(String.valueOf(managerName) + " eat Borjor. You regained " + String.valueOf(managerHEAL) + " HP.");

                if (enemyHealth < 0) {
                    txtLog.setText("Hooray! " + String.valueOf(managerName) + " managed to get " + String.valueOf(enemyName) + " to leave peacefully. Manager wins!");
                    managerHealth = 200;
                    enemyHealth = 200;
                    turnNumber = 1;
                    buttonCounter = 0;
                    nextTurn.setText("Reset Game");
                }
                break;
            case R.id.btnNext:

                if (turnNumber % 2 == 1) {
                    enemyHealth = enemyHealth - manageratk;
                    turnNumber++;
                    karenHP.setText(String.valueOf(enemyHealth));
                    nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    txtLog.setText(String.valueOf(managerName) + " zoned out and unknowingly dealt " + String.valueOf(manageratk) + " to " + String.valueOf(enemyName));

                    if (enemyHealth < 0) {
                        txtLog.setText("Hooray! " + String.valueOf(managerName) + " managed to get " + String.valueOf(enemyName) + " to leave peacefully. Manager wins!");
                        managerHealth = 200;
                        enemyHealth = 200;
                        turnNumber = 1;
                        buttonCounter = 0;
                        nextTurn.setText("Reset Game");
                    }
                    buttonCounter--;
                } else if (turnNumber % 2 != +1) {
                    if (disabledTurn == true) {
                        txtLog.setText(String.valueOf(enemyName) + " is still ashamed and is stunned for " + String.valueOf(turnCounter) + " turns.");
                        turnCounter--;
                        turnNumber++;
                        nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");
                        if (turnCounter == 0) {
                            disabledTurn = false;
                        }
                    } else {
                        managerHealth = managerHealth - enemyatk;
                        turnNumber++;
                        managerHP.setText(String.valueOf(managerHealth));
                        nextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                        txtLog.setText(String.valueOf(enemyName) + " threw a tantrum and unknowingly dealt " + String.valueOf(enemyatk) + " to " + String.valueOf(managerName));

                        if (managerHealth < 0) {
                            txtLog.setText("Oh no! " + String.valueOf(managerName) + " failed to make " + String.valueOf(enemyName) + " leave! You are fired from your job, a disgrace to managership.");
                            managerHealth = 200;
                            enemyHealth = 200;
                            turnNumber = 1;
                            buttonCounter = 0;
                            nextTurn.setText("Reset Game");
                        }
                    }
                    break;
                }
        }
    }
}