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

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.email_reg)
    EditText etEmail;
    @BindView(R.id.password_reg)
    EditText etPass;
    @BindView(R.id.signin_txt)
    TextView tvSignIn;
    @BindView(R.id.btn_reg)
    Button btnRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(v -> {
            String mEmail = etEmail.getText().toString().trim();
            String mPass = etPass.getText().toString().trim();

            if (TextUtils.isEmpty(mEmail)){
                etEmail.setError("Email harus diisi!");
                return;
            }

            if (TextUtils.isEmpty(mPass)){
                etPass.setError("Password harus diisi!");
                return;
            }

            mDialog.setMessage("Processing...");
            mDialog.show();

            mAuth.createUserWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });
        });

        tvSignIn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}
