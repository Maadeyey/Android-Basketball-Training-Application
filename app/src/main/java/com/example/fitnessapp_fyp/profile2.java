package com.example.fitnessapp_fyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class profile2 extends Fragment {
    private Button predictButton;
    EditText InputText;
    TextView ResultDisplay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile2, container, false);
        InputText = root.findViewById(R.id.input);
        ResultDisplay = root.findViewById(R.id.result);


        predictButton = root.findViewById(R.id.predict_button);
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return root;


    }
}