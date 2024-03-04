package com.example.fitnessapp_fyp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;


public class drill2 extends Fragment {




    Button button1,button2,button3,button4;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_drill2, container, false);


        button1 = (Button) rootView.findViewById(R.id.handlesbutton);
        button2 = (Button) rootView.findViewById(R.id.shootingbutton);
        button3 = (Button) rootView.findViewById(R.id.passingbutton);
        button4 = (Button) rootView.findViewById(R.id.fitnessbutton);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), workouts.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), workouts2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), workouts3.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), workouts4.class);
                startActivity(intent);

            }
        });


        return rootView;


    }
    public void handles (View view){
        Intent intent = new Intent(getActivity(), workouts.class);
        startActivity(intent);

    }
    public void shooting (View view){
        Intent intent = new Intent(getActivity(), workouts2.class);
        startActivity(intent);

    }
    public void passing (View view){
        Intent intent = new Intent(getActivity(), workouts3.class);
        startActivity(intent);

    }
    public void fitness (View view){
        Intent intent = new Intent(getActivity(), workouts4.class);
        startActivity(intent);

    }

}