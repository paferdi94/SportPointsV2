package com.example.dannyang27.sportpoints.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dannyang27.sportpoints.R;

public class EventoInfo extends AppCompatActivity {

    private ListView listaEventos;
    private ImageView addBtn;
    private SearchView buscador;

    private String[] eventos = { "Evento1", "Evento2", "Evento3", "Evento4", "Evento5", "Evento6", "Evento7", };

    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_info);

        //findByView

        listaEventos = (ListView) findViewById(R.id.evento_listView);
        addBtn = (ImageView) findViewById(R.id.imageView_evento);
        //buscador = (SearchView) findViewById(R.id.buscar_evento);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,eventos);
        listaEventos.setAdapter(arrayAdapter);

    }
}
