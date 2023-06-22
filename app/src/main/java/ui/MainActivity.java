package ui;

import static android.content.SharedPreferences.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import algonquin.cst2335.chen0914.R;


public class MainActivity extends AppCompatActivity {

    //    protected ActivityMainBinding binding;
    private String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.w("MainActivity", "In onCreate() - Loading Widgets");

        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        prefs.getString("LoginName", "");






        Button button = findViewById(R.id.loginButton);
        button.setOnClickListener( (v) -> {
            Log.e("MainActivity", "You clicked the button");
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
            EditText emailEditText = findViewById(R.id.emailText12);
            String emailAddress = emailEditText.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName",emailAddress);
            editor.apply();
            nextPage.putExtra("emailAddress",emailAddress);
            startActivity(nextPage);

        } );




    }



    @Override
    protected void onStart() {
        super.onStart();
//        setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onStart() - The application is now visible on screen.");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onPause() - The application no longer responds to user input.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onResume() - The application is now responding to user input.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //  setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onStop() - The application is no longer visible.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onDestroy() - Any memory used by the application is freed.");
    }
}