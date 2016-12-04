package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.Evento;
import com.example.dannyang27.sportpoints.activities.Principal.PPrincipal;
import com.example.dannyang27.sportpoints.activities.RecyclerViewHolders.EventoItemHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventoInfo extends AppCompatActivity {

    private DatabaseReference mRef;
    private RecyclerView mEventosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_info);

        //Precargar custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Referencia a Eventos de FireBase
        mRef = FirebaseDatabase.getInstance().getReference().child("Eventos");

        mEventosList = (RecyclerView) findViewById(R.id.recycler_view);
        mEventosList.setHasFixedSize(false);
        mEventosList.setLayoutManager(new LinearLayoutManager(this));

        floattingButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Evento, EventoItemHolder> adapter = new FirebaseRecyclerAdapter<Evento, EventoItemHolder>(
                Evento.class,
                R.layout.evento_item,
                EventoItemHolder.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(EventoItemHolder viewHolder, Evento model, int position) {
                viewHolder.setHora(model.getHora());
                viewHolder.setUsuario(model.getUsuario());

                //Cuando pulsemos un evento...
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),PPrincipal.class);
                        startActivity(intent);
                    }
                });
            }
        };
        mEventosList.setAdapter(adapter);
    }

    //back arrow in a toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void floattingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), CrearEvento.class);
                startActivity(intent);
            }
        });
    }
}

//public class EventoInfo extends AppCompatActivity {
//
//    private ListView listaEventos;
//    //private ImageView addBtn;
//    private SearchView buscador;
//
//    private String[] eventos = { "Evento1", "Evento2", "Evento3", "Evento4", "Evento5", "Evento6", "Evento7", };
//
//    private ArrayAdapter arrayAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.evento_info);
//
//        //findByView
//
//        listaEventos = (ListView) findViewById(R.id.evento_listView);
//        //addBtn = (ImageView) findViewById(R.id.imageView_evento);
//        //buscador = (SearchView) findViewById(R.id.buscar_evento);
//
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,eventos);
//        listaEventos.setAdapter(arrayAdapter);
//
//        //Pasar objeto parcelable al intent;
//
//        final EventoParcelable evento = new EventoParcelable();
//        evento.setNombre("Torneo Overwatch");
//        evento.setHora("15:00");
//        evento.setFecha("23/04/2017");
//        evento.setDescripcion("omg no widow");
//        evento.setCapacidadActual(0);
//        evento.setCapacidadMaxima(100);
//
//        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                goEventoConfigView(evento);
//                Toast.makeText(getApplicationContext(),eventos[i].toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private void goEventoConfigView(EventoParcelable evento) {
//        Intent i = new Intent(this, EventoConfigView.class);
//        i.putExtra("PARCELABLE",evento);
//        startActivity(i);
//    }
//}
