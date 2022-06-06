package edu.neu.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    //linkslist: whatever info we stored is in linkslist
    ArrayList<links> linkslist;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    FloatingActionButton enterNewLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //enternewlink is a FLOATING BUTTON
        enterNewLink = findViewById(R.id.enterNewLink);
        //initialize list here
        linkslist = new ArrayList<>();
//        linkslist.add(new links("google","www.google.com"));
//        linkslist.add(new links("google","www.google.com"));
//        linkslist.add(new links("google","www.google.com"));
        recyclerAdapter = new recyclerAdapter(linkslist, this);
        recyclerView.setAdapter(recyclerAdapter);

        enterNewLink.setOnClickListener(new View.OnClickListener() {
            //when the button is clicked, call alertdialog
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
    }

    private void alertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Enter New Link");

        final View customLayout = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        dialog.setView(customLayout);

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        Button confirm_button = customLayout.findViewById(R.id.confirm_button);
        Button cancel_button = customLayout.findViewById(R.id.cancel_button);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName = customLayout.findViewById(R.id.url_name);
                EditText editURL = customLayout.findViewById(R.id.url_link);
                String name = editName.getText().toString();
                String URL = "https://" + editURL.getText().toString();

                links mylink = new links(name, URL);
                linkslist.add(mylink);
                //
                recyclerView.setAdapter(recyclerView.getAdapter());

                Snackbar snackbar = Snackbar.make(recyclerView, "A link is added", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linkslist.remove(mylink);
                        recyclerView.setAdapter(recyclerView.getAdapter());
                    }
                });
                snackbar.show();
//
                alertDialog.hide();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }
}





