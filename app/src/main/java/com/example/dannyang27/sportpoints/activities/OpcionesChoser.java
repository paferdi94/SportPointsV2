package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.dannyang27.sportpoints.R;

public class OpcionesChoser extends AppCompatActivity {

    private Button equipoBtn;
    private Button eventoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_choser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        equipoBtn = (Button) findViewById(R.id.equipo_btn);
        eventoBtn = (Button) findViewById(R.id.eventos_btn);



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
        Intent i = new Intent(this, EquipoInfo.class);
        startActivity(i);
    }
    public void toEventoView(View v){
        Intent i = new Intent(this, EventoInfo.class);
        startActivity(i);
    }
}
