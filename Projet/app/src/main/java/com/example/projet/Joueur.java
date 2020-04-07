package com.example.projet;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Joueur extends AppCompatActivity {
    Database myDb;
    EditText nom1;
    EditText nom2;
    Button btnAddEquipe1;
    Button btnAddEquipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueur);
        myDb = new Database(this);

        nom1 = (EditText)findViewById(R.id.nomJoueur1);
        nom2 = (EditText)findViewById(R.id.nomJoueur2);
        btnAddEquipe1 = (Button)findViewById(R.id.btn_equipe1);
        btnAddEquipe2 = (Button)findViewById(R.id.btn_equipe2);
        addEquipe1();
        addEquipe2();
    }

    public void addEquipe1(){
        btnAddEquipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = myDb.insertJoueur (nom1.getText().toString(),1,"",1);
                if (inserted == true){
                    Toast.makeText(Joueur.this, "Data inserted", Toast.LENGTH_LONG).show();
                    nom1.setText(null);
                }
                else
                    Toast.makeText(Joueur.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addEquipe2(){
        btnAddEquipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = myDb.insertJoueur (nom2.getText().toString(),2,"",1);
                if (inserted == true){
                    Toast.makeText(Joueur.this, "Data inserted", Toast.LENGTH_LONG).show();
                    nom2.setText(null);

                }
                else
                    Toast.makeText(Joueur.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openActivity3 (View v){
        startActivity(new Intent(this, ThirdActivity.class));
    }

}