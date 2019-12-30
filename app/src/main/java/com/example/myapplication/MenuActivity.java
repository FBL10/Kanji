package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Switch pro_switch =  findViewById(R.id.prononciation_switch);
        Switch trad_switch =  findViewById(R.id.traduction_switch);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            int trad = extras.getInt("trad_state");
            int pro = extras.getInt("pro_state");

            if (trad == 1){
                trad_switch.setChecked(true);
            }
            else{
                trad_switch.setChecked(false);
            }
            if (pro == 1){
                pro_switch.setChecked(true);
            }
            else{
                pro_switch.setChecked(false);
            }
        }
    }

    // lance l'activité MainActivity
    public void start_button_click(View v) {

        Switch pro_switch =  findViewById(R.id.prononciation_switch);
        Switch trad_switch =  findViewById(R.id.traduction_switch);

        boolean pro_state = pro_switch.isChecked();
        boolean trad_state = trad_switch.isChecked();

        Intent start_main = new Intent(this, MainActivity.class);
        start_main.putExtra("pro_state",pro_state);
        start_main.putExtra("trad_state",trad_state);
        startActivity(start_main);
    }

    public void button2_click(View v) {

        Switch pro_switch =  findViewById(R.id.prononciation_switch);
        Switch trad_switch =  findViewById(R.id.traduction_switch);

        boolean pro_state = pro_switch.isChecked();
        boolean trad_state = trad_switch.isChecked();

        Intent start_main_2 = new Intent(this, MainActivity.class);
        start_main_2.putExtra("pro_state",pro_state);
        start_main_2.putExtra("trad_state",trad_state);
        startActivity(start_main_2);
    }

    public void back_button_click(View v) {
        return ;
    }
}
