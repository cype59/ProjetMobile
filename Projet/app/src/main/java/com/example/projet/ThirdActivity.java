package com.example.projet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    Database controller;
    TextView viewEquipe1;
    TextView viewEquipe2;
    TextView viewJoueur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        viewEquipe1 = (TextView) findViewById(R.id.viewEquipe1);
        viewEquipe2 = (TextView) findViewById(R.id.viewEquipe2);
        viewJoueur = (TextView) findViewById(R.id.viewJoueur);
        controller = new Database(this);
        controller.list_equipe(viewEquipe1, 2);
        controller.list_equipe(viewEquipe2, 4);
        controller.list_joueur(viewJoueur,2);
    }
}
