package com.derysudrajat.mybiodata;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_poster)
    ImageView poster;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_major)
    TextView major;
    @BindView(R.id.tv_id)
    TextView idStudent;
    @BindView(R.id.tv_born)
    TextView born;
    @BindView(R.id.tv_goals)
    TextView goals;
    @BindView(R.id.tv_desc)
    TextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Glide.with(this).load(R.drawable.derys).into(poster);
        name.setText(getResources().getString(R.string.name));
        major.setText(getResources().getString(R.string.major));
        idStudent.setText(getResources().getString(R.string.id_student));
        born.setText(getResources().getString(R.string.born));
        goals.setText(getResources().getString(R.string.future_goals));
        desc.setText(getResources().getString(R.string.desc));
    }
}
