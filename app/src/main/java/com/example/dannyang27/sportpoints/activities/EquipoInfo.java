package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dannyang27.sportpoints.R;

public class EquipoInfo extends AppCompatActivity {

    private String id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_info);
        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");
    }

    public void toCrearEquipoView(View v){
        Intent i = new Intent(this, CrearEquipo.class);
        i.putExtra("id_usuario",id_usuario);
        startActivity(i);
    }
}
