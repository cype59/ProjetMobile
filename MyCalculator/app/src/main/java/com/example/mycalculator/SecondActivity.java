package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null){
            double var1 = intent.getDoubleExtra("var1", 0);
            double var2 = intent.getDoubleExtra("var2", 0);
            double result = intent.getDoubleExtra("result", 0);
            char op = intent.getCharExtra("operateur", ' ');


            TextView textView = (TextView) findViewById(R.id.tv1);
            textView.setText(var1 + String.valueOf(op) + var2 + "=" + result);
        }
    }

        public void rechercheInternet(View v) {
            // 1. Appeler une URL web
            EditText editText = (EditText) findViewById(R.id.urlInternet);

            String url = editText.getText().toString();

            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);
        }
    }


