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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout myCL = new ConstraintLayout(this);

        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);


        button_1.setOnClickListener(v -> openActivity1());
        button_2.setOnClickListener(v -> openActivity2());
        button_3.setOnClickListener(v -> openActivity3());

    }


    public void openActivity1(){
        Intent intent = new Intent(this,Activity1.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this,Activity3.class);
        startActivity(intent);
    }

    



}