package com.derysudrajat.daftarbelanja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.email_login)
    EditText etEmail;
    @BindView(R.id.password_login)
    EditText etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_signup)
    TextView tvSignup;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(v -> {
            String mMail = etEmail.getText().toString().trim();
            String mPass = etPass.getText().toString().trim();

            if (TextUtils.isEmpty(mMail)){
                etEmail.setError("Required Field");
                return;
            }

            if (TextUtils.isEmpty(mPass)){
                etPass.setError("Required Field");
                return;
            }

            mDialog.setMessage("Processing...");
            mDialog.show();

            mAuth.signInWithEmailAndPassword(mMail, mPass).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                   Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                   mDialog.dismiss();
               }else{
                   Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
               }
            });
        });

        tvSignup.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        });
    }
}
