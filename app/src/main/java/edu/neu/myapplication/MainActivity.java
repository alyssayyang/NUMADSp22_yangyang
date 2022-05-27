/*
YANG YANG
CS 5520 A1
MAY 13 2022
 */

package edu.neu.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActicity2();
            }
        });


    }

    public void displayToastMsg(String msg){
        Toast toast = Toast.makeText(this,msg,Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.FILL_HORIZONTAL,0,0);
        toast.show();
    }

    public void intro(View v){
        displayToastMsg("YANG YANG \nyang.yang16@northeastern.edu");
    }

    public void openActicity2(){
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }


}