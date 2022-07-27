package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class recviewAdapter extends RecyclerView.Adapter<recviewAdapter.MyviewHolder> {

    private Context context;
    ArrayList<bp_record_pass> list = new ArrayList<>();
    //bp
    //bp_record_pass



    public recviewAdapter(Context context) {
        this.context = context;
       // this.list = list;

    }

    public void setItems(ArrayList<bp_record_pass> bpr){
        list.addAll(bpr);
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        //bp b = list.get(position);
        MyviewHolder vh = (MyviewHolder) holder;

        bp_record_pass bpr = list.get(position);

        vh.date.setText(bpr.getDate());
        vh.sys.setText(bpr.getSys());
        vh.dia.setText(bpr.getDia());
        vh.verdict.setText(bpr.getVerdict());

        /*
        holder.date.setText(bpr.getDate());
        holder.sys.setText(bpr.getSys());
        holder.dia.setText(bpr.getDia());
        holder.verdict.setText(bpr.getVerdict());

         */

        /*
        holder.vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context,addRecord.class);
               intent.putExtra("EDIT",bpr);
               context.startActivity(intent);
            }
        });

         */

        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,addRecord.class);
                intent.putExtra("EDIT",bpr);
                context.startActivity(intent);
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db_class dbClass = new db_class();
                dbClass.delete(bpr.getKey()).addOnSuccessListener(suc ->
                {
                    Toast.makeText(context, "Record Is DELETED", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });

       holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,viewActivity.class);
                intent.putExtra("VIEW",bpr);
                context.startActivity(intent);
            }
        });







    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView sys,dia,date,verdict;
        View vi;
        Button edit_btn, delete_btn, view_btn;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            sys = itemView.findViewById(R.id.sysview);
            dia = itemView.findViewById(R.id.diaview);
            date = itemView.findViewById(R.id.dateview);
            verdict = itemView.findViewById(R.id.verdictview);

            vi = itemView;

            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            view_btn = itemView.findViewById(R.id.view_btn);


        }
    }


}
