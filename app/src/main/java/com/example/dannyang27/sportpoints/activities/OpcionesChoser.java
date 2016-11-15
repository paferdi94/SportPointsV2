package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Equipo.ListarEquipo;
import com.example.dannyang27.sportpoints.activities.PruebasDanny.PruebaListarEvento;

public class OpcionesChoser extends AppCompatActivity {

    private ImageView equipoBtn;
    private ImageView eventoBtn;
    private String id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_opciones_choser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle(getResources().getString(R.string.entrar_en_toolbar));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        equipoBtn = (ImageView) findViewById(R.id.equipo_imView);
        eventoBtn = (ImageView) findViewById(R.id.eventos_imView);

        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void toEquipoView(View v){
        Intent i = new Intent(this, ListarEquipo.class);
        i.putExtra("id_usuario",id_usuario);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"Cargando equipos...", Toast.LENGTH_LONG).show();

    }
    public void toEventoView(View v){
        Intent i = new Intent(this, PruebaListarEvento.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"Cargando eventos...", Toast.LENGTH_SHORT).show();
    }
}
