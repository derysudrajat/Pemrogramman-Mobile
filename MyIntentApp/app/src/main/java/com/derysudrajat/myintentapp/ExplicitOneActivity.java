package com.derysudrajat.myintentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExplicitOneActivity extends AppCompatActivity {

    @BindView(R.id.et_text)
    EditText inputData;
    @BindView(R.id.btn_page2)
    AppCompatButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_one);
        ButterKnife.bind(this);

        btnSend.setOnClickListener(view -> {
            Intent intent = new Intent(ExplicitOneActivity.this, ExplicitTwoActivity.class);
            intent.putExtra(ExplicitTwoActivity.EXTRA_DATA, inputData.getText().toString());
            startActivity(intent);
        });

        if (getSupportActionBar() != null) {
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
