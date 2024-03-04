package com.example.fitnessapp_fyp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity3 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    drill2 drill2= new drill2();
    activity2 activity2=new activity2();
    Home2 home2= new Home2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.box, home2).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.box, home2).commit();
                        return true;

                    case R.id.activity:
                        getSupportFragmentManager().beginTransaction().replace(R.id.box, activity2).commit();
                        return true;


                    case R.id.drills:
                        getSupportFragmentManager().beginTransaction().replace(R.id.box, drill2).commit();
                        return true;

                }
                return false;
            }
        });




    }
}