package edu.neu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private final ArrayList<Link> linkslist;
    private final Context context;

    //when we want to instantiate a recycleradapter object, we need to pass a list
    public recyclerAdapter(ArrayList<Link> linkslist, Context context){
        //now the linkslist field is updated to the passed in linkslist
        this.linkslist=linkslist;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //create two local var to link with the items in listitem xml
        private TextView nametxt;
        private TextView urltxt;

        public MyViewHolder(final View view){
            super(view);
            //these two are from the itemlist xml
            nametxt = view.findViewById(R.id.textView_name);
            urltxt = view.findViewById(R.id.textView_url);
        }

        //added 4:54pm june6
        public void bindThisData(Link thelinktobind){
            nametxt.setText(thelinktobind.getLinkname());
            urltxt.setText(thelinktobind.getLinkurl());
        }

    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //here list_items layout is passed to viewholder
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {


        holder.bindThisData(linkslist.get(position));

        final String url = linkslist.get(position).getLinkurl();
        holder.urltxt.setText(url);

        holder.urltxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browseIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return linkslist.size();
    }

//add on 6:06pm june 6
    public Link getItem(int i){
        return linkslist.get(i);
    }


}
