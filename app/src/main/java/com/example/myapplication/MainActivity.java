package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

//ignored warning : hard-coded string will not be translated
@SuppressLint("SetTextI18n")

public class MainActivity extends AppCompatActivity {

    int[] ordre ;

    String[][] mots;

    ImageButton menu_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bouton principal : affiche la réponse ou change de mot
        Button button = findViewById(R.id.button);
        button.getBackground().setAlpha(0);

        mots = Mots.getMots();

        // bouton du menu principal
        menu_button = (ImageButton) findViewById(R.id.main_menu_button);


        TextView text_symbole = (TextView)findViewById(R.id.symbole);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("number");
            text_symbole.setText(Integer.toString(value));
        }
    }

    int nombre_traduction = 0;
    int romaji = 0;

    Boolean reponse = false; // indique si l'utilisateur voit la réponse

    int iterateur_mots = 0; // itère parmis les mots courants

    public void traduction_switch(View v){

        nombre_traduction = 1 - nombre_traduction;
        iterateur_mots = 0;
        reponse = false;

        buttonClick(v);
    }

    public void prononciation_switch(View v){

        TextView prononciation = (TextView)findViewById(R.id.prononciation);

        romaji= 1 - romaji;

        if(ordre.length != 0 && !reponse) {

            if (mots[ordre[iterateur_mots - 1]][1].length() == 0 && romaji == 0){
                prononciation.setText("");
            } else {
                prononciation.setText("(" + mots[ordre[iterateur_mots-1]][1+romaji] + ")");
            }
        }
    }

    // TODO : garder l'état des activités quittées

    // lance l'activité MenuActivity
    public void main_menu_button_click(View v){
        Intent start_menu = new Intent(this, MenuActivity.class);
        startActivity(start_menu);
    }

    // affiche la réponse ou change de mot
    public void buttonClick(View v) {

        TextView text_symbole = (TextView)findViewById(R.id.symbole);
        TextView prononciation = (TextView)findViewById(R.id.prononciation);
        TextView text_traduction = (TextView)findViewById(R.id.traduction);

        // crée un nouvel ordre de mot au premier
        // ou si l'utilisateur les a tous vus
        if (iterateur_mots == 0 && !reponse){
            ordre = Logique.make_shuffled_tab(mots.length);
        }

        // affiche la réponse
        if (!reponse){

            String curr_symbole = mots[ordre[iterateur_mots]][3 *(nombre_traduction)];
            text_symbole.setText(curr_symbole);

            int symbole_textSize = 30 + (150 / (text_symbole.getText()).length());
            text_symbole.setTextSize(symbole_textSize);

            prononciation.setText("");
            text_traduction.setText("");
        }

        //affiche une nouveau mot
        else{

            // si le mot à une pronociation
            if (mots[ordre[iterateur_mots]][1+romaji].length() != 0) {

                String curr_pronociation = "(" + mots[ordre[iterateur_mots]][1 + romaji] + ")" ;
                prononciation.setText(curr_pronociation);
            }

            String curr_traduction = mots[ordre[iterateur_mots]][-3 * nombre_traduction + 3] ;
            text_traduction.setText(curr_traduction);

            int traduction_textSize = 40 + (100 / (text_traduction.getText()).length());
            text_traduction.setTextSize(traduction_textSize);

            iterateur_mots = (iterateur_mots + 1) % mots.length;
        }

        reponse = !reponse;

    }
}
