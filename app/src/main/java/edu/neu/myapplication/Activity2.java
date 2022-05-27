package edu.neu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private GridLayout g;
    private Button button15;
    private Button button17;
    private Button button18;
    private Button button19;
    private Button button20;
    private Button button21;
    private Button button22;
    private TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        g = (GridLayout) findViewById(R.id.gridLayout2);

        button15 = (Button)findViewById(R.id.button15);
        button17 = (Button)findViewById(R.id.button17);
        button18 = (Button)findViewById(R.id.button18);
        button19 = (Button)findViewById(R.id.button19);
        button20 = (Button)findViewById(R.id.button20);
        button21 = (Button)findViewById(R.id.button21);
        button22 = (Button)findViewById(R.id.button22);

        tv2 = (TextView) findViewById(R.id.textView2);
    }

    public void onBtnClicked(View v){
        v.getId();
        tv2.setText("Pressed: " + ((Button)v).getText());
    }

    public void goBack(View v){
        super.onBackPressed();
    }





}