package com.example.projet;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.String.*;

public class ThirdActivity extends AppCompatActivity {
    Database controller;
    TextView viewEquipe1;
    TextView viewEquipe2;
    TextView viewScore1;
    TextView viewScore2;
    String[] joueur = new String[1];
    String[] id_joueur = new String[1];
    int score_equipe_1 = 0;
    int score_equipe_2 = 0;
    int numeroEquipe;

    Button btnAddBut;
    Button btnAddPasse;
    Button btnAddRouge;
    Button btnAddJaune;
    Button btnFinirMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        viewEquipe1 = (TextView) findViewById(R.id.viewEquipe1);
        viewEquipe2 = (TextView) findViewById(R.id.viewEquipe2);
        viewScore1 = (TextView) findViewById(R.id.viewScore1);
        viewScore2 = (TextView) findViewById(R.id.viewScore2);
        controller = new Database(this);
        controller.list_equipe(viewEquipe1, 2);
        controller.list_equipe(viewEquipe2, 4);

        String[] nom_equipe1 = new String[11];
        String[] id_equipe1 = new String[11];
        nom_equipe1 = list_joueur(1, nom_equipe1, 1);
        id_equipe1 = list_joueur(1, id_equipe1, 0);
        TableLayout table_equipe1 = (TableLayout) findViewById(R.id.idTable1); // on prend le tableau défini dans le layout
        affiche_joueur(nom_equipe1, id_equipe1, 1, table_equipe1);

        String[] nom_equipe2 = new String[11];
        String[] id_equipe2 = new String[11];
        nom_equipe2 = list_joueur(2, nom_equipe2, 1);
        id_equipe2 = list_joueur(2, id_equipe2, 0);
        TableLayout table_equipe2 = (TableLayout) findViewById(R.id.idTable2); // on prend le tableau défini dans le layout
        affiche_joueur(nom_equipe2, id_equipe2, 2,table_equipe2);

        btnAddBut = (Button) findViewById(R.id.but);
        btnAddPasse = (Button) findViewById(R.id.passe);
        btnAddRouge = (Button) findViewById(R.id.cartonRouge);
        btnAddJaune = (Button) findViewById(R.id.cartonJaune);
        btnFinirMatch = (Button) findViewById(R.id.finirMatch);

        add_but();
        add_passe();
        add_jaune();
        add_rouge();
        finir_match();

    }

    public String[] list_joueur(int equipe, String[] data, int col) {
        int i = 0;
        Cursor cursor = controller.list_joueur(equipe);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                data[i] = cursor.getString(col);
                i = i + 1;
            }
        }
        return data;
    }

    public void affiche_joueur(String[] nom, String[] id, final int numEquipe, TableLayout table) {
        TableRow row; // création d'un élément : ligne
        TextView tv1; // création des cellules
        for (int i = 0; i < nom.length; i++) {
            row = new TableRow(this); // création d'une nouvelle ligne
            final int j = i;
            final String[] finalNom = nom;
            final String[] finalId = id;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    joueur[0] = finalNom[j];
                    id_joueur[0] = finalId[j];
                    numeroEquipe = numEquipe;
                }
            });

            tv1 = new TextView(this); // création cellule
            tv1.setText(nom[i]); // ajout du texte
            row.addView(tv1);// ajout des cellules à la ligne
            table.addView(row);// ajout de la ligne au tableau
        }
    }

    public void add_but() {

        btnAddBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id_joueur[0] == null) {  //si aucun joueur n'a été selectionné afficher une alerte
                    Toast.makeText(ThirdActivity.this, "Slectionner un joueur", Toast.LENGTH_SHORT).show();
                } else {
                    if (numeroEquipe == 1) {  //si le joueur est dans l'équipe 1
                        score_equipe_1 = score_equipe_1 + 1;
                        viewScore1.setText(valueOf(score_equipe_1));

                    } else {
                        score_equipe_2 = score_equipe_2 + 1;
                        viewScore2.setText(valueOf(score_equipe_2));
                    }
                    controller.ajout_role(id_joueur, "But");
                    id_joueur[0] = null;  // on initialise la variable à null pour obliger l'utilisateur à re selectionner un joueur
                }
            }
        });
    }

    public void add_passe() {
        btnAddPasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_joueur[0] == null) {
                    Toast.makeText(ThirdActivity.this, "Slectionner un joueur", Toast.LENGTH_SHORT).show();
                } else {

                    controller.ajout_role(id_joueur, "Passe");
                    id_joueur[0] = null;
                }
            }
        });
    }

    public void add_jaune() {
        btnAddJaune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_joueur[0] == null) {
                    Toast.makeText(ThirdActivity.this, "Slectionner un joueur", Toast.LENGTH_SHORT).show();
                } else {

                    controller.ajout_role(id_joueur, "Jaune");
                    id_joueur[0] = null;
                }
            }
        });
    }

    public void add_rouge() {
        btnAddRouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_joueur[0] == null) {
                    Toast.makeText(ThirdActivity.this, "Slectionner un joueur", Toast.LENGTH_SHORT).show();
                } else {

                    controller.ajout_role(id_joueur, "Rouge");
                    id_joueur[0] = null;
                }
            }
        });
    }

    public void finir_match() {
        btnFinirMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.ajout_score(score_equipe_1,score_equipe_2);
            }
        });
    }
}

