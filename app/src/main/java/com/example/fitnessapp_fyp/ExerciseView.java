package com.example.fitnessapp_fyp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ExerciseView extends AppCompatActivity {

    DBHelper dbHelper;
    int image_id;
    String name;


    ImageView gifImage;
    TextView title, timer;

    Button startButton;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);



        dbHelper = new DBHelper(this);
        timer = (TextView) findViewById(R.id.timer);
        title = (TextView) findViewById(R.id.title);
        gifImage = (ImageView) findViewById(R.id.workout_image);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startButton.setText("DONE");
                    new CountDownTimer(20000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished / 1000);
                        }
                        @Override
                        public void onFinish() {
                            Toast.makeText(ExerciseView.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            String username = sharedPreferences.getString("username", "");
                            dbHelper.incrementCountForUser(username, ExerciseView.this);
                            finish();
                        }
                    }.start();
                } else {
                    Toast.makeText(ExerciseView.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                isRunning = !isRunning;
            }
        });
        timer.setText("");
        if (getIntent() != null) {
            image_id = getIntent().getIntExtra("image_id", -1);
            name = getIntent().getStringExtra("name");
            Glide.with(this)
                    .load(image_id)
                    .apply(RequestOptions.fitCenterTransform())
                    .into(gifImage);
            title.setText(name);

        }

    }




}