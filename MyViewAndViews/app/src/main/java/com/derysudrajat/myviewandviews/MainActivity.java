package com.derysudrajat.myviewandviews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_donasi)
    Button btnDonasi;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.r80).into(ivBanner);
        Glide.with(this).load(R.drawable.logor80).into(ivIcon);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.donasi));
        }

        btnDonasi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DonasiActivity.class);
            startActivity(intent);
        });
    }
}
