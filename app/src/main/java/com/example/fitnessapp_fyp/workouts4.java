package com.example.fitnessapp_fyp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp_fyp.Adapter.RecyclerViewAdapter;
import com.example.fitnessapp_fyp.Model.Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class workouts4 extends AppCompatActivity {
    List<Exercise> exerciseList= new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts4);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.ListOfExercise);
        adapter= new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initData() {
        exerciseList.add(new Exercise(R.drawable.onefive, " Push ups x 20"));
        exerciseList.add(new Exercise(R.drawable.oneseven, "Scissors x 20"));
        exerciseList.add(new Exercise(R.drawable.onesix, "Russian Twists"));
        exerciseList.add(new Exercise(R.drawable.oneeight, "Box Jumps"));
        exerciseList.add(new Exercise(R.drawable.mountain, "Step Ups x 20"));
        Collections.shuffle(exerciseList);
    }

}
