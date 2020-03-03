package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Handler handler;

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

        handler = new Handler();

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



        //Methode Handler
        bouttonEgal.setOnClickListener(bouttonEgalHandler());

        //Methode Async
        //bouttonEgal.setOnClickListener(bouttonEgalAsync());



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
            }
        });

        //Multiplication
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                fois = true;
                calcul.setText(null);
            }
        });

        //Addition
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                plus = true;
                calcul.setText(null);
            }
        });


        //Soustraction
        soust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var1=Double.parseDouble(calcul.getText()+"");
                moins = true;
                calcul.setText(null);
            }
        });


        //Boutton clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcul.setText(null);
                resultat.setText(null);
            }
        });

        layout.addView(bouttonEgal);

    }


    //Version Handler
    View.OnClickListener bouttonEgalHandler() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        var2=Double.parseDouble("" + calcul.getText());
                                        if (plus==true){
                                            calcul.setText(var1+ "+"+ var2);
                                            resultat.setText(var1 + var2+"");
                                            plus =false;
                                        }
                                        if (moins==true){
                                            calcul.setText(var1+ "-"+ var2);
                                            resultat.setText(var1 - var2+"");
                                            moins =false;
                                        }

                                        if (fois==true){
                                            calcul.setText(var1+ "*"+ var2);
                                            resultat.setText(var1 * var2+"");
                                            fois =false;
                                        }
                                        if (division==true){
                                            calcul.setText(var1+ "/"+ var2);
                                            resultat.setText(var1 / var2+"");
                                            division =false;
                                        }

                                    }
                                }
                        );
                    }
                };
                new Thread(runnable).start();
            }
        };
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
            var2 = Double.parseDouble("" + calcul.getText());
            if (plus == true) {
                calcul.setText(var1 + "+" + var2);
                resultat.setText(var1 + var2 + "");
                plus = false;
            }
            if (moins == true) {
                calcul.setText(var1 + "-" + var2);
                resultat.setText(var1 - var2 + "");
                moins = false;
            }

            if (fois == true) {
                calcul.setText(var1 + "*" + var2);
                resultat.setText(var1 * var2 + "");
                fois = false;
            }
            if (division == true) {
                calcul.setText(var1 + "/" + var2);
                resultat.setText(var1 / var2 + "");
                division = false;
            }
            return null;

        }

    }

}