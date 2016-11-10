package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.dannyang27.sportpoints.R;

public class Deportes extends AppCompatActivity {

    private EditText email_field;
    private EditText password1_field;
    private EditText password2_field;
    private CheckBox futbol_cb;
    private CheckBox baloncesto_cb;
    private CheckBox tenis_cb;
    private Button atras_btn_2;
    private Button fin_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_deportes);

        email_field = (EditText) findViewById(R.id.email_field);
        password1_field = (EditText) findViewById(R.id.contrasenya1_field);
        password2_field = (EditText) findViewById(R.id.contrasenya2_field);
        futbol_cb = (CheckBox) findViewById(R.id.futbol_checkbox);
        baloncesto_cb = (CheckBox) findViewById(R.id.baloncesto_checkbox);
        tenis_cb = (CheckBox) findViewById(R.id.tenis_checkbox);
        atras_btn_2 = (Button) findViewById(R.id.atras_btn_2);
        fin_btn = (Button) findViewById(R.id.fin_btn);

        atras_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        fin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente();
            }
        });

    }
    public void atras(){
        Intent i = new Intent(this, PerfilAlta.class);
        startActivity(i);
    }
    public void siguiente(){
        Intent i = new Intent(this, CorrectoAlta.class);
        startActivity(i);

    }
}
