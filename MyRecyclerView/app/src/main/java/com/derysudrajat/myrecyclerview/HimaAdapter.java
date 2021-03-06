package com.derysudrajat.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HimaAdapter extends RecyclerView.Adapter<HimaAdapter.HimaViewHolder> {

    private Context context;

    private List<Hima> himaList;

    public HimaAdapter(Context context) {
        this.context = context;
    }

    public List<Hima> getHimaList() {
        return himaList;
    }

    public void setHimaList(List<Hima> himaList) {
        this.himaList = himaList;
    }

    @NonNull
    @Override
    public HimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_model, parent, false);
        ButterKnife.bind(this, view);
        return new HimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HimaViewHolder holder, int position) {
        Hima hima = himaList.get(position);

        Glide.with(context).load(hima.getUrlImg()).into(holder.imgHima);
        holder.tvNama.setText(hima.getNamaHm());
        holder.tvDesc.setText(hima.getDescHm());

        holder.cvMain.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.DETAIL_EXTRA, hima);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return himaList.size();
    }

    public class HimaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cvMain;
        @BindView(R.id.iv_hima)
        ImageView imgHima;
        @BindView(R.id.tv_name)
        TextView tvNama;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        public HimaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
