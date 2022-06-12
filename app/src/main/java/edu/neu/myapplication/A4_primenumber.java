package edu.neu.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;




public class A4_primenumber extends AppCompatActivity {
    Button search;
    Button terminate;
    TextView currentNum;
    TextView currentPrime;
    private Handler myHandler = new Handler();
    private boolean searchStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4_primenumber);

        search = findViewById(R.id.b_findPrimes);
        terminate = findViewById(R.id.b_terminateSearch);
        currentNum = findViewById(R.id.tv_currentNum);
        currentPrime = findViewById(R.id.tv_currentPrime);
        searchStatus = false;

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchPrimes(v);
            }
        });

        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStatus = false;
            }
        });
    }

    public void searchPrimes(View v){
        if(!searchStatus){
            searchStatus = true;
            //create a runnable object
            //all events happen in the new thread are in the runnable class
            myRun myRunnable = new myRun();
            //start the new thread
            new Thread(myRunnable).start();
        }
    }

    public boolean isPrime(int i){

        if (i <= 1) return false;

        for (int j = 2; j < i;j++){
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(searchStatus) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Searching in progess, are you sure to exit?", Snackbar.LENGTH_LONG);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    class myRun implements Runnable{
        @Override
        public void run() {
            // do periodical action here
            int i = 2;
            while (searchStatus) {
                final int num = i;
                //post message from background thread to main thread using looper
                myHandler.post(() ->{
                    currentNum.setText(String.valueOf(num));
                    if (isPrime(num)) {
                        currentPrime.setText(String.valueOf(num));
                    }
                });

                i++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}