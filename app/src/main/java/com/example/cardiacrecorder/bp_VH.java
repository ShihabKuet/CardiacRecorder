package com.example.cardiacrecorder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class bp_VH extends RecyclerView.ViewHolder {
    public TextView sys,dia,date,verdict;
    public bp_VH(@NonNull View itemView) {
        super(itemView);
        sys = itemView.findViewById(R.id.sysview);
        dia = itemView.findViewById(R.id.diaview);
        date = itemView.findViewById(R.id.dateview);
        verdict = itemView.findViewById(R.id.verdictview);
    }
}
