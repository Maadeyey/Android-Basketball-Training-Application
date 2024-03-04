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
public class workouts extends AppCompatActivity {

    List<Exercise> exerciseList= new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);


        initData();
        recyclerView = (RecyclerView) findViewById(R.id.ListOfExercise);
        adapter= new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.one, "Between The Legs"));
        exerciseList.add(new Exercise(R.drawable.onethree, "Crossovers"));
        exerciseList.add(new Exercise(R.drawable.onefour, "Behind the back"));
        exerciseList.add(new Exercise(R.drawable.onetwo, "Same Hand Between the Leg"));
        exerciseList.add(new Exercise(R.drawable.vidone, "in and out"));
        exerciseList.add(new Exercise(R.drawable.two, "Cone drills"));
        exerciseList.add(new Exercise(R.drawable.behind, "Crossover + Behind the Back"));
        Collections.shuffle(exerciseList);
    }


}
