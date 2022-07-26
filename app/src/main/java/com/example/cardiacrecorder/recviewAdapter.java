package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recviewAdapter extends RecyclerView.Adapter<recviewAdapter.MyviewHolder> {

    Context context;
    ArrayList<bp> list;

    public recviewAdapter(Context context, ArrayList<bp> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        bp b = list.get(position);
        holder.date.setText(b.getDate());
        holder.sys.setText(b.getSys());
        holder.dia.setText(b.getDia());
        holder.verdict.setText(b.getVerdict());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView sys,dia,date,verdict;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            sys = itemView.findViewById(R.id.sysview);
            dia = itemView.findViewById(R.id.diaview);
            date = itemView.findViewById(R.id.dateview);
            verdict = itemView.findViewById(R.id.verdictview);
        }
    }
}
