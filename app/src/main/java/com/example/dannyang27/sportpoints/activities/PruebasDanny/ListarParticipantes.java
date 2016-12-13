package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Evento.EventoParcelable;
import com.example.dannyang27.sportpoints.activities.Modelos.Participante;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListarParticipantes extends AppCompatActivity {
    private ListView participantesLv;
    private List<Participante> listaPart = new ArrayList<>();
    private List<Participante> listaPartTemp = new ArrayList<>();
    private CustomPartAdapter cAdapter;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Eventos");
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_activity_listar_participantes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_4);
        toolbar.setTitle("LISTA PARTICIPANTES");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final EventoParcelable evento = getIntent().getParcelableExtra("PARCELABLE");
        //listaPartTemp = evento.getParticipantes();

        //Participante p = new Participante("Pepe", "654987321");
        //listaPart.add(p);


        //emptyText = (TextView) findViewById(android.R.id.empty);

        participantesLv = (ListView) findViewById(R.id.listarParticipantes_id);
        cAdapter = new CustomPartAdapter(this, listaPart);

        participantesLv.setEmptyView(findViewById(android.R.id.empty));
        participantesLv.setAdapter(cAdapter);

        participantesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),listaPart.get(position)
                        .getNombre().toString() ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference partRef= mRef.child(evento.getNombre()).child("Participantes");
        partRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Participante e = dataSnapshot.getValue(Participante.class);
                listaPart.add(e);
                cAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

}
