package com.derysudrajat.myrecyclerview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_EXTRA = "detail_extra";

    @BindView(R.id.iv_detail_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_detail_nama)
    TextView tvNama;
    @BindView(R.id.tv_detail_desc)
    TextView tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getData();
    }


    private void getData() {
        Hima hima = getIntent().getParcelableExtra(DETAIL_EXTRA);
        assert hima != null;
        Glide.with(this).load(hima.getUrlImg()).into(ivLogo);
        tvNama.setText(hima.getNamaHm());
        tvDesc.setText(hima.getDescHm());

        setToolbar(hima.getNamaHm());
    }

    private void setToolbar(String title) {
        if (getSupportActionBar() != null) {
            setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
