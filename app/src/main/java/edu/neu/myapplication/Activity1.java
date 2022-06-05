package edu.neu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        TextView tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText("YANG YANG \nyang.yang16@northeastern.edu\n");

    }
}