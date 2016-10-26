package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.ClasesBasicas.EventoParcelable;

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

        //Pasar objeto parcelable al intent;

         final EventoParcelable evento = new EventoParcelable();
        evento.setNombre("Quedada youtuber huehue");
        evento.setHora("15:00");
        evento.setFecha("23/04/2017");
        evento.setDescripcion("Caceria Poketomonostah");
        evento.setCapacidadActual(0);
        evento.setCapacidadMaxima(100);





        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                goEventoConfigView(evento);
                Toast.makeText(getApplicationContext(),eventos[i].toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goEventoConfigView(EventoParcelable evento) {
        Intent i = new Intent(this, EventoConfigView.class);
        i.putExtra("PARCELABLE",evento);
        startActivity(i);
    }
}
