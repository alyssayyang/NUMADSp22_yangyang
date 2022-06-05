package edu.neu.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<links> linkslist;

    public recyclerAdapter(ArrayList<links> linkslist){
        this.linkslist=linkslist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nametxt;
        private TextView urltxt;

        public MyViewHolder(final View view){
            super(view);
            nametxt = view.findViewById(R.id.textView_name);
            urltxt = view.findViewById(R.id.textView_url);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = linkslist.get(position).getLinkname();
        holder.nametxt.setText(name);

        String url = linkslist.get(position).getLinkurl();
        holder.urltxt.setText(url);

    }

    @Override
    public int getItemCount() {
        return linkslist.size();
    }
}
