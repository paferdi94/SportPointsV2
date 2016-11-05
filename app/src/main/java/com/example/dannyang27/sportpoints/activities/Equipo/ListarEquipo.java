package com.example.dannyang27.sportpoints.activities.Equipo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Base64Custom;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Map;

public class ListarEquipo extends AppCompatActivity {

    private ListView listar;
    private ArrayAdapter<EquipoParceable> adapter;
    private EquipoItemHolder customAdapter;
    private FloatingActionButton newEquipbtn;
    //private EditText filter;
    private ArrayList<EquipoParceable> listaEquipos = new ArrayList<>();
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private String id_usuario;
    private EquipoParceable equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_Lista);
        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");
        //filter = (EditText) findViewById(R.id.filter_id1);
        newEquipbtn = (FloatingActionButton) findViewById(R.id.newEquipbtn);
        listar = (ListView) findViewById(R.id.equipo_listView);
        customAdapter = new EquipoItemHolder(this, listaEquipos);
        listar.setAdapter(customAdapter);
        listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EquipoParceable e = listaEquipos.get(i);
                showEquipoInfo(e);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_2);
        toolbar.setTitle("LISTADO DE EQUIPOS");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DatabaseReference eRef = mRef.child("Equipos");
        eRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Bitmap logo = Base64Custom.decodeBase64(map.get("logo").toString());
                EquipoParceable q = new EquipoParceable(
                        dataSnapshot.getKey(),
                        map.get("nombre").toString(),
                        map.get("deporte").toString(),
                        Integer.parseInt(map.get("max_jugadores").toString()),
                        logo
                );
                if(!map.get("jugadores").toString().equals("")){
                    q.addJugadores(map.get("jugadores").toString());
                }
                listaEquipos.add(q);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Bitmap logo = Base64Custom.decodeBase64(map.get("logo").toString());
                int pos = -1;
                for (EquipoParceable eq : listaEquipos) {
                    pos++;
                    if(eq.getID().equals(dataSnapshot.getKey())){
                        eq = new EquipoParceable(
                                dataSnapshot.getKey(),
                                map.get("nombre").toString(),
                                map.get("deporte").toString(),
                                Integer.parseInt(map.get("max_jugadores").toString()),
                                logo
                        );
                        if(!map.get("jugadores").toString().equals("")){
                            eq.addJugadores(map.get("jugadores").toString());
                        }
                        listaEquipos.set(pos,eq);
                        customAdapter.notifyDataSetChanged();
                        break;
                    }
                }
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

        newEquipbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toCrearEquipo();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home: onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    // Mostrar un equipo.
    private void showEquipoInfo(EquipoParceable e) {
        Intent i = new Intent(this, EquipoInfo.class);
        i.putExtra("Equipo", e);
        i.putExtra("Usuario",id_usuario);
        startActivity(i);
    }

    // Crear un equipo.
    private void toCrearEquipo() {
        Intent i = new Intent(this, CrearEquipo.class);
        i.putExtra("id_usuario", id_usuario);
        startActivity(i);
    }
}