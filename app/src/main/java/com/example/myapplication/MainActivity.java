package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

// TODO : donner les mots au menu, les renvoyer ou changer selon la section, implementer back_button

//ignored warning : hard-coded string will not be translated
@SuppressLint("SetTextI18n")

public class MainActivity extends AppCompatActivity {

    boolean first;

    String[][] mots;

    int[] ordre ; // ordre courant des mots

    Boolean reponse = false; // indique si l'utilisateur voit la réponse

    int iterateur_mots = 0; // itère parmis les mots courants

    int nombre_traduction = 0;
    int romaji = 0;

    ImageButton menu_button ;

    // ignored warning : overrides OnTouchListener but does not define onClick event
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        first = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bouton principal : affiche la réponse ou change de mot
        Button button = findViewById(R.id.button);
        button.getBackground().setAlpha(0); // bouton transparent

        TextView prononciation = findViewById(R.id.prononciation);
        TextView text_traduction = findViewById(R.id.traduction);
        TextView text_symbole = findViewById(R.id.symbole);

        mots = Mots.getMots(); // seulement si aucun choix dans le menu ************

        // bouton du menu principal
        menu_button = (ImageButton) findViewById(R.id.menu_button);

        // recoit les changements de traduction et de
        // prononciation du menu ainsi que le choix de section
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            boolean trad = extras.getBoolean("trad_state");
            boolean pro = extras.getBoolean("pro_state");
            reponse = extras.getBoolean("reponse");

            if (trad){
                nombre_traduction = 1;
            }
            else{
                nombre_traduction = 0;
            }
            if (pro){
                romaji = 1;
            }
            else{
                romaji = 0;
            }

            ordre = extras.getIntArray("ordre");
            iterateur_mots = extras.getInt("iterateur_mots");

            if (ordre != null){

                reponse = !reponse;

                if (reponse){
                    if (iterateur_mots == 0){
                        iterateur_mots = ordre.length - 1;
                    }
                    else{
                        iterateur_mots -= 1;
                    }
                }

                String curr_symbole = mots[ordre[iterateur_mots]][3 *(nombre_traduction)];
                text_symbole.setText(curr_symbole);

                int symbole_textSize = 30 + (150 / (text_symbole.getText()).length());
                text_symbole.setTextSize(symbole_textSize);

                if (reponse){
                    iterateur_mots = (iterateur_mots + 1) % mots.length;
                }

                reponse = !reponse;

                if(ordre.length != 0 && !reponse) {

                    if (mots[ordre[iterateur_mots - 1]][1].length() == 0 && romaji == 0){
                        prononciation.setText("");
                    } else {
                        prononciation.setText("(" + mots[ordre[iterateur_mots-1]][1+romaji] + ")");
                    }
                }
            }
        }

        // override la gestion de click du bouton principal
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = false;
                buttonClick(v);
            }
        });

        // gestion de swipe, droite change la traduction, gauche la prononciation
        button.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeRight() {
                first = false;

                nombre_traduction = 1 - nombre_traduction;

                TextView text_symbole = findViewById(R.id.symbole);

                reponse = !reponse;

                if (reponse){
                    if (iterateur_mots == 0 && first){
                        iterateur_mots = ordre.length;
                    }
                    else if (iterateur_mots == 0){
                        iterateur_mots = ordre.length - 1;
                    }
                    else{
                        iterateur_mots -= 1;
                    }
                }

                String curr_symbole = mots[ordre[iterateur_mots]][3 *(nombre_traduction)];
                text_symbole.setText(curr_symbole);

                int symbole_textSize = 30 + (150 / (text_symbole.getText()).length());
                text_symbole.setTextSize(symbole_textSize);

                if (reponse){
                    iterateur_mots = (iterateur_mots + 1) % mots.length;
                }

                reponse = !reponse;

                return true;
            }
            public boolean onSwipeLeft() {
                first = false;

                TextView prononciation = (TextView)findViewById(R.id.prononciation);

                romaji= 1 - romaji;

                if(ordre.length != 0 && !reponse) {

                    if (mots[ordre[iterateur_mots - 1]][1].length() == 0 && romaji == 0){
                        prononciation.setText("");
                    } else {
                        prononciation.setText("(" + mots[ordre[iterateur_mots-1]][1+romaji] + ")");
                    }
                }
                return true;
            }
        });
    }

    // lance l'activité MenuActivity
    public void main_menu_button_click(View v){
        Intent start_menu = new Intent(this, MenuActivity.class);

        start_menu.putExtra("pro_state",romaji);
        start_menu.putExtra("trad_state",nombre_traduction);
        start_menu.putExtra("iterateur_mots", iterateur_mots);
        start_menu.putExtra("ordre", ordre);
        start_menu.putExtra("reponse", reponse);

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

        //affiche une nouveau mot
        if (!reponse){

            String curr_symbole = mots[ordre[iterateur_mots]][3 *(nombre_traduction)];
            text_symbole.setText(curr_symbole);

            int symbole_textSize = 30 + (150 / (text_symbole.getText()).length());
            text_symbole.setTextSize(symbole_textSize);

            prononciation.setText("");
            text_traduction.setText("");
        }

        // affiche la réponse
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
