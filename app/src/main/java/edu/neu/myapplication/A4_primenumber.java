package edu.neu.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
//
//public class A4_primenumber extends AppCompatActivity {
//
//    Button b_findPrimes;
//    Button b_terminateSearch;
//    TextView tv_currentNum;
//    TextView tv_currentPrime;
//    boolean flag;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.a4_primenumber);
//
//        b_findPrimes = findViewById(R.id.b_findPrimes);
//        b_terminateSearch = findViewById(R.id.b_terminateSearch);
//
//        tv_currentNum = findViewById(R.id.tv_currentNum);
//        tv_currentPrime = findViewById(R.id.tv_currentPrime);
//        flag = false;
//
//        b_findPrimes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startFindPrime();
//            }
//        });
//
//        b_terminateSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                terminateSearch();
//            }
//        });
//
//    }
//
//
//    public void startFindPrime() {
//            //!flag is true now, so it will start run
//            if(!flag) {
//                //change flag to true
//                flag = true;
//                //new a run, and pass it to thread
//                Thread current = new Thread(new Runnable(){
//                    @Override
//                    public void run(){
//                                try {
//                                        synchronized (this){
//                                            wait(5000);
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    int num = 2;
//                                                    while (num < Integer.MAX_VALUE) {
//                                                        if (flag == false)
//                                                            break;
//                                                        tv_currentNum.setText(Integer.toString(num));
//                                                        if (isPrime(num)) {
//                                                            tv_currentPrime.setText(Integer.toString(num));
//                                                        }
//                                                        num++;
//                                                        try {
//                                                            Thread.sleep(500);
//                                                        } catch (InterruptedException e) {
//                                                            e.printStackTrace();
//                                                        }
//                                                    }
//                                                }
//                                            });
//                                        }
//                                }
//                                catch (InterruptedException e) {
//                                    e.printStackTrace();
//                    }
//
//                    }
//                    //start the thread
//
//                });
//                current.start();
//            }
//
//    }
//
//
//
//
//
//    public void terminateSearch(){
//        //set flag = false here, in run value of flag will be constantly checked
//        flag = false;
//    }
//
//
//
//    @Override
//    public void onBackPressed(){
//
//    }
//
//
//
//    public boolean isPrime(int num){
//
//        if(num<=1){
//            return false;
//        }
//
//        for(int i = 2; i< num; i++){
//            if(num % i == 0){
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//}


public class A4_primenumber extends AppCompatActivity {
    Button search_bt;
    Button terminate_bt;
    TextView checkingTV;
    TextView primeIsTV;
    boolean searchState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4_primenumber);
        search_bt = findViewById(R.id.b_findPrimes);
        terminate_bt = findViewById(R.id.b_terminateSearch);
        checkingTV = findViewById(R.id.tv_currentNum);
        primeIsTV = findViewById(R.id.tv_currentPrime);
        searchState = false;
    }

    public void searchThread(View v) {
        if(!searchState) {
            searchState = true;
            Thread checking = new Thread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    int number = 2;
                    while (number < Integer.MAX_VALUE) {
                        if (searchState) break;
                        try {
                            checkingTV.setText(Integer.toString(number));
                            if (isPrime(number)) {
                                primeIsTV.setText(Integer.toString(number));
                            }
                            number++;
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            checking.start();
        }

    }

    public void terminateClicked(View v){
        if(!searchState) {
            Snackbar snackbar = Snackbar.make(v,"Search is not running, please start search first", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else{
            searchState = false;
            Snackbar snackbar = Snackbar.make(v,"Search Terminated", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    public boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < number; i++){
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // this method will stop the thread when exit
    @Override
    public void onPause() {
        super.onPause();
        searchState = false;
    }


    // to confirm exit when pressing the exit button
    @Override
    public void onBackPressed() {
        if (searchState) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Confirm if you want to exit while searching on progress", Snackbar.LENGTH_LONG);
            snackbar.setAction("Confirm", v-> {
                snackbar.dismiss();
                finish();
            });
            snackbar.show();
        }
        else {
            finish();
        }
    }

    // to confirm exit when pressing the back button on toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}