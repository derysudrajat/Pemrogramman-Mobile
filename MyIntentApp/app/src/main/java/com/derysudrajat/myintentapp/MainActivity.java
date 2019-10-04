package com.derysudrajat.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_move)
    AppCompatButton btnMove;
    @BindView(R.id.btn_move_data)
    AppCompatButton btnMovewData;
    @BindView(R.id.btn_call)
    AppCompatButton btnCall;
    @BindView(R.id.btn_web)
    AppCompatButton btnWeb;
    @BindView(R.id.btn_intent_explicit)
    AppCompatButton btnIntentEx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnMove.setOnClickListener(this);
        btnMovewData.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnIntentEx.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_move:
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_move_data:
                Intent intentData = new Intent(MainActivity.this, MoveWithDataActivity.class);
                intentData.putExtra(MoveWithDataActivity.EXTRA_NAME,"Bambank Sunarto");
                intentData.putExtra(MoveWithDataActivity.EXTRA_AGE,17);
                startActivity(intentData);
                break;
            case R.id.btn_call:
                String phoneNumber = "081322505589";
                Intent intentDial = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:"+phoneNumber));
                startActivity(intentDial);
                break;
            case R.id.btn_web:
                String mainUrl = "https://ittelkom-pwt.ac.id/";
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(mainUrl));
                startActivity(intentWeb);
                break;
            case R.id.btn_intent_explicit:
                Intent intentExplicit = new Intent(MainActivity.this,ExplicitOneActivity.class);
                startActivity(intentExplicit);
                break;
        }
    }
}
