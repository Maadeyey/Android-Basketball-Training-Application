package com.example.fitnessapp_fyp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class activity2 extends Fragment {
    private DBHelper dbHelper;
    private TextView numWatchedTextView;
    EditText weightEditText, heightEditText;
    TextView resultTextView;
    Button SubmitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity2, container, false);
        weightEditText = view.findViewById(R.id.WEIGHT);
        heightEditText = view.findViewById(R.id.HEIGHT);
        SubmitButton = view.findViewById(R.id.SUBMIT);
        resultTextView = view.findViewById(R.id.result);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
        dbHelper = new DBHelper(getContext());
        numWatchedTextView = view.findViewById(R.id.numWatchedText);

        // get the username from shared preferences
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        // get the numWatched stats from the database
        int watch_count = dbHelper.getCountForUser(username, getContext());
        // set the numWatched text view
        numWatchedTextView.setText(String.valueOf(watch_count));
        return view;
    }
    private void calculateBMI() {
        // Get user inputs from the EditText fields
        String weightString = weightEditText.getText().toString();
        String heightString = heightEditText.getText().toString();
        // Convert strings to floats
        float weight = Float.parseFloat(weightString);
        float height = Float.parseFloat(heightString);
        // Calculate BMI
        float bmi = weight / ((height / 100) * (height / 100));
        // Set result in the TextView
        resultTextView.setText( String.valueOf(bmi));
    }
}

