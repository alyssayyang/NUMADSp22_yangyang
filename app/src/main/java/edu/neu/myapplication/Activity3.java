package edu.neu.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private ArrayList<links> linkslist;
    private RecyclerView recyclerView;
    private FloatingActionButton enterNewLink;

    private TextView textViewName;
    private TextView textViewURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        recyclerView = findViewById(R.id.recyclerView);
        enterNewLink = findViewById(R.id.enterNewLink);
        linkslist = new ArrayList<>();

        enterNewLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openDialog();
            }
        });

        setLinksInfo();
        setAdapter();


    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(linkslist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //error: unable to invoke method on a null object reference
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setLinksInfo() {
        linkslist.add(new links("google", "https://www.google.com.hk/?client=safari&gws_rd=ssl"));
    }

    public void openDialog(){
        //creating an instance of dialog
        EnterLinkDialog enterLinkDialog = new EnterLinkDialog();
        enterLinkDialog.show(getSupportFragmentManager(),"Enter New Link");
    }






}

