package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    //Création des variables qui vont contenir les variables des bouttons
    Button zero;
    Button un;
    Button deux;
    Button trois;
    Button quatre;
    Button cinq;
    Button six;
    Button sept;
    Button huit;
    Button neuf;
    Button div;
    Button multi;
    Button add;
    Button soust;
    Button clear;
    TextView calcul;
    TextView resultat;
    boolean update = false;
    boolean plus, moins, fois, division;
    double var1;
    double var2;
    double result;
    char op ;


    //Linker les variables d'en haut avec les id du content_main
    public void settingValues() {
        zero = (Button) findViewById(R.id.btn0);
        un = (Button) findViewById(R.id.btn1);
        deux = (Button) findViewById(R.id.btn2);
        trois = (Button) findViewById(R.id.btn3);
        quatre = (Button) findViewById(R.id.btn4);
        cinq = (Button) findViewById(R.id.btn5);
        six = (Button) findViewById(R.id.btn6);
        sept = (Button) findViewById(R.id.btn7);
        huit = (Button) findViewById(R.id.btn8);
        neuf = (Button) findViewById(R.id.btn9);
        div = (Button) findViewById(R.id.btndiv);
        multi = (Button) findViewById(R.id.btnfois);
        add = (Button) findViewById(R.id.btnplus);
        soust = (Button) findViewById(R.id.btnmoins);
        clear = (Button) findViewById(R.id.btnclear);


        calcul = (TextView) findViewById(R.id.calcul);
        resultat = (TextView) findViewById(R.id.resultat);

    }



    void chiffre (String str){
        if (update){
            update= false;
        }
        else {
            if (!calcul.getText().equals("0"))
                str =calcul.getText()+str;
        }
        calcul.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayout layout = findViewById(R.id.layoutEgal);

        settingValues();


        //Méthode permettant d'ajouter le boutton egal
        Button bouttonEgal = new Button(this);
        bouttonEgal.setId(R.id.bouttonEgal);
        bouttonEgal.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT )
        );
        bouttonEgal.setText("=");


        //Methode Async
        bouttonEgal.setOnClickListener(bouttonEgalAsync());



        //faire apparaître les chiffres et les signes sur le TextView à la suite
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("0");
            }
        });
        un.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("1");
            }
        });
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("2");
            }
        });
        trois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("3");
            }
        });
        quatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("4");
            }
        });
        cinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("6");
            }
        });
        sept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("7");
            }
        });
        huit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("8");
            }
        });
        neuf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffre("9");
            }
        });



        //programmation dees opérations
        //Division
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                division = true;
                calcul.setText(null);
                op = '/';
            }
        });

        //Multiplication
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                fois = true;
                calcul.setText(null);
                op = '*';

            }
        });

        //Addition
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                plus = true;
                calcul.setText(null);
                op = '+';

            }
        });


        //Soustraction
        soust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                moins = true;
                calcul.setText(null);
                op = '-';
            }
        });


        //Boutton clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcul.setText(null);
                resultat.setText(null);
                op = ' ';

            }
        });

        layout.addView(bouttonEgal);

    }

    //Version Async
    View.OnClickListener bouttonEgalAsync()  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                AsyncEgal task = new AsyncEgal();
                task.execute();

            }
        };
    }
    private class AsyncEgal extends AsyncTask<Void, Void, String> {
        @SuppressLint("WrongThread")
        protected String doInBackground(Void... voids) {
            try {

                var2=Double.parseDouble("" + calcul.getText());

                Socket socket = new Socket("10.0.2.2", 9876);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeDouble(var1);
                dos.writeChar(op);
                dos.writeDouble(var2);

                result= dis.readDouble();


                dis.close();
                dos.close();
                socket.close();


            }catch (IOException e) {
                e.printStackTrace();
            }


            resultat.setText(String.valueOf(result));
            calcul.setText(var1 + String.valueOf(op) + var2 + "=" + result);
            return null;

        }
    }

    //Création du menu en haut à droite
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Action en fonction de l'item du menu sélectionné
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.second_activity:
                ChangeActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Méthode permettant de changer d'activité en envoyant les paramètres de l'opération
    public void ChangeActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("var1", var1);
        intent.putExtra("var2", var2);
        intent.putExtra("result", result);
        intent.putExtra("operateur", op);

        startActivity(intent);

    }

}