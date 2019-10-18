package com.derysudrajat.signupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.BtnRegister)
    Button btnRegister;
    @BindView(R.id.BtnReadmore)
    Button btnReadmore;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    Boolean isRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        isRead = false;

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnReadmore.setOnClickListener(view -> {
            if (!isRead){
                tvDesc.setMaxLines(20);
                isRead = true;
                btnReadmore.setText(getResources().getString(R.string.ringkasan));
            }else{
                tvDesc.setMaxLines(2);
                isRead = false;
                btnReadmore.setText(getResources().getString(R.string.selengkapnya));
            }

        });




    }
}
