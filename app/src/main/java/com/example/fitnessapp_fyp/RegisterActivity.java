package com.example.fitnessapp_fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    EditText username, password, repassword;
    Button signup, signin;
    DBHelper MyDB;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        MyDB = new DBHelper(getApplicationContext());
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        if(MyDB.checkusername(user)){ // check if the username already exists
                            Toast.makeText(getApplicationContext(),"User already exists! Please try a different username", Toast.LENGTH_SHORT).show();
                        } else {
                            Boolean insert = MyDB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                                // Stores the username of the registered user in shared preferences
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", user);
                                editor.apply();
                                Intent intent = new Intent(getApplicationContext().getApplicationContext(),MainActivity3.class);
                                intent.putExtra("username", user);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Passwords not matching",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);


            }
        });

    }
    }
