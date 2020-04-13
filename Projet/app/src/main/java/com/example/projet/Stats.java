package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {
    Database controller;
    ArrayList<String> stade;
    ArrayList<String> equipe1;
    ArrayList<String> score1;
    ArrayList<String> score2;
    ArrayList<String> equipe2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        controller = new Database(this);

        stade = viewdata("Stade");
        equipe1 = viewdata("Equipe1");
        equipe2 = viewdata("Equipe2");
        score1 = viewdata("ScoreEquipe1");
        score2 = viewdata("ScoreEquipe2");
        
        
        TableLayout table_match = (TableLayout) findViewById(R.id.idTableMatch); // on prend le tableau défini dans le layout
        affiche_joueur(table_match);
        
    }

    
    public ArrayList viewdata(String colonne) {
        ArrayList data = new ArrayList<String>();;
        Cursor cursor = controller.list_match();
        if (cursor.getCount()==0){
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext())
                data.add(cursor.getString(cursor.getColumnIndex(colonne)));
        }
        return data;
    }
    
    public void affiche_joueur(TableLayout table) {
        TableRow row; // création d'un élément : ligne
        TextView tv1,tv3,tv4,tv5,tv6; // création des cellules
        for (int i = 0; i < stade.size(); i++) {
            row = new TableRow(this); // création d'une nouvelle ligne
            
            tv1 = new TextView(this); // création cellule
            tv3 = new TextView(this);
            tv4 = new TextView(this);
            tv5 = new TextView(this);
            tv6 = new TextView(this);
            
            tv1.setText(stade.get(i)); // ajout du texte
            tv3.setText(equipe1.get(i));
            tv4.setText(score1.get(i));
            tv5.setText(score2.get(i));
            tv6.setText(equipe2.get(i));

            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv3.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv4.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv5.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv5.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv6.setGravity(Gravity.CENTER); // centrage dans la cellule
            tv6.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            
            row.addView(tv1);// ajout des cellules à la ligne
            row.addView(tv3);
            row.addView(tv4);
            row.addView(tv5);
            row.addView(tv6);
            
            table.addView(row);// ajout de la ligne au tableau
        }
    }
}
