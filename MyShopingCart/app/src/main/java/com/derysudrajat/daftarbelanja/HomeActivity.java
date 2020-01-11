package com.derysudrajat.daftarbelanja;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab_btn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private RecyclerView recyclerView;

    private TextView totalResult;

    //Global Variable
    private String type;
    private int amount;
    private String note;
    private String post_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.keepSynced(true);

        fab_btn = findViewById(R.id.fab);
        fab_btn.setOnClickListener(view -> customDialog());

        totalResult = findViewById(R.id.total_amount);

        recyclerView = findViewById(R.id.rv_home);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //Total Sum Number
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int totalAmount = 0;
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Data data = snap.getValue(Data.class);

                    totalAmount += data.getAmount();
                    String sttotal = "Rp " + totalAmount;

                    totalResult.setText(sttotal);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void customDialog() {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(HomeActivity.this);

        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
        View myview = inflater.inflate(R.layout.input_data_layout, null);
        final AlertDialog dialog = mydialog.create();
        dialog.setView(myview);
        dialog.show();

        final EditText type = myview.findViewById(R.id.edt_type);
        final EditText amount = myview.findViewById(R.id.edt_amount);
        final EditText note = myview.findViewById(R.id.edt_note);
        Button btnSave = myview.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(view -> {

            String mType = type.getText().toString().trim();
            String mAmount = amount.getText().toString().trim();
            String mNote = note.getText().toString().trim();

            int ammint = Integer.parseInt(mAmount);

            if (TextUtils.isEmpty(mType)) {
                type.setError("Require Field...");
                return;
            }

            if (TextUtils.isEmpty(mAmount)) {
                amount.setError("Require Field...");
                return;
            }

            if (TextUtils.isEmpty(mNote)) {
                note.setError("Require Field...");
                return;
            }

            String id = mDatabase.push().getKey();
            String date = DateFormat.getDateInstance().format(new Date());
            Data data = new Data(id, mType, ammint, mNote, date);
            String uId = mUser.getUid();
            mDatabase.child("Daftar Belanja").child(uId).child(id).setValue(data);

            Toast.makeText(getApplicationContext(), "Data Add", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>(
                Data.class,
                R.layout.item_data,
                MyViewHolder.class,
                mDatabase) {

            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final Data model, final int position) {
                viewHolder.setmDate(model.getDate());
                viewHolder.setmType(model.getType());
                viewHolder.setmNote(model.getNote());
                viewHolder.setmAmount(model.getAmount());

                viewHolder.myview.setOnClickListener(view -> {

                    post_key = getRef(position).getKey();
                    type = model.getType();
                    note = model.getNote();
                    amount = model.getAmount();

                    updateData();
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public void updateData() {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(HomeActivity.this);
        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
        View mView = inflater.inflate(R.layout.update_data_layout, null);

        final AlertDialog dialog = mydialog.create();
        dialog.setView(mView);

        final EditText edt_type = mView.findViewById(R.id.edt_type_upd);
        final EditText edt_amount = mView.findViewById(R.id.edt_amount_upd);
        final EditText edt_note = mView.findViewById(R.id.edt_note_upd);

        edt_type.setText(type);
        edt_type.setSelection(type.length());

        edt_amount.setText(String.valueOf(amount));
        edt_amount.setSelection(String.valueOf(amount).length());

        edt_note.setText(note);
        edt_note.setSelection(note.length());

        Button btnUpdate = mView.findViewById(R.id.btn_save_upd);
        Button btnDelete = mView.findViewById(R.id.btn_delete_upd);

        btnUpdate.setOnClickListener(view -> {
            type = edt_type.getText().toString().trim();
            String mAmount = String.valueOf(amount);
            mAmount = edt_amount.getText().toString().trim();
            note = edt_note.getText().toString().trim();

            int intAmount = Integer.parseInt(mAmount);
            String date = DateFormat.getDateInstance().format(new Date());

            Data data = new Data(post_key, type, intAmount, note, date);

            mDatabase.child(post_key).setValue(data);
            dialog.dismiss();

        });

        btnDelete.setOnClickListener(view -> {
            mDatabase.child(post_key).removeValue();

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
