package com.derysudrajat.mycalass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.derysudrajat.mycalass.MyApp.db;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    CrewAdapter adapter;

    List<Crew> crewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fetchDataFromRoom();
        initView();
    }

    private void initView() {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setHasFixedSize(true);
        rvMain.setItemAnimator(new DefaultItemAnimator());
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "crew").allowMainThreadQueries().build();
        crewList = db.crewDao().getAllCrew();
        Log.d("MainActivity", "data: " + crewList.size());
        adapter = new CrewAdapter(this);
        adapter.setCrewList(crewList);
        rvMain.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    void addCrew() {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @Override
    protected void onResume() {
        fetchDataFromRoom();
        initView();
        super.onResume();
    }

}

