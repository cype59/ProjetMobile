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

public class SecondActivity extends AppCompatActivity {
    Database myDb;
    EditText stade;
    EditText equipe1;
    EditText formation1;
    EditText equipe2;
    EditText formation2;
    EditText arbitre;
    Button btnAddMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myDb = new Database(this);

       stade = (EditText)findViewById(R.id.stade);
       equipe1 = (EditText)findViewById(R.id.equipe1);
       equipe2 = (EditText)findViewById(R.id.equipe2);
       formation1 = (EditText)findViewById(R.id.formation1);
       formation2 = (EditText)findViewById(R.id.formation2);
       arbitre = (EditText)findViewById(R.id.arbitre);
        btnAddMatch = (Button)findViewById(R.id.btn_add_match);
        addMatch();

    }

    public void addMatch(){
        btnAddMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = myDb.insertMatch(stade.getText().toString(),equipe1.getText().toString(),formation1.getText().toString(),
                        equipe2.getText().toString(),formation2.getText().toString(),arbitre.getText().toString());
                if (inserted == true){
                    Toast.makeText(SecondActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    openActivity3();

                }
                else
                    Toast.makeText(SecondActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openActivity3 (){
        startActivity(new Intent(this, ThirdActivity.class));
    }

}
