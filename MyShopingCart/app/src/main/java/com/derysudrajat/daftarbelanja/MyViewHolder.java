package com.derysudrajat.daftarbelanja;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    View myview;

    TextView mType;
    TextView mNote;
    TextView mDate;
    TextView mAmount;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        myview = itemView;
        mType = myview.findViewById(R.id.type);
        mNote = myview.findViewById(R.id.note);
        mDate = myview.findViewById(R.id.date);
        mAmount = myview.findViewById(R.id.amount);
    }

    public void setmType(String mType) {
        this.mType.setText(mType);
    }

    public void setmNote(String mNote) {
        this.mNote.setText(mNote);
    }

    public void setmDate(String mDate) {
        this.mDate.setText(mDate);
    }

    public void setmAmount(int mAmount) {
        this.mAmount.setText(String.valueOf(mAmount));
    }
}
