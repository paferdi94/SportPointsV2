package com.example.dannyang27.sportpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoUsuario extends AppCompatActivity {

    private Button jugador_btn;
    private Button arbitro_btn;
    private Button centroDeportivo_btn;
    private Button atras_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipo_usuario);

        jugador_btn = (Button) findViewById(R.id.jugador_btn);
        arbitro_btn = (Button)findViewById(R.id.arbitro_btn);
        centroDeportivo_btn = (Button) findViewById(R.id.centro_deportivo_btn);
        atras_btn = (Button) findViewById(R.id.atras_btn_1);

        jugador_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        arbitro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        centroDeportivo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });


        atras_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });



    }

    public void login(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void atras(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
