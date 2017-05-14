package com.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import com.dannyang27.sportpoints.R;
import com.dannyang27.sportpoints.activities.Equipo.EquipoPruebaDanny;
import com.dannyang27.sportpoints.activities.Evento.DialogCalificarJugador;
import com.dannyang27.sportpoints.activities.Evento.EventoPruebaDanny;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VerParticipantesProvisional extends AppCompatActivity {

    private Toolbar tb;
    private ListView lv;
    private ArrayList<String> participantes = new ArrayList<>();
    private RatingBar ratingBar;
    private Button btnCalificar;

    private String[] arrayParticipantes = new String[30];

    FirebaseDatabase mRef = FirebaseDatabase.getInstance();
    DatabaseReference mRefPart = mRef.getReference();
    DatabaseReference mRefParticipates;
    EquipoPruebaDanny nombre_equipo;
    EventoPruebaDanny evento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaa_activity_ver_participantes_provisional);

        //Intent i = getIntent();
        //participantes = i.getStringArrayListExtra("listaParticipantes");

        //EquipoPruebaDanny nombre_equipo = getIntent().getParcelableExtra("PARCELABLE");

        if ((getIntent().getParcelableExtra("PARCELABLE") instanceof EquipoPruebaDanny)) {
            nombre_equipo = getIntent().getParcelableExtra("PARCELABLE");
            mRefParticipates = mRefPart.child("Equipos").child(nombre_equipo.getNombre())
                    .child("participantes");
        } else {
            evento = getIntent().getParcelableExtra("PARCELABLE");
            mRefParticipates = mRefPart.child("Eventos").child(evento.getNombre())
                    .child("participantes");
        }


        tb = (Toolbar) findViewById(R.id.tb);
        lv = (ListView) findViewById(R.id.listView_participantes);

        /////////////////////Añadir Toolbar////////////////////////
        tb.setTitle("Listado de participantes");
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = (String) parent.getItemAtPosition(position);
                //Toast.makeText(VerParticipantesProvisional.this, val, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DialogCalificarJugador.class);
                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("email", val);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mRefParticipates.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*
                int key =Integer.parseInt(dataSnapshot.getKey().toString());
                String p = dataSnapshot.getValue(String.class).toString();

                arrayParticipantes[key] = p;
                 */
                String p = dataSnapshot.getValue(String.class).toString();
                participantes.add(p);

                //Toast.makeText(getApplicationContext(), key+" : "+p, Toast.LENGTH_SHORT).show();
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


        //rellenarArrayList(arrayParticipantes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.custom_textview, participantes);

        lv.setAdapter(adapter);


    }


    public int devolverKey(String str) {
        int k = -1;
        for (int i = 0; i < arrayParticipantes.length; i++) {
            if (arrayParticipantes[i].equals(str)) {
                return i;
            }
        }
        return k;

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

