package com.example.fitnessapp_fyp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.fitnessapp_fyp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Home2 extends Fragment{

    private VideoView videoView;
    Button handlesbutton2, shootingbutton2,RandomWorkoutButton;
    private List<Exercise> exerciseList = new ArrayList<>();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        exerciseList.add(new Exercise(R.drawable.two, "Between The Legs"));
        exerciseList.add(new Exercise(R.drawable.betweentheleg, "Between The Legs"));
        exerciseList.add(new Exercise(R.drawable.two, "Between The Legs"));
        exerciseList.add(new Exercise(R.drawable.betweentheleg, "Between The Legs"));



        View rootView = inflater.inflate(R.layout.fragment_home2,container,false);
        handlesbutton2 = (Button) rootView.findViewById(R.id.Handlesbutton2);
        shootingbutton2 = (Button) rootView.findViewById(R.id.shortcutShooting2);
        RandomWorkoutButton= (Button) rootView.findViewById(R.id.sbutton);


        handlesbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), workouts.class);
                startActivity(intent);
            }
        });
        shootingbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), workouts2.class);
                startActivity(intent);
            }
        });
        RandomWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Exercise workout = exerciseList.get(random.nextInt(exerciseList.size()));

                // Launch ExerciseView activity with the chosen workout's details
                Intent intent = new Intent(getActivity(), ExerciseView.class);
                intent.putExtra("image_id", workout.getImage_id());
                intent.putExtra("name", workout.getName());
                startActivity(intent);

            }
        });


        return rootView;

        // Inflate the layout for this fragment


    }

}