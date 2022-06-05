package edu.neu.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private ArrayList<links> linkslist;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        recyclerView = findViewById(R.id.recyclerView);
        linkslist = new ArrayList<>();

        setLinksInfo();
        setAdapter();

    }

    private void setAdapter(){
        recyclerAdapter adapter = new recyclerAdapter(linkslist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //error: unable to invoke method on a null object reference
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setLinksInfo(){
        linkslist.add(new links("google","https://www.google.com.hk/?client=safari&gws_rd=ssl"));
    }

}