package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.example.dannyang27.sportpoints.R;

public class CrearEquipo extends AppCompatActivity {
    private EditText nombre_equipo;
    private Spinner deporte;
    private ImageView img_equipo;
    private Button subir_img_btn;
    private Button cancelar_btn;
    private Button crear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_equipo);

        nombre_equipo = (EditText) findViewById(R.id.nombre_equipo_field);
        deporte = (Spinner) findViewById(R.id.deporte_spinner);
        img_equipo = (ImageView)findViewById(R.id.img_equipo);
        subir_img_btn = (Button) findViewById(R.id.subir_btn);
        cancelar_btn = (Button) findViewById(R.id.cancelar_btn);
        crear_btn = (Button) findViewById(R.id.crear_btn);

        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

        crear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crear();
            }
        });


    }

    public void cancelar(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void crear(){
        //Intent i = new Intent(this, Deportes.class);
        //startActivity(i);
    }

    public void comprobar(Equipo eq){

    }
}
