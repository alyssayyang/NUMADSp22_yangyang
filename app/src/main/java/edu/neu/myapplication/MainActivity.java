/*
YANG YANG
CS 5520 A1
MAY 13 2022
 */

package edu.neu.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        button_6 = (Button) findViewById(R.id.button_6);



        button_1.setOnClickListener(v -> openActivity1());
        button_2.setOnClickListener(v -> openActivity2());
        button_3.setOnClickListener(v -> openActivity3());
        button_4.setOnClickListener(v -> openActivity4());
        button_5.setOnClickListener(v -> openActivity5());
        button_6.setOnClickListener(v -> openActivity6());


    }


    public void openActivity1(){
        Intent intent = new Intent(this,A1_aboutme.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, A2_clickyclicky.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, A3_linkcollector.class);
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this, A4_primenumber.class);
        startActivity(intent);
    }

    public void openActivity5(){
        Intent intent = new Intent(this, A5_location.class);
        startActivity(intent);
    }

    public void openActivity6(){
        Intent intent = new Intent(this, A6_weather.class);
        startActivity(intent);
    }




}