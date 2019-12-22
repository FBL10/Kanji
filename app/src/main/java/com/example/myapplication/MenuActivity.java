package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    // lance l'activité MainActivity
    public void start_button_click(View v) {
        Intent start_main = new Intent(this, MainActivity.class);
        start_main.putExtra("number",1);
        startActivity(start_main);
    }

    public void button2_click(View v) {
        Intent start_main_2 = new Intent(this, MainActivity.class);
        start_main_2.putExtra("number",2);
        startActivity(start_main_2);
    }
}
